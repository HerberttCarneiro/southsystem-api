package br.com.southsystem.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseVotingResultDetailsDTO {
    private Long numberOfVotes;

    private boolean result;
}
