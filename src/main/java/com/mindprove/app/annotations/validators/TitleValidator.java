package com.mindprove.app.annotations.validators;

import com.mindprove.app.annotations.ValidTitle;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TitleValidator implements ConstraintValidator<ValidTitle, String> {

	@Override
	public boolean isValid(String title, ConstraintValidatorContext context) {
		if(title==null || title.trim().isEmpty())
			return false;
		return true;
	}

}
