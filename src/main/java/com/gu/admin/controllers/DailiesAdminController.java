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
		String view;
		if (Util.isEmpty(sdate)) {
			date = new Date();
			view = "dailyadminarrow";
		} else {
			date = DateUtil.getDate(sdate);
			view = "dailyadminarrows";
		}
		Calendar c = Calendar.getInstance();
		if (date.before(c.getTime())) {
			Daily daily = dailyService.getDaily(date);
			if (daily.getFinalamount() == null) {
				model.setViewName("notdailyadmin");
			} else {
				model.addObject("daily", daily);
				model.setViewName(view);
				model.addObject("datedaily", date);
			}
		} else {
			model.setViewName("notdailyadmin");
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
					model.setViewName("dailyadminarrows");
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
					String view;
					String stoday = DateUtil.getStringDateFormatdd_MM_yyyy(new Date());
					sdate = DateUtil.getStringDateFormatdd_MM_yyyy(date);
					if (stoday.compareTo(sdate) == 0) {
						view = "dailyadminarrow";
					} else {
						view = "dailyadminarrows";
					}
					model.addObject("daily", daily);
					model.setViewName(view);
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
