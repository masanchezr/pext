package com.gu.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gu.employee.forms.CheckboxesEmp;

public class InOutValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return CheckboxesEmp.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		CheckboxesEmp cb = (CheckboxesEmp) arg0;
		if (cb.getEmployees() == null || cb.getEmployees().isEmpty()) {
			arg1.rejectValue("employees", "selectemployees");
		}
	}
}