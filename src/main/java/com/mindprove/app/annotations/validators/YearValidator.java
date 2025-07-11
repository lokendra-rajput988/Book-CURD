package com.mindprove.app.annotations.validators;

import java.time.Year;

import com.mindprove.app.annotations.ValidYear;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class YearValidator implements ConstraintValidator<ValidYear, Integer> {

	@Override
	public boolean isValid(Integer year, ConstraintValidatorContext context) {
		if(year==null )
		return false;
		int currentYear = Year.now().getValue();
        return year >= 1500 && year <= currentYear;
	}

}
