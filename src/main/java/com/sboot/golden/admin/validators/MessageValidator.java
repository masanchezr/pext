package com.sboot.golden.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sboot.golden.dbaccess.entities.MessageEntity;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;

public class MessageValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return MessageEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DATEFROM, ConstantsViews.ERRORSELECTDATE);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DATEUNTIL, ConstantsViews.ERRORSELECTDATE);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "smessage", ConstantsViews.ERRORSELECTDESCRIPTION);
	}

}
