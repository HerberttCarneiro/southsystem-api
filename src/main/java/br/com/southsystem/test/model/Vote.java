package br.com.southsystem.test.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Vote {
    public static String APPROVED = "APPROVED";
    public static String DISAPPROVED = "DISAPPROVED";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private boolean vote;

    @NotNull
    @OneToOne
    Associate associate;

    @NotNull
    @OneToOne
    VotingAgenda votingAgenda;
}
