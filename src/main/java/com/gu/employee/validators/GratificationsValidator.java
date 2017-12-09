package com.gu.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.util.string.Util;

public class GratificationsValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return GratificationEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		GratificationEntity g = (GratificationEntity) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "client", "client");
		String client = g.getClient();
		if (Util.isNumeric(client)) {
			arg1.rejectValue("client", "notclient");
		}
	}

}
