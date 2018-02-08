package com.gu.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gu.dbaccess.entities.MessageEntity;
import com.gu.util.constants.ConstantsJsp;

public class MessageValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return MessageEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.DATEFROM, ConstantsJsp.SELECTDATE);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.DATEUNTIL, ConstantsJsp.SELECTDATE);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.MESSAGE, ConstantsJsp.ERRORSELECTDESCRIPTION);
	}

}
