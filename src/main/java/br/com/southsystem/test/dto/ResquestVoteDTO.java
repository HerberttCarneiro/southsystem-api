package br.com.southsystem.test.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResquestVoteDTO {
    @NotNull
    private Long associateId;

    @NotNull
    private boolean vote;
}
