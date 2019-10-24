package com.gu.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gu.forms.Incident;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;

public class IncidentValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Incident.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DESCRIPTION, ConstantsJsp.ERRORSELECTDESCRIPTION);
	}

}
