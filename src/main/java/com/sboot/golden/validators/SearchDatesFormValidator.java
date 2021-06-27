package com.sboot.golden.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sboot.golden.forms.SearchByDatesForm;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.date.DateUtil;

public class SearchDatesFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return SearchByDatesForm.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		SearchByDatesForm dailyForm = (SearchByDatesForm) arg0;
		Date date = DateUtil.getDate(dailyForm.getDatefrom());
		if (date == null || new DateUtil().getNow().before(date)) {
			arg1.rejectValue(Constants.DATEFROM, "datecannotbegreater");
		}
	}

}
