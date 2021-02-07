package br.com.southsystem.test.service;

import br.com.southsystem.test.exception.BadRequestException;
import br.com.southsystem.test.model.Associate;
import br.com.southsystem.test.repository.AssociateRepository;
import br.com.southsystem.test.util.AssociateCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
class AssociateServiceTest {
    @InjectMocks
    AssociateService associateService;

    @Mock
    private AssociateRepository associateRepositoryMock;

    @BeforeEach
    void setUp() {
        List<Associate> associates = Arrays.asList(AssociateCreator.createValidAssociate());

        BDDMockito.when(associateRepositoryMock.findAll())
                .thenReturn(associates);

        BDDMockito.when(associateRepositoryMock.save(ArgumentMatchers.any(Associate.class)))
                .thenReturn(AssociateCreator.createValidAssociate());
    }

    @Test
    void list_ReturnListOfAssociates_WhenSuccessful() {
        List<Associate> associates = associateService.list();
        Associate associate = AssociateCreator.createValidAssociate();

        Assertions.assertThat(associates).isNotNull();
        Assertions.assertThat(associates.get(0).getCpf()).isEqualTo(associate.getCpf());
    }

    @Test
    void save_ThrowsConstraintViolationException_WhenCpfIsEmpty() {
        Associate associate = new Associate();

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> associateService.save(associate))
                .withMessageContaining("CPF ERROR -");
    }

    @Test
    void savePersistAssociateWhenCpfIsAbleToVote() {
        try {
            Associate associateToBeSaved = AssociateCreator.createAssociateToBeSaved();

            Associate associateSaved = associateService.save(associateToBeSaved);

            Assertions.assertThat(associateSaved.getId()).isNotNull();
            Assertions.assertThat(associateSaved.getCpf()).isEqualTo(associateToBeSaved.getCpf());
        } catch (BadRequestException exception) {
            Assertions.assertThat(exception).hasMessageContaining("CPF ERROR -");
        }
    }
}
