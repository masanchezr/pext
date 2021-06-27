package com.sboot.golden.employee.controllers;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.dbaccess.entities.IncomeLuckiaEntity;
import com.sboot.golden.employee.forms.IncomeLuckia;
import com.sboot.golden.services.dailies.DailyService;
import com.sboot.golden.services.incomeluckia.IncomeLuckiaService;
import com.sboot.golden.util.constants.ConstantsViews;

@Controller
public class IncomeLuckiaController {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private IncomeLuckiaService incomeLuckiaService;

	@Autowired
	private Mapper mapper;

	private static final String FORMLUCKIA = "iluckia";

	@GetMapping("/employee/newincomeluckia")
	public ModelAndView newincomeluckia() {
		ModelAndView model = new ModelAndView("employee/income/newincomeluckia");
		model.addObject(FORMLUCKIA, new IncomeLuckia());
		return model;
	}

	@PostMapping("/employee/saveincomeluckia")
	public ModelAndView saveincomeluckia(@ModelAttribute(FORMLUCKIA) IncomeLuckia iluckia) {
		ModelAndView model = new ModelAndView();
		incomeLuckiaService.save(mapper.map(iluckia, IncomeLuckiaEntity.class));
		model.addObject(ConstantsViews.DAILY, dailyService.getDailyEmployee());
		model.setViewName("employee/daily/daily");
		return model;
	}
}
