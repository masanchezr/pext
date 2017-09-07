package com.gu.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gu.admin.forms.EntryMoneyForm;
import com.gu.dbaccess.entities.EntryMoneyEntity;

public class EntryMoneyValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return EntryMoneyEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", "selectamount");
		EntryMoneyForm money = (EntryMoneyForm) arg0;
		BigDecimal amount = money.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue("amount", "selectamount");
		}
	}

}
