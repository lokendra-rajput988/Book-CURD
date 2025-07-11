package com.mindprove.app.annotations.validators;

import com.mindprove.app.annotations.ValidAuthor;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuthorValidator implements ConstraintValidator<ValidAuthor, String> {

	@Override
	public boolean isValid(String author, ConstraintValidatorContext context) {
		if(author==null || author.trim().isEmpty())
			return false;
		return true;
	}

}
