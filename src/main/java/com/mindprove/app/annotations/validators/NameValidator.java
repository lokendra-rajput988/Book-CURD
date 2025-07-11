package com.mindprove.app.annotations.validators;

import com.mindprove.app.annotations.ValidName;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if(name==null || name.trim().isEmpty())
		return false;
		return true;
	}
	

}
