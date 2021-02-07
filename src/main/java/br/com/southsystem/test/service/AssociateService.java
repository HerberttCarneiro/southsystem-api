package br.com.southsystem.test.service;

import br.com.southsystem.test.dto.ResponseValidateAssociateDTO;
import br.com.southsystem.test.exception.BadRequestException;
import br.com.southsystem.test.model.Associate;
import br.com.southsystem.test.repository.AssociateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AssociateService {
    private final String baseUrl = "https://user-info.herokuapp.com/users/";

    final AssociateRepository associateRepository;

    AssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public List<Associate> list() {
        return associateRepository.findAll();
    }

    public Associate save(Associate associate) {
        if (associate.getCpf() != null) {
            validatedCpf(associate);
            return associateRepository.save(associate);
        } else {
            throw new BadRequestException("CPF ERROR - is empty");
        }
    }

    private void validatedCpf(Associate associate) {
        try {
            String url = baseUrl.concat(associate.getCpf());

            ResponseValidateAssociateDTO response = new RestTemplate().getForObject(url, ResponseValidateAssociateDTO.class);

            if (response.getStatus().equals(Associate.UNABLE_TO_VOTE)) {
                throw new BadRequestException("CPF ERROR - unable to vote");
            }

            return;
        } catch (HttpStatusCodeException exception) {
            int statusCode = exception.getStatusCode().value();

            if (statusCode == HttpStatus.NOT_FOUND.value())
                throw new BadRequestException("CPF ERROR - not found");
        }
    }

    public Associate findById(Long id) {
        return associateRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(
                        "Not found associate"));
    }
}
