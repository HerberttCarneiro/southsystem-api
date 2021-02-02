package br.com.southsystem.test.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Associate {
    public static String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";
    public static String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private String cpf;
}
