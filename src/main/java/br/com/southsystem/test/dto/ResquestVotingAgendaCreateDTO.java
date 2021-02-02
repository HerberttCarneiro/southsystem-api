package br.com.southsystem.test.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ResquestVotingAgendaCreateDTO {
    @NotEmpty
    private String description;
}
