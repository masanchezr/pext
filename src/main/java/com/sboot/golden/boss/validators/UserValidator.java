package com.sboot.golden.boss.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sboot.golden.services.users.User;
import com.sboot.golden.util.constants.Constants;

public class UserValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.USERNAME, "selectuser");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.CONTRASE, "selectpassword");
	}
}