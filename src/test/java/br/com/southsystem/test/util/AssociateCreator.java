package br.com.southsystem.test.util;

import br.com.southsystem.test.model.Associate;

public class AssociateCreator {
    public static Associate createAssociateToBeSaved() {
        return Associate.builder()
                .cpf("85962396006")
                .name("Herbertt")
                .build();
    }

    public static Associate createInvalidAssociate() {
        return Associate.builder()
                .cpf(null)
                .name(null)
                .build();
    }

    public static Associate createValidAssociate() {
        return Associate.builder()
                .id(1L)
                .cpf("85962396006")
                .name("Herbertt")
                .build();
    }
}
