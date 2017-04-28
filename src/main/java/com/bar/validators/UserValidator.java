package com.bar.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bar.services.users.User;

public class UserValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "username", "selectuser");
	}

}
