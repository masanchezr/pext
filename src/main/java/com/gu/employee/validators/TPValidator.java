package com.gu.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gu.dbaccess.entities.TPVEntity;

public class TPValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return TPVEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "idtpv", "selectid");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", "selectamount");
	}

}
