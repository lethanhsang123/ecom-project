package com.personal.project.common.validator.custom.impl;

import com.personal.project.common.validator.custom.CustomMethodValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomMethodValidator implements
        ConstraintValidator<CustomMethodValidation, String> {

    @Override
    public void initialize(CustomMethodValidation contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[0-9]+");
    }

}
