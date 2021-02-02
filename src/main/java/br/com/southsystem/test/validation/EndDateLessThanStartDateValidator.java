package br.com.southsystem.test.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class EndDateLessThanStartDateValidator implements ConstraintValidator<EndDateLessThanStartDate, LocalDateTime> {

    public void initialize(EndDateLessThanStartDate constraint) {
    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        return LocalDateTime.now().isBefore(localDateTime);
    }
}
