package com.testcase.test_case_management_system.CustomAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "Invalid value. Must be one of the predefined enum values.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
