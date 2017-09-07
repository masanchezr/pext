package com.gu.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gu.forms.SearchByDatesForm;
import com.gu.util.date.DateUtil;

public class SearchDatesFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return SearchByDatesForm.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		SearchByDatesForm dailyForm = (SearchByDatesForm) arg0;
		Date date = DateUtil.getDate(dailyForm.getDatefrom());
		if (date == null && new Date().before(date)) {
			arg1.rejectValue("date", "datecannotbegreater");
		}
	}

}
