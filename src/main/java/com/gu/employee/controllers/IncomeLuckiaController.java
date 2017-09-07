package com.gu.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.IncomeLuckiaEntity;
import com.gu.services.incomeluckia.IncomeLuckiaService;

@Controller
public class IncomeLuckiaController {

	@Autowired
	private IncomeLuckiaService incomeLuckiaService;

	@RequestMapping(value = "/employee/newincomeluckia")
	public ModelAndView newincomeluckia() {
		ModelAndView model = new ModelAndView("incomeluckia");
		model.addObject("iluckia", new IncomeLuckiaEntity());
		return model;
	}

	@RequestMapping(value = "/employee/saveincomeluckia")
	public ModelAndView saveincomeluckia(@ModelAttribute("iluckia") IncomeLuckiaEntity iluckia) {
		ModelAndView model = new ModelAndView("successemployee");
		model.addObject("daily", incomeLuckiaService.save(iluckia));
		model.setViewName("daily");
		return model;
	}
}
