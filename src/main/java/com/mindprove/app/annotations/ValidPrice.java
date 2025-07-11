package com.mindprove.app.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mindprove.app.annotations.validators.PriceValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PriceValidator.class)
public @interface ValidPrice {

	  String message() default "Invalid price";

	    Class<?>[] groups() default {};

	    Class<? extends Payload>[] payload() default {};
}
