package com.gu.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.IncomeLuckiaEntity;
import com.gu.services.incomeluckia.IncomeLuckiaService;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class IncomeLuckiaController {

	@Autowired
	private IncomeLuckiaService incomeLuckiaService;

	private static final String FORMLUCKIA = "iluckia";

	@RequestMapping(value = "/employee/newincomeluckia")
	public ModelAndView newincomeluckia() {
		ModelAndView model = new ModelAndView("incomeluckia");
		model.addObject(FORMLUCKIA, new IncomeLuckiaEntity());
		return model;
	}

	@RequestMapping(value = "/employee/saveincomeluckia")
	public ModelAndView saveincomeluckia(@ModelAttribute(FORMLUCKIA) IncomeLuckiaEntity iluckia) {
		ModelAndView model = new ModelAndView(ConstantsJsp.VIEWSUCCESSEMPLOYEE);
		model.addObject(ConstantsJsp.DAILY, incomeLuckiaService.save(iluckia));
		model.setViewName(ConstantsJsp.DAILY);
		return model;
	}
}
