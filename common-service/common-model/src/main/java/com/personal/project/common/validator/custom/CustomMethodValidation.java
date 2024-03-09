package com.personal.project.common.validator.custom;

import com.personal.project.common.validator.custom.impl.CustomMethodValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomMethodValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomMethodValidation {
    String message() default "Invalid data";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
