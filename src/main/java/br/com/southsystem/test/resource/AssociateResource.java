package br.com.southsystem.test.resource;

import br.com.southsystem.test.model.Associate;
import br.com.southsystem.test.service.AssociateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "associates")
@AllArgsConstructor
public class AssociateResource {
    final AssociateService associateService;

    @PostMapping()
    public ResponseEntity<URI> save(@RequestBody @Valid Associate associate) {
        URI uri = associateService.save(associate);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    public ResponseEntity<List<Associate>> list() {
        List<Associate>  associates= associateService.list();
        return ResponseEntity.ok(associates);
    }
}
