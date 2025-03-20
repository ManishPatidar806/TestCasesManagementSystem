package com.testcase.test_case_management_system.CustomAnnotation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {
    private Enum<?>[] enumValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        enumValues = annotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false; // Prevent null values
        return Arrays.stream(enumValues)
                .map(Enum::name)
                .anyMatch(enumName -> enumName.equals(value));
    }
}
