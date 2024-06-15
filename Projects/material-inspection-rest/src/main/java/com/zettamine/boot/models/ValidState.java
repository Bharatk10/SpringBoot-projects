package com.zettamine.boot.models;




import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zettamine.boot.validators.StateValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StateValidator.class)
public @interface ValidState {
    String message() default "Invalid state";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
