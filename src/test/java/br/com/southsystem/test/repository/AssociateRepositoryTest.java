package br.com.southsystem.test.repository;

import br.com.southsystem.test.model.Associate;
import br.com.southsystem.test.util.AssociateCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@DataJpaTest
class AssociateRepositoryTest {
    @Autowired
    private AssociateRepository associateRepository;

    @Test
    void savePersistAssociateWhenSuccessful() {
        Associate associateToBeSaved = AssociateCreator.createAssociateToBeSaved();
        Associate associateSaved = associateRepository.save(associateToBeSaved);

        Assertions.assertThat(associateSaved).isNotNull();
        Assertions.assertThat(associateSaved.getId()).isNotNull();
        Assertions.assertThat(associateSaved.getCpf()).isEqualTo(associateToBeSaved.getCpf());
    }

    @Test
    void saveThrowsConstraintViolationExceptionWhenCpfIsEmpty() {
        Associate associate = new Associate();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> associateRepository.save(associate))
                .withMessageContaining("must not be empty");
    }

    @Test
    void findByIdReturnEmptyWhenAssociateNotFound() {
        Optional<Associate> associateOptional = associateRepository.findById(123L);

        Assertions.assertThat(associateOptional).isEmpty();
    }

    @Test
    void findByIdReturnAssociateWhenSuccessful() {
        Associate associateToBeSaved = AssociateCreator.createAssociateToBeSaved();
        Associate associateSaved = associateRepository.save(associateToBeSaved);

        Optional<Associate> associateOptional = associateRepository.findById(associateSaved.getId());

        Assertions.assertThat(associateOptional).isNotEmpty();
        Assertions.assertThat(associateOptional.get().getId()).isEqualTo(associateSaved.getId());
    }
}
