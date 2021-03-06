package com.sboot.golden.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sboot.golden.employee.forms.ExtraHours;
import com.sboot.golden.util.constants.ConstantsViews;

public class ExtraHoursValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ExtraHours.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departuretime", "selectdeparturetime");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", ConstantsViews.ERRORSELECTDESCRIPTION);
	}

}
