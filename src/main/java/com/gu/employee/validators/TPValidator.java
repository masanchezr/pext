package com.gu.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gu.dbaccess.entities.TPVEntity;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;

public class TPValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return TPVEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.IDTPV, ConstantsJsp.ERRORSELECTID);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
	}

}
