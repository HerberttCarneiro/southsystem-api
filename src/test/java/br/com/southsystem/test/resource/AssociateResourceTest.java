package br.com.southsystem.test.resource;

import br.com.southsystem.test.exception.BadRequestException;
import br.com.southsystem.test.model.Associate;
import br.com.southsystem.test.service.AssociateService;
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
class AssociateResourceTest {
    @InjectMocks
    private AssociateResource associateResource;

    @Mock
    AssociateService associateServiceMock;

    @BeforeEach
    void setUp() {
        List<Associate> associates = Arrays.asList(AssociateCreator.createValidAssociate());

        BDDMockito.when(associateServiceMock.list())
                .thenReturn(associates);

        BDDMockito.when(associateServiceMock.save(ArgumentMatchers.any(Associate.class)))
                .thenReturn(AssociateCreator.createValidAssociate());
    }

    @Test
    void listReturnListOfAssociatesWhenSuccessful() {
        List<Associate> associates = associateResource.list().getBody();
        Associate associate = AssociateCreator.createValidAssociate();

        Assertions.assertThat(associates.get(0).getCpf()).isEqualTo(associate.getCpf());
        Assertions.assertThat(associates).isNotNull();
    }

    @Test
    void savePersistAssociateWhenCpfIsAbleToVote() {
        try {
            Associate associateToBeSaved = AssociateCreator.createAssociateToBeSaved();

            Associate associateSaved = associateResource.save(associateToBeSaved).getBody();

            Assertions.assertThat(associateSaved.getId()).isNotNull();
            Assertions.assertThat(associateSaved.getCpf()).isEqualTo(associateToBeSaved.getCpf());
        } catch (BadRequestException exception) {
            Assertions.assertThat(exception).hasMessageContaining("CPF ERROR -");
        }
    }
}
