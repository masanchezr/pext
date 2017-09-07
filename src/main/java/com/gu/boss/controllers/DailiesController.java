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
import com.gu.util.date.DateUtil;
import com.gu.validators.SearchDatesFormValidator;

@Controller
public class DailiesController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private SearchDatesFormValidator searchDatesFormValidator;

	@RequestMapping(value = "/searchcalculatedailies")
	public ModelAndView searchCalculateDailies() {
		ModelAndView model = new ModelAndView("searchcalculatedailies");
		model.addObject("searchDailyForm", new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/daily")
	public ModelAndView daily() {
		ModelAndView model = new ModelAndView("searchdaily");
		model.addObject("searchDailyForm", new SearchByDatesForm());
		return model;
	}

	/**
	 * Search daily.
	 *
	 * @param sdf
	 *            the sdf
	 * @return the model and view
	 */
	@RequestMapping(value = "/resultdaily")
	public ModelAndView searchDaily(@ModelAttribute("searchDailyForm") SearchByDatesForm sdf, BindingResult arg1) {
		ModelAndView model;
		String sdate = sdf.getDatefrom();
		Date date;
		if (sdate == null) {
			date = new Date();
			return getDailyModel(date);
		} else {
			searchDatesFormValidator.validate(sdf, arg1);
			if (arg1.hasErrors()) {
				model = new ModelAndView();
				model.addObject("searchDailyForm", sdf);
				model.setViewName("searchdaily");
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
			model.setViewName("notdailyadmin");
		} else {
			model.addObject("daily", daily);
			model.setViewName("dailyboss");
			model.addObject("datedaily", date);
		}
		return model;
	}

	@RequestMapping(value = "/calculatedailies")
	public ModelAndView calculateDailies(@ModelAttribute("searchDailyForm") SearchByDatesForm sdf,
			HttpServletRequest request, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		searchDatesFormValidator.validate(sdf, arg1);
		if (arg1.hasErrors()) {
			model.setViewName("searchcalculatedailies");
		} else {
			String sdate = sdf.getDatefrom();
			Date date = DateUtil.getDate(sdf.getDatefrom());
			if (date != null) {
				Calendar c = Calendar.getInstance();
				c.set(2015, 2, 31);
				date = DateUtil.getDate(sdate);
				if (date.after(c.getTime())) {
					dailyService.calculateDailies(date);
					model.setViewName("success");
				} else {
					model.setViewName("searchcalculatedailies");
				}
			}
		}
		return model;
	}

	@RequestMapping(value = "/beforeday{date}")
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
					model.setViewName("dailyboss");
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

	@RequestMapping(value = "/againday{date}")
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
					model.setViewName("dailyboss");
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
