package com.atmj.gsboot.admin.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.services.MetadataService;
import com.atmj.gsboot.dbaccess.entities.FicticionalRegisterEntity;
import com.atmj.gsboot.forms.SearchByDatesForm;
import com.atmj.gsboot.services.register.FicticionalRegisterService;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.util.date.DateUtil;

@Controller
public class RegisterAdminController {

	@Autowired
	private FicticionalRegisterService ficticionalRegisterService;

	@Autowired
	private MetadataService metadataservice;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(RegisterAdminController.class);

	@GetMapping("/admin/searchregister")
	public ModelAndView searchregister() {
		ModelAndView model = new ModelAndView("admin/registers/searchregisters");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@PostMapping("/admin/register")
	public ModelAndView register(@Valid SearchByDatesForm sdf, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		String sfrom = sdf.getDatefrom();
		String suntil = sdf.getDateuntil();
		if (arg1.hasErrors()) {
			model = new ModelAndView();
			model.addObject(ConstantsViews.FORMSEARCH, sdf);
			model.setViewName("admin/registers/searchregisters");
			return model;
		} else {
			model.addObject("registers", ficticionalRegisterService.findByDates(sdf.getDatefrom(), sdf.getDateuntil()));
			model.setViewName("admin/registers/register");
			model.addObject("datefrom", DateUtil.getStringDateFormatddMMyyyy(DateUtil.getDate(sfrom)));
			model.addObject("dateuntil", DateUtil.getStringDateFormatddMMyyyy(DateUtil.getDate(suntil)));
		}
		return model;
	}

	@GetMapping("/admin/downloadpdf{datefrom}/{dateuntil}")
	public ModelAndView downloadpdf(@PathVariable("datefrom") String from, @PathVariable("dateuntil") String until,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("admin/register/register");
		String path = metadataservice.getValue("datadir");
		File file = new File(path.concat("register.pdf"));
		List<FicticionalRegisterEntity> register = ficticionalRegisterService.findByDates(from, until);
		ficticionalRegisterService.generatePdf(register, file);
		model.addObject("register", register);
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment; filename=register.pdf");
		try (InputStream inputStream = new FileInputStream(file)) {
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
		}
		return model;
	}

}
