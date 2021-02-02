package br.com.southsystem.test.repository;

import br.com.southsystem.test.dto.ResponseVotingResultDetailsDTO;
import br.com.southsystem.test.model.Associate;
import br.com.southsystem.test.model.Vote;
import br.com.southsystem.test.model.VotingAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT " +
            "    new br.com.southsystem.test.dto.ResponseVotingResultDetailsDTO(COUNT(vote), vote.vote) " +
            "FROM " +
            "    Vote vote " +
            "WHERE vote.votingAgenda.id = :id " +
            "GROUP BY vote.vote")
    List<ResponseVotingResultDetailsDTO> votingResult(@Param("id") Long id);

    boolean existsVoteByAssociateAndVotingAgenda(Associate associate, VotingAgenda votingAgenda);
}
