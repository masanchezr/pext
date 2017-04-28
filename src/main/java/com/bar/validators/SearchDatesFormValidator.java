package com.bar.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bar.forms.SearchDatesForm;
import com.bar.util.date.DateUtil;

public class SearchDatesFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return SearchDatesForm.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		SearchDatesForm dailyForm = (SearchDatesForm) arg0;
		Date date = DateUtil.getDate(dailyForm.getDatefrom());
		if (date == null && new Date().before(date)) {
			arg1.rejectValue("date", "datecannotbegreater");
		}
	}

}
