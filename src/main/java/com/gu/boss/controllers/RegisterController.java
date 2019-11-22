package com.gu.boss.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.RegisterEntity;
import com.gu.forms.SearchByDatesForm;
import com.gu.services.register.RegisterService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsViews;
import com.gu.util.date.DateUtil;
import com.gu.validators.SearchDatesFormValidator;

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private SearchDatesFormValidator searchDatesFormValidator;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@GetMapping("/register")
	public ModelAndView register(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchByDatesForm sdf, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		String sfrom = sdf.getDatefrom();
		String suntil = sdf.getDateuntil();
		searchDatesFormValidator.validate(sdf, arg1);
		if (arg1.hasErrors()) {
			model = new ModelAndView();
			model.addObject(ConstantsViews.FORMSEARCH, sdf);
			model.setViewName(ConstantsViews.VIEWSEARCHREGISTER);
			return model;
		} else {
			model.addObject("registers", registerService.findByDates(sfrom, suntil));
			model.setViewName(ConstantsViews.VIEWREGISTER);
			model.addObject("datefrom", DateUtil.getStringDateFormatddMMyyyy(DateUtil.getDate(sfrom)));
			model.addObject("dateuntil", DateUtil.getStringDateFormatddMMyyyy(DateUtil.getDate(suntil)));
		}
		return model;
	}

	@GetMapping("/searchregister")
	public ModelAndView searchregister() {
		ModelAndView model = new ModelAndView(ConstantsViews.VIEWSEARCHREGISTER);
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@GetMapping("/downloadpdf{datefrom}/{dateuntil}")
	public ModelAndView downloadpdf(@PathVariable("datefrom") String from, @PathVariable("dateuntil") String until,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("admin/register/register");
		String path = System.getenv(Constants.OPENSHIFT_DATA_DIR);
		File file = new File(path.concat("register.pdf"));
		List<RegisterEntity> register = registerService.findByDates(from, until);
		registerService.generatePdf(register, file);
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
