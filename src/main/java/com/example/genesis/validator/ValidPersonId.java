package com.example.genesis.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ValidPersonIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPersonId {
    String message() default "Invalid Person ID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
