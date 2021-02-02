package br.com.southsystem.test.resource;

import br.com.southsystem.test.model.Associate;
import br.com.southsystem.test.service.AssociateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "associate")
@AllArgsConstructor
public class AssociateResource {
    final AssociateService associateService;

    @PostMapping()
    public ResponseEntity<URI> save(@RequestBody @Valid Associate associate) {
        URI uri = associateService.save(associate);
        return ResponseEntity.created(uri).build();
    }
}
