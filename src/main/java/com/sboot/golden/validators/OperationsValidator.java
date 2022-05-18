package com.sboot.golden.validators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sboot.golden.forms.Operation;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;

@Component
public class OperationsValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Operation.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		Operation operation = (Operation) arg0;
		BigDecimal amount = operation.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		}
		if (operation.getAward().getIdaward().compareTo(Constants.OTHERAWARDS) == 0
				|| (operation.getAward().getIdaward().compareTo(Constants.EATANDDRINKS) == 0)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DESCRIPTION,
					ConstantsViews.ERRORSELECTDESCRIPTION);
		}
	}

}
