package com.zettamine.boot.validators;

import com.zettamine.boot.models.MaterialType;
import com.zettamine.boot.models.ValidMaterialType;
import com.zettamine.boot.utility.StringUtilis;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MaterialTypeValidator implements ConstraintValidator<ValidMaterialType, String> {

    @Override
    public void initialize(ValidMaterialType constraintAnnotation) {
        // Not needed for initialization
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	
    	value = StringUtilis.processSentance(value);
        for (MaterialType materialType : MaterialType.getAllMaterialTypes()) {
            if (materialType.getMaterialType().equalsIgnoreCase(value)) {
                return true; 
            }
        }
        return false; 
    }
}

