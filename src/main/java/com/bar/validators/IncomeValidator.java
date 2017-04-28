package com.bar.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bar.dbaccess.entities.IncomeEntity;

public class IncomeValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return IncomeEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		IncomeEntity income = (IncomeEntity) arg0;
		BigDecimal amount = income.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue("amount", "selectamount");
		}

	}

}
