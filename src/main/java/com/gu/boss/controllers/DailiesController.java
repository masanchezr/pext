package com.gu.boss.controllers;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;
import com.gu.util.string.Util;
import com.gu.validators.SearchDatesFormValidator;

@Controller
public class DailiesController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private SearchDatesFormValidator searchDatesFormValidator;

	private static final String VIEWSEARCHCALCULATEDAILIES = "searchcalculatedailies";
	private static final String FORMSEARCHDAILY = "searchDailyForm";
	private static final String VIEWSEARCHDAILY = "searchdaily";

	@RequestMapping(value = "/searchcalculatedailies")
	public ModelAndView searchCalculateDailies() {
		ModelAndView model = new ModelAndView(VIEWSEARCHCALCULATEDAILIES);
		model.addObject(FORMSEARCHDAILY, new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/daily")
	public ModelAndView daily() {
		ModelAndView model = new ModelAndView(VIEWSEARCHDAILY);
		model.addObject(FORMSEARCHDAILY, new SearchByDatesForm());
		return model;
	}

	/**
	 * Search daily.
	 *
	 * @param sdf the sdf
	 * @return the model and view
	 */
	@RequestMapping(value = "/resultdaily")
	public ModelAndView searchDaily(@ModelAttribute(FORMSEARCHDAILY) SearchByDatesForm sdf, BindingResult arg1) {
		ModelAndView model;
		String sdate = sdf.getDatefrom();
		Date date;
		if (Util.isEmpty(sdate)) {
			date = new Date();
			return getDailyModel(date);
		} else {
			searchDatesFormValidator.validate(sdf, arg1);
			if (arg1.hasErrors()) {
				model = new ModelAndView();
				model.addObject(FORMSEARCHDAILY, sdf);
				model.setViewName(VIEWSEARCHDAILY);
				return model;
			} else {
				date = DateUtil.getDate(sdate);
				return getDailyModel(date);
			}
		}
	}

	private ModelAndView getDailyModel(Date date) {
		ModelAndView model = new ModelAndView();
		Daily daily = dailyService.getDaily(date);
		if (daily.getFinalamount() == null) {
			model.setViewName(ConstantsJsp.VIEWNOTDAILYBOSS);
		} else {
			String view;
			String stoday = DateUtil.getStringDateFormatddMMyyyy(new Date());
			String sdate = DateUtil.getStringDateFormatddMMyyyy(date);
			if (stoday.compareTo(sdate) == 0) {
				view = ConstantsJsp.VIEWDAILYBOSSARROW;
			} else {
				view = ConstantsJsp.VIEWDAILYBOSSARROWS;
			}
			model.addObject(ConstantsJsp.DAILY, daily);
			model.setViewName(view);
			model.addObject(ConstantsJsp.DATEDAILY, date);
		}
		return model;
	}

	@RequestMapping(value = "/calculatedailies")
	public ModelAndView calculateDailies(@ModelAttribute(FORMSEARCHDAILY) SearchByDatesForm sdf,
			HttpServletRequest request, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		searchDatesFormValidator.validate(sdf, arg1);
		if (arg1.hasErrors()) {
			model.setViewName(VIEWSEARCHCALCULATEDAILIES);
		} else {
			String sdate = sdf.getDatefrom();
			Date date = DateUtil.getDate(sdf.getDatefrom());
			if (date != null) {
				Calendar c = Calendar.getInstance();
				c.set(2015, 2, 31);
				date = DateUtil.getDate(sdate);
				if (date.after(c.getTime())) {
					dailyService.calculateDailies(date);
					model.setViewName(ConstantsJsp.SUCCESS);
				} else {
					model.setViewName(VIEWSEARCHCALCULATEDAILIES);
				}
			}
		}
		return model;
	}

	@RequestMapping(value = "/beforeday{date}")
	public ModelAndView beforeday(@PathVariable(Constants.DATE) String sdate) {
		ModelAndView model = new ModelAndView();
		Calendar c = Calendar.getInstance();
		c.set(2015, 2, 31);
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			if (date.after(c.getTime())) {
				date = DateUtil.addDays(date, -1);
				Daily daily = dailyService.getDaily(date);
				if (daily.getFinalamount() == null) {
					model.setViewName(ConstantsJsp.VIEWNOTDAILYBOSS);
				} else {
					model.addObject(ConstantsJsp.DAILY, daily);
					model.setViewName(ConstantsJsp.VIEWDAILYBOSSARROWS);
					model.addObject(ConstantsJsp.DATEDAILY, date);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsJsp.VIEWNOTDAILYBOSS);
				existdaily = true;
			}
		}
		return model;
	}

	@RequestMapping(value = "/beforeday")
	public ModelAndView beforeday() {
		ModelAndView model = new ModelAndView();
		Date date = DateUtil.addDays(DateUtil.getDateFormated(new Date()), -1);
		Daily daily = dailyService.getDaily(DateUtil.addDays(DateUtil.getDateFormated(new Date()), -1));
		if (daily.getFinalamount() == null) {
			model.setViewName(ConstantsJsp.VIEWNOTDAILYBOSS);
		} else {
			model.addObject(ConstantsJsp.DAILY, daily);
			model.setViewName(ConstantsJsp.VIEWDAILYBOSSARROWS);
			model.addObject(ConstantsJsp.DATEDAILY, date);
		}
		return model;
	}

	@RequestMapping(value = "/againday{date}")
	public ModelAndView againday(@PathVariable(Constants.DATE) String sdate) {
		ModelAndView model = new ModelAndView();
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			date = DateUtil.addDays(date, 1);
			if (date.compareTo(new Date()) < 0) {
				Daily daily = dailyService.getDaily(date);
				if (daily.getFinalamount() == null) {
					model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
				} else {
					String view;
					String stoday = DateUtil.getStringDateFormatddMMyyyy(new Date());
					sdate = DateUtil.getStringDateFormatddMMyyyy(date);
					if (stoday.compareTo(sdate) == 0) {
						view = "dailybossarrow";
					} else {
						view = ConstantsJsp.VIEWDAILYBOSSARROWS;
					}
					model.addObject(ConstantsJsp.DAILY, daily);
					model.setViewName(view);
					model.addObject(ConstantsJsp.DATEDAILY, date);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
				existdaily = true;
			}
		}
		return model;
	}

}
