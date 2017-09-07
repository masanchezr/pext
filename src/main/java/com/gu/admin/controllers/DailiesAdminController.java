package com.gu.admin.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.date.DateUtil;
import com.gu.util.string.Util;

@Controller
public class DailiesAdminController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@RequestMapping(value = "/admin/daily")
	public ModelAndView daily() {
		ModelAndView model = new ModelAndView("searchdailyadmin");
		model.addObject("searchForm", new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/admin/resultdaily")
	public ModelAndView resultdaily(@ModelAttribute("searchForm") SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView();
		String sdate = searchForm.getDatefrom();
		Date date;
		if (Util.isEmpty(sdate)) {
			date = new Date();
		} else {
			date = DateUtil.getDate(sdate);
		}
		Calendar c = Calendar.getInstance();
		c.set(2016, 11, 12);
		if (date.after(c.getTime())) {
			Daily daily = dailyService.getDaily(date);
			if (daily.getFinalamount() == null) {
				model.setViewName("notdaily");
			} else {
				model.addObject("daily", daily);
				model.setViewName("dailyadmin");
				model.addObject("datedaily", date);
			}
		} else {
			model.setViewName("notdaily");
		}
		return model;
	}

	@RequestMapping(value = "/admin/beforeday{date}")
	public ModelAndView beforeday(@PathVariable("date") String sdate) {
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
					model.setViewName("notdailyadmin");
				} else {
					model.addObject("daily", daily);
					model.setViewName("dailyadmin");
					model.addObject("datedaily", date);
					existdaily = true;
				}
			} else {
				model.setViewName("notdailyadmin");
				existdaily = true;
			}
		}
		return model;
	}

	@RequestMapping(value = "/admin/againday{date}")
	public ModelAndView againday(@PathVariable("date") String sdate) {
		ModelAndView model = new ModelAndView();
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			date = DateUtil.addDays(date, 1);
			if (date.compareTo(new Date()) < 0) {
				Daily daily = dailyService.getDaily(date);
				if (daily.getFinalamount() == null) {
					model.setViewName("notdailyadmin");
				} else {
					model.addObject("daily", daily);
					model.setViewName("dailyadmin");
					model.addObject("datedaily", date);
					existdaily = true;
				}
			} else {
				model.setViewName("notdailyadmin");
				existdaily = true;
			}
		}
		return model;
	}
}
