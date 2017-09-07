package com.gu.admin.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gu.dbaccess.entities.ProvidingEntity;

public class ProvidingValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return ProvidingEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ProvidingEntity providing = (ProvidingEntity) arg0;
		BigDecimal amount = providing.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue("amount", "selectamount");
		}
	}

}
