package com.sboot.golden.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sboot.golden.util.constants.ConstantsViews;

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
