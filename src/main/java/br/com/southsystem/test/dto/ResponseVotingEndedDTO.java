package br.com.southsystem.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVotingEndedDTO {
    private List<ResponseVotingResultDetailsDTO> votingResultDetails;
    private String votingResult;
}
