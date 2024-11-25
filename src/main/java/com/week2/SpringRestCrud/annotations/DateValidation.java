package com.week2.SpringRestCrud.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {DateValidator.class})
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValidation {
    String message() default "date pattern should be DD-MM-YYYY only...";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
