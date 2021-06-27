package com.sboot.golden.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sboot.golden.dbaccess.entities.ChangeMachineEntity;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;

public class ChangeMachineValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return ChangeMachineEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.IDCHANGEMACHINE, ConstantsViews.ERRORSELECTID);
		ChangeMachineEntity cm = (ChangeMachineEntity) arg0;
		BigDecimal amount = cm.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		}
	}

}
