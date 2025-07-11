package com.mindprove.app.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mindprove.app.annotations.validators.AuthorValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = AuthorValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAuthor {
	

	  String message() default "Invalid author";

	    Class<?>[] groups() default {};

	    Class<? extends Payload>[] payload() default {};

}
