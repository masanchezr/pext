package com.sboot.golden.boss.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.forms.SearchByDatesForm;
import com.sboot.golden.services.dailies.Daily;
import com.sboot.golden.services.dailies.DailyService;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.util.date.DateUtil;
import com.sboot.golden.util.string.Util;
import com.sboot.golden.validators.SearchDatesFormValidator;

@Controller
public class DailiesController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;
	private static final String FORMSEARCHDAILY = "searchDailyForm";
	private static final String VIEWSEARCHDAILY = "boss/dailies/searchdaily";

	@GetMapping("/daily")
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
	@PostMapping("/resultdaily")
	public ModelAndView searchDaily(
			@Validated(SearchDatesFormValidator.class) @ModelAttribute(FORMSEARCHDAILY) SearchByDatesForm sdf,
			BindingResult arg1) {
		ModelAndView model;
		String sdate = sdf.getDatefrom();
		Date date;
		if (Util.isEmpty(sdate)) {
			date = DateUtil.getDateFormatddMMyyyy(new DateUtil().getNow());
			return getDailyModel(date);
		} else {
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
			model.setViewName(ConstantsViews.VIEWNOTDAILYBOSS);
		} else {
			String view;
			String stoday = DateUtil.getStringDateFormatddMMyyyy(new DateUtil().getNow());
			String sdate = DateUtil.getStringDateFormatddMMyyyy(date);
			if (stoday.compareTo(sdate) == 0) {
				view = ConstantsViews.VIEWDAILYBOSSARROW;
			} else {
				view = ConstantsViews.VIEWDAILYBOSSARROWS;
			}
			model.addObject(ConstantsViews.DAILY, daily);
			model.setViewName(view);
			model.addObject(ConstantsViews.DATEDAILY, sdate);
		}
		return model;
	}

	@GetMapping("/beforeday{date}")
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
					model.setViewName(ConstantsViews.VIEWNOTDAILYBOSS);
				} else {
					model.addObject(ConstantsViews.DAILY, daily);
					model.setViewName(ConstantsViews.VIEWDAILYBOSSARROWS);
					model.addObject(ConstantsViews.DATEDAILY, (DateUtil.getDateFormatddMMyyyy(date)));
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsViews.VIEWNOTDAILYBOSS);
				existdaily = true;
			}
		}
		return model;
	}

	@GetMapping("/beforeday")
	public ModelAndView beforeday() {
		ModelAndView model = new ModelAndView();
		Date date = DateUtil.addDays(DateUtil.getDateFormatddMMyyyy(new DateUtil().getNow()), -1);
		Daily daily = dailyService
				.getDaily(DateUtil.addDays(DateUtil.getDateFormatddMMyyyy(new DateUtil().getNow()), -1));
		if (daily.getFinalamount() == null) {
			model.setViewName(ConstantsViews.VIEWNOTDAILYBOSS);
		} else {
			model.addObject(ConstantsViews.DAILY, daily);
			model.setViewName(ConstantsViews.VIEWDAILYBOSSARROWS);
			model.addObject(ConstantsViews.DATEDAILY, date);
		}
		return model;
	}

	@GetMapping("/againday{date}")
	public ModelAndView againday(@PathVariable(Constants.DATE) String sdate) {
		ModelAndView model = new ModelAndView();
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			date = DateUtil.addDays(date, 1);
			if (date.compareTo(new DateUtil().getNow()) < 0) {
				Daily daily = dailyService.getDaily(date);
				if (daily.getFinalamount() == null) {
					model.setViewName(ConstantsViews.VIEWNOTDAILYADMIN);
				} else {
					String view;
					String stoday = DateUtil.getStringDateFormatddMMyyyy(new DateUtil().getNow());
					sdate = DateUtil.getStringDateFormatddMMyyyy(date);
					if (stoday.compareTo(sdate) == 0) {
						view = ConstantsViews.VIEWDAILYBOSSARROW;
					} else {
						view = ConstantsViews.VIEWDAILYBOSSARROWS;
					}
					model.addObject(ConstantsViews.DAILY, daily);
					model.setViewName(view);
					model.addObject(ConstantsViews.DATEDAILY, date);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsViews.VIEWNOTDAILYADMIN);
				existdaily = true;
			}
		}
		return model;
	}

}
