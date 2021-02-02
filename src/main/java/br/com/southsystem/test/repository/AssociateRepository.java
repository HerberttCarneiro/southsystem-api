package br.com.southsystem.test.repository;

import br.com.southsystem.test.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociateRepository extends JpaRepository<Associate, Long> {
}
