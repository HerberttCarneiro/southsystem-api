package br.com.southsystem.test.dto;

import br.com.southsystem.test.validation.EndDateLessThanStartDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResquestOpenVoteDTO {
    @EndDateLessThanStartDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime closingDate;
}
