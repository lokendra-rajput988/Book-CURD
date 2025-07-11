package com.mindprove.app.annotations.validators;

import com.mindprove.app.annotations.ValidPrice;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<ValidPrice, Double> {

	@Override
	public boolean isValid(Double price, ConstraintValidatorContext context) {
		if (price == null || price <= 0)
			return false;

		return true;
	}

}
