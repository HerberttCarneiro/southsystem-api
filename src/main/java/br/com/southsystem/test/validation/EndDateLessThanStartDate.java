package br.com.southsystem.test.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndDateLessThanStartDateValidator.class)
public @interface EndDateLessThanStartDate {
    String message() default "Closing date less than opening date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
