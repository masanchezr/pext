package com.gu.admin.controllers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.validators.ChangeMachineValidator;
import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.services.changemachine.ChangeMachineService;

@Controller
public class ChangeMachineController {

	@Autowired
	private ChangeMachineService changeMachineService;

	@Autowired
	private ChangeMachineValidator changeMachineValidator;

	@RequestMapping(value = "/admin/changemachinetotal")
	public ModelAndView changemachinetotal() {
		ModelAndView model = new ModelAndView("changemachinetotal");
		model.addObject("totalmonth", changeMachineService.getIncomeTotalMonth());
		model.addObject("total", changeMachineService.getTotal());
		model.addObject("awardsluckia", changeMachineService.getAwardsLuckia());
		model.addObject("awards", changeMachineService.getAwards());
		return model;
	}

	@RequestMapping(value = "/admin/luckiaAward")
	public ModelAndView luckiaAward() {
		ModelAndView model = new ModelAndView("luckia");
		model.addObject("cmachineForm", changeMachineService.getLastLuckia());
		return model;
	}

	@RequestMapping(value = "/admin/saveawardluckia")
	public ModelAndView saveAwardLuckia(@ModelAttribute("cmachineForm") ChangeMachineEntity cmachine,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("luckia");
			model.addObject("cmachineForm", cmachine);
		} else {
			changeMachineService.saveLuckiaAward(cmachine);
			model = changemachinetotal();
		}
		return model;
	}

	@InitBinder
	public void bigDecimalCustomBinder(WebDataBinder binder) {
		final DecimalFormat FORMATTER = (DecimalFormat) NumberFormat.getNumberInstance(new Locale("es"));
		binder.addValidators(changeMachineValidator);

		// Creation of a new binder for the type "BigDecimal"
		CustomNumberEditor cbinder = new CustomNumberEditor(BigDecimal.class, new NumberFormat() {

			private static final long serialVersionUID = 1L;

			@Override
			public Number parse(String source, ParsePosition parsePosition) {
				if (source != null) {
					source = source.replace('.', ',');
				}
				return FORMATTER.parse(source, parsePosition);
			}

			@Override
			public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
				return FORMATTER.format(number, toAppendTo, pos);
			}

			@Override
			public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
				return FORMATTER.format(number, toAppendTo, pos);
			}
		}, true);

		// Registration of the binder
		binder.registerCustomEditor(BigDecimal.class, cbinder);
	}

	@RequestMapping(value = "/admin/resetcm")
	public ModelAndView resetcm() {
		changeMachineService.reset();
		return changemachinetotal();
	}
}
