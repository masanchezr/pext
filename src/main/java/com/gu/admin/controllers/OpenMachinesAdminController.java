package com.gu.admin.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.openmachines.OpenMachineService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;
import com.gu.util.string.Util;

@Controller
public class OpenMachinesAdminController {

	@Autowired
	private OpenMachineService openmachineservice;

	@RequestMapping("/searchopenmachines")
	public ModelAndView searchopenmachines() {
		ModelAndView model = new ModelAndView("searchopenmachines");
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@RequestMapping("/openmachines")
	public ModelAndView openmachines(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView("openmachinesadmin");
		String sdate = searchForm.getDatefrom();
		Date date = new Date();
		if (!Util.isEmpty(sdate)) {
			date = DateUtil.getDate(sdate);
		}
		model.addObject(Constants.OPENMACHINES, openmachineservice.getOpenMachines(date));
		return model;
	}
}
