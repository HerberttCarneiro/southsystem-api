package br.com.southsystem.test.resource;

import br.com.southsystem.test.dto.ResponseVotingEndedDTO;
import br.com.southsystem.test.dto.ResquestOpenVoteDTO;
import br.com.southsystem.test.dto.ResquestVoteDTO;
import br.com.southsystem.test.model.Vote;
import br.com.southsystem.test.model.VotingAgenda;
import br.com.southsystem.test.service.VotingAgendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "voting-agenda")
@AllArgsConstructor
public class VotingAgendaResource {
    final VotingAgendaService votingAgendaService;

    @PostMapping()
    public ResponseEntity<URI> save(@RequestBody @Valid VotingAgenda votingAgenda) {
        URI uri = votingAgendaService.save(votingAgenda);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}/open-vote")
    public ResponseEntity<VotingAgenda> openVote(
            @PathVariable("id") Long id,
            @RequestBody @Valid ResquestOpenVoteDTO requestOpenVote
    ) {
        VotingAgenda votingAgenda = votingAgendaService.openVote(id, requestOpenVote);
        return ResponseEntity.ok(votingAgenda);
    }

    @PutMapping("/{id}/vote")
    public ResponseEntity<Vote> vote(
            @PathVariable("id") Long id,
            @RequestBody @Valid ResquestVoteDTO resquestVoteDTO
    ) {
        Vote vote = votingAgendaService.vote(id, resquestVoteDTO);
        return ResponseEntity.ok(vote);
    }

    @GetMapping("/{id}/voting-result")
    public ResponseEntity<ResponseVotingEndedDTO> votingResult(@PathVariable("id") Long id) {
        ResponseVotingEndedDTO votingResult = votingAgendaService.votingResult(id);
        return ResponseEntity.ok(votingResult);
    }
}
