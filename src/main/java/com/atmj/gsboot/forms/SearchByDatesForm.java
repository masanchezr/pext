package com.atmj.gsboot.forms;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.atmj.gsboot.util.constants.ConstantsViews;

public class SearchByDatesForm {

	@NotNull
	@NotEmpty(message = ConstantsViews.ERRORSELECTDATE)
	private String datefrom;

	@NotNull
	@NotEmpty(message = ConstantsViews.ERRORSELECTDATE)
	private String dateuntil;

	public String getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	public String getDateuntil() {
		return dateuntil;
	}

	public void setDateuntil(String dateuntil) {
		this.dateuntil = dateuntil;
	}

}
