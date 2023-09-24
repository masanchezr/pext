package com.atmj.gsboot.validators;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.atmj.gsboot.forms.SearchByDatesForm;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.date.DateUtil;

@Component
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
