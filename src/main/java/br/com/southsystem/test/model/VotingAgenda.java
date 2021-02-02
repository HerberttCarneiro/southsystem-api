package br.com.southsystem.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Entity
public class VotingAgenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String description;

    @Column
    LocalDateTime openingDate;

    @Column
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    LocalDateTime closingDate;
}
