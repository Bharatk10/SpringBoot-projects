package com.zettamine.boot.models;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zettamine.boot.validators.MaterialTypeValidator;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaterialTypeValidator.class)
public @interface ValidMaterialType {
    String message() default "Invalid Material Type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
