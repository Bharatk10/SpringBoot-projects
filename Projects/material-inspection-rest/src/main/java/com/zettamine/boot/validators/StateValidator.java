package com.zettamine.boot.validators;

import com.zettamine.boot.models.State;
import com.zettamine.boot.models.ValidState;
import com.zettamine.boot.utility.StringUtilis;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidator implements ConstraintValidator<ValidState, String> {

    @Override
    public void initialize(ValidState constraintAnnotation) {
      
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	
  
    	
    	value = StringUtilis.processSentance(value);
        for (State stateEnum : State.getAllStates()) {
            if (stateEnum.getStateName().equalsIgnoreCase(value)) {
                return true; 
            }
        }
        return false; 
    }
}

