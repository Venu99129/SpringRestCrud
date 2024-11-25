package com.week2.SpringRestCrud.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class DateValidator implements ConstraintValidator<DateValidation , String> {
    @Override
    public boolean isValid(String inputDate, ConstraintValidatorContext constraintValidatorContext) {

        String regex = "^([0-2][0-9]|(3)[0-1])-(0[1-9]|1[0-2])-[0-9]{4}$";
        return Pattern.matches(regex,inputDate);
    }
}
