package com.testcase.test_case_management_system.CustomAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, Enum<?>> {

    private Set<String> allowedValues;

    @Override
    public void initialize(ValueOfEnum constraintAnnotation) {
        allowedValues = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;  // Let @NotEmpty or @NotNull handle nulls
        }
        return allowedValues.contains(value.name());
    }
}
