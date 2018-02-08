package com.gu.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gu.dbaccess.entities.BarDrinkEntity;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;

public class IncomeValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return BarDrinkEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		BarDrinkEntity income = (BarDrinkEntity) arg0;
		BigDecimal amount = income.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
		}

	}

}
