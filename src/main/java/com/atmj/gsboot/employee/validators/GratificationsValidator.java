package com.atmj.gsboot.employee.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atmj.gsboot.employee.forms.Gratification;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.util.string.Util;

@Component
public class GratificationsValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Gratification.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		Gratification g = (Gratification) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.CLIENT, ConstantsViews.CLIENT);
		String client = g.getClient();
		if (Util.isNumeric(client)) {
			arg1.rejectValue(ConstantsViews.CLIENT, "notclient");
		}
	}

}
