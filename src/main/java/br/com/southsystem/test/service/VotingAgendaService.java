package br.com.southsystem.test.service;

import br.com.southsystem.test.dto.ResponseVotingEndedDTO;
import br.com.southsystem.test.dto.ResquestOpenVoteDTO;
import br.com.southsystem.test.dto.ResquestVoteDTO;
import br.com.southsystem.test.dto.ResquestVotingAgendaCreateDTO;
import br.com.southsystem.test.exception.BadRequestException;
import br.com.southsystem.test.model.Associate;
import br.com.southsystem.test.model.Vote;
import br.com.southsystem.test.model.VotingAgenda;
import br.com.southsystem.test.repository.VotingAgendaRepository;
import br.com.southsystem.test.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class VotingAgendaService {
    final VotingAgendaRepository votingAgendaRepository;
    final AssociateService associateService;
    final VoteService voteService;

    public URI save(ResquestVotingAgendaCreateDTO requestVotingAgendaCreateDTO) {
        VotingAgenda votingAgenda = Mapper.mapper().map(requestVotingAgendaCreateDTO, VotingAgenda.class);
        votingAgenda = votingAgendaRepository.save(votingAgenda);
        return URI.create(votingAgenda.getId().toString());
    }

    public VotingAgenda openVote(Long id, ResquestOpenVoteDTO requestOpenVote) {
        VotingAgenda votingAgenda = findByIdAndVotingNotOpened(id);
        setTimes(votingAgenda, requestOpenVote);
        votingAgendaRepository.save(votingAgenda);

        return votingAgenda;
    }

    public Vote vote(Long id, ResquestVoteDTO requestVote) {
        Associate associate = associateService.findById(requestVote.getAssociateId());
        VotingAgenda votingAgenda = findByIdAndVotingOpened(id);
        voteService.existsVoteByAssociateAndVotingAgenda(associate, votingAgenda);

        Vote vote = new Vote();
        vote.setVote(requestVote.isVote());
        vote.setAssociate(associate);
        vote.setVotingAgenda(votingAgenda);

        return voteService.save(vote);
    }

    public ResponseVotingEndedDTO votingResult(Long id) {
        return voteService.votingResult(id);
    }

    public VotingAgenda findByIdAndVotingOpened(Long id) {
        LocalDateTime now = LocalDateTime.now();
        return votingAgendaRepository.findByIdAndOpeningDateIsNotNullAndClosingDateGreaterThan(id, now)
                .orElseThrow(() -> new BadRequestException(
                        "Not found or voting is closed"));
    }

    public VotingAgenda findByIdAndVotingNotOpened(Long id) {
        return votingAgendaRepository.findByIdAndOpeningDateIsNull(id)
                .orElseThrow(() -> new BadRequestException(
                        "Not found or already opened"));
    }

    private VotingAgenda setTimes(VotingAgenda votingAgenda, ResquestOpenVoteDTO requestOpenVote) {
        LocalDateTime now = LocalDateTime.now();

        votingAgenda.setOpeningDate(now);

        if (requestOpenVote.getClosingDate() == null) {
            votingAgenda.setClosingDate(now.plusMinutes(1));
        } else {
            votingAgenda.setClosingDate(requestOpenVote.getClosingDate());
        }

        return votingAgenda;
    }

    public List<VotingAgenda> list() {
        return votingAgendaRepository.findAll();
    }
}
