package com.atmj.gsboot.boss.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.forms.SearchByDatesForm;
import com.atmj.gsboot.services.openmachines.OpenMachineService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.util.date.DateUtil;
import com.atmj.gsboot.util.string.Util;

@Controller
public class OpenMachinesController {

	@Autowired
	private OpenMachineService openmachineservice;

	@GetMapping("/searchopenmachines")
	public ModelAndView searchopenmachines() {
		ModelAndView model = new ModelAndView("boss/openmachines/search");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@PostMapping("/openmachines")
	public ModelAndView openmachines(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView("boss/openmachines/openmachines");
		String sdate = searchForm.getDatefrom();
		Date date = new DateUtil().getNow();
		if (!Util.isEmpty(sdate)) {
			date = DateUtil.getDate(sdate);
		}
		model.addObject(Constants.OPENMACHINES, openmachineservice.getOpenMachines(date));
		return model;
	}
}
