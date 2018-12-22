package com.example.demo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.example.demo.util.Util;
import com.example.demo.validator.CheckJson.CheckJsonValidator;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckJsonValidator.class)
@Documented
public @interface CheckJson {

	String message() default "";

	Class<?> ofType() default Object.class;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean isCollection();

	public class CheckJsonValidator implements ConstraintValidator<CheckJson, String> {

		private CheckJson checkJson;

		@Override
		public void initialize(CheckJson checkJson) {
			// TODO Auto-generated method stub
			this.checkJson = checkJson;
		}

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			// TODO Auto-generated method stub
			if (value == null) {
				return true;
			}
			// 如果是集合
			if (checkJson.isCollection()) {
				return Util.toObject(value, new JsonListTypeReference()) == null ? false : true;
			} else {
				return Util.toObject(value, checkJson.ofType()) == null ? false : true;
			}

		}

	}
}
