package br.com.southsystem.test.service;

import br.com.southsystem.test.dto.ResponseValidateAssociateDTO;
import br.com.southsystem.test.exception.BadRequestException;
import br.com.southsystem.test.model.Associate;
import br.com.southsystem.test.repository.AssociateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class AssociateService {
    final AssociateRepository associateRepository;

    AssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    @Value("${url.api}")
    private String baseUrl;

    public List<Associate> list() {
        return associateRepository.findAll();
    }

    public URI save(Associate associate) {
        validatedCpf(associate.getCpf());
        associate = associateRepository.save(associate);
        return URI.create(associate.getId().toString());
    }

    private void validatedCpf(String cpf) {
        try {
            String uri = "users/" + cpf;
            String url = baseUrl + uri;

            ResponseValidateAssociateDTO responseValidateAssociateDTO = new RestTemplate().getForObject(url,
                    ResponseValidateAssociateDTO.class);

            if (responseValidateAssociateDTO.getStatus().equals(Associate.UNABLE_TO_VOTE)) {
                throw new BadRequestException("Cpf unable to vote");
            }
            return;
        } catch (HttpStatusCodeException exception) {
            int statusCode = exception.getStatusCode().value();

            if (statusCode == HttpStatus.NOT_FOUND.value())
                throw new BadRequestException("Cpf not found");
        }
    }

    public Associate findById(Long id) {
        return associateRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(
                        "Not found associate"));
    }
}
