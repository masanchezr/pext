package com.gu.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gu.employee.forms.Gratification;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.string.Util;

public class GratificationsValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Gratification.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		Gratification g = (Gratification) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.CLIENT, ConstantsJsp.CLIENT);
		String client = g.getClient();
		if (Util.isNumeric(client)) {
			arg1.rejectValue(ConstantsJsp.CLIENT, "notclient");
		}
	}

}
