package com.mindprove.app.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mindprove.app.annotations.validators.YearValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = YearValidator.class)
public @interface ValidYear {
	
	 String message() default "Invalid year";

	    Class<?>[] groups() default {};

	    Class<? extends Payload>[] payload() default {};

}
