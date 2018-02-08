package com.gu.boss.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gu.services.users.User;
import com.gu.util.constants.Constants;

public class UserValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "username", "selectuser");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.NAME, "selectname");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "alias", "selectalias");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "password", "selectpassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "role", "selectrole");
	}

}
