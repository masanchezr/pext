package com.sboot.golden.boss.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sboot.golden.dbaccess.entities.TPVEntity;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;

public class TPValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return TPVEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.IDTPV, ConstantsViews.ERRORSELECTID);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.CREATIONDATE, ConstantsViews.ERRORSELECTDATE);
	}

}
