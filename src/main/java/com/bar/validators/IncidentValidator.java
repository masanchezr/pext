package com.bar.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bar.dbaccess.entities.IncidentEntity;

public class IncidentValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return IncidentEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description", "selectdescription");
	}

}
