package com.atmj.gsboot.forms;

import com.atmj.gsboot.util.constants.ConstantsViews;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SearchByDatesForm {

	@NotNull(message = "{" + ConstantsViews.ERRORSELECTDATE + "}")
	@NotEmpty(message = "{" + ConstantsViews.ERRORSELECTDATE + "}")
	private String datefrom;

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
