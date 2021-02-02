package br.com.southsystem.test.service;

import br.com.southsystem.test.dto.ResponseVotingEndedDTO;
import br.com.southsystem.test.dto.ResponseVotingResultDetailsDTO;
import br.com.southsystem.test.exception.BadRequestException;
import br.com.southsystem.test.model.Associate;
import br.com.southsystem.test.model.Vote;
import br.com.southsystem.test.model.VotingAgenda;
import br.com.southsystem.test.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoteService {
    final VoteRepository voteRepository;

    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    public void existsVoteByAssociateAndVotingAgenda(Associate associate, VotingAgenda votingAgenda) {
        boolean alreadyVoted = voteRepository.existsVoteByAssociateAndVotingAgenda(associate, votingAgenda);

        if (alreadyVoted) {
            throw new BadRequestException("User has already voted");
        }
    }

    public ResponseVotingEndedDTO votingResult(Long id) {
        Long agreeingVote = 0L;
        Long disagreeVote = 0L;

        List<ResponseVotingResultDetailsDTO> responseVotingResultDetailsDTOList = voteRepository.votingResult(id);

        for (ResponseVotingResultDetailsDTO voting : responseVotingResultDetailsDTOList) {
            if (voting.isResult()) {
                agreeingVote = voting.getNumberOfVotes();
            } else {
                disagreeVote = voting.getNumberOfVotes();
            }
        }

        ResponseVotingEndedDTO votingEndedDTO = new ResponseVotingEndedDTO();
        votingEndedDTO.setVotingResultDetails(responseVotingResultDetailsDTOList);

        votingEndedDTO.setVotingResult(Vote.DISAPPROVED);

        if (agreeingVote.compareTo(disagreeVote) == 1) {
            votingEndedDTO.setVotingResult(Vote.APPROVED);
        }

        return votingEndedDTO;
    }
}
