package br.com.southsystem.test.repository;

import br.com.southsystem.test.model.VotingAgenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VotingAgendaRepository extends JpaRepository<VotingAgenda, Long> {
    Optional<VotingAgenda> findByIdAndOpeningDateIsNull(Long id);

    Optional<VotingAgenda> findByIdAndOpeningDateIsNotNullAndClosingDateGreaterThan(Long id, LocalDateTime now);

    Optional<VotingAgenda> findByIdAndOpeningDateIsNotNullAndClosingDateLessThan(Long id, LocalDateTime now);
}
