package com.gu.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gu.dbaccess.entities.MessageEntity;

public class MessageValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return MessageEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "datefrom", "selectdate");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "dateuntil", "selectdate");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "message", "selectdescription");
	}

}
