package com.gu.admin.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;
import com.gu.util.string.Util;

@Controller
public class DailiesAdminController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@GetMapping("/admin/daily")
	public ModelAndView daily() {
		ModelAndView model = new ModelAndView("admin/dailies/searchdaily");
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@PostMapping("/admin/resultdaily")
	public ModelAndView resultdaily(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView();
		String sdate = searchForm.getDatefrom();
		Date now = new DateUtil().getNow();
		Date date;
		String view;
		if (Util.isEmpty(sdate)) {
			date = DateUtil.getDateFormatddMMyyyy(now);
			view = "admin/dailies/dailyarrow";
		} else {
			date = DateUtil.getDate(sdate);
			view = ConstantsJsp.VIEWDAILYADMINARROWS;
		}
		if (date.before(now) || date.equals(now)) {
			Daily daily = dailyService.getDaily(date);
			if (daily.getFinalamount() == null) {
				model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
			} else {
				model.addObject(ConstantsJsp.DAILY, daily);
				model.setViewName(view);
				model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateFormatddMMyyyy(date));
			}
		} else {
			model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
		}
		return model;
	}

	@GetMapping("/admin/beforeday{date}")
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
					model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
				} else {
					model.addObject(ConstantsJsp.DAILY, daily);
					model.setViewName(ConstantsJsp.VIEWDAILYADMINARROWS);
					model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateFormatddMMyyyy(date));
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
				existdaily = true;
			}
		}
		return model;
	}

	@GetMapping("/admin/againday{date}")
	public ModelAndView againday(@PathVariable(Constants.DATE) String sdate) {
		ModelAndView model = new ModelAndView();
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			date = DateUtil.addDays(date, 1);
			if (date.compareTo(new DateUtil().getNow()) < 0) {
				Daily daily = dailyService.getDaily(date);
				if (daily.getFinalamount() == null) {
					model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
				} else {
					String view;
					String stoday = DateUtil.getStringDateFormatddMMyyyy(new DateUtil().getNow());
					sdate = DateUtil.getStringDateFormatddMMyyyy(date);
					if (stoday.compareTo(sdate) == 0) {
						view = "admin/dailies/dailyarrow";
					} else {
						view = ConstantsJsp.VIEWDAILYADMINARROWS;
					}
					model.addObject(ConstantsJsp.DAILY, daily);
					model.setViewName(view);
					model.addObject(ConstantsJsp.DATEDAILY, sdate);
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
