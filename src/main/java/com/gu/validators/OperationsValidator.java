package com.gu.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gu.dbaccess.entities.OperationEntity;
import com.gu.util.constants.Constants;

public class OperationsValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return OperationEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		OperationEntity operation = (OperationEntity) arg0;
		BigDecimal amount = operation.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue("amount", "selectamount");
		}
		if (operation.getAward().getIdaward().equals(Constants.OTHERAWARDS)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description", "selectdescription");
		}
	}

}
