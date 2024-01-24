package com.atmj.gsboot.employee.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.dbaccess.entities.IncomeSportsBetsEntity;
import com.atmj.gsboot.employee.forms.IncomeSportsBets;
import com.atmj.gsboot.services.dailies.DailyService;
import com.atmj.gsboot.services.incomesportsbets.IncomeSportsBetsService;
import com.atmj.gsboot.util.constants.ConstantsViews;

@Controller
public class IncomeSportsBetsController {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private IncomeSportsBetsService incomeSportsBetsService;

	@Autowired
	private ModelMapper mapper;

	private static final String FORMSPORTSBETS = "isportsbets";

	@GetMapping("/employee/newincomesportsbets")
	public ModelAndView newincomesportsbets() {
		ModelAndView model = new ModelAndView("employee/income/newincomesportsbets");
		model.addObject(FORMSPORTSBETS, new IncomeSportsBets());
		return model;
	}

	@PostMapping("/employee/saveincomesportsbets")
	public ModelAndView saveincomesportsbets(@ModelAttribute(FORMSPORTSBETS) IncomeSportsBets isportsbets) {
		ModelAndView model = new ModelAndView();
		incomeSportsBetsService.save(mapper.map(isportsbets, IncomeSportsBetsEntity.class));
		model.addObject(ConstantsViews.DAILY, dailyService.getDailyEmployee());
		model.setViewName("employee/daily/daily");
		return model;
	}
}
