package com.atmj.gsboot.employee.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.atmj.gsboot.employee.forms.ReturnMoneyEmployee;
import com.atmj.gsboot.services.dailies.DailyService;
import com.atmj.gsboot.services.returnmoneyemployees.ReturnMoneyEmployeeService;
import com.atmj.gsboot.services.users.User;
import com.atmj.gsboot.services.users.UserService;
import com.atmj.gsboot.util.constants.ConstantsViews;

@Controller
public class ReturnMoneyEmployeeController {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private ReturnMoneyEmployeeService returnmoneyemployeeservice;

	@Autowired
	private UserService employeeservice;

	@Autowired
	private ModelMapper mapper;

	private static final String FORMMONEYADVANCE = "moneyadvance";

	@GetMapping("/employee/newreturn")
	public ModelAndView newreturn() {
		ModelAndView model = new ModelAndView("employee/income/returnmoneyemployee");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		User employee = employeeservice.findUser(user);
		List<ReturnMoneyEmployeeEntity> moneyadvance = returnmoneyemployeeservice.findAdvanceByEmployee(employee);
		model.addObject(FORMMONEYADVANCE, moneyadvance);
		model.addObject(ConstantsViews.FORMINCOME, new ReturnMoneyEmployee());
		return model;
	}

	@PostMapping("/employee/savereturn")
	public ModelAndView savereturn(@ModelAttribute(ConstantsViews.FORMINCOME) ReturnMoneyEmployee returnme) {
		ModelAndView model = new ModelAndView();
		returnmoneyemployeeservice.savereturn(mapper.map(returnme, ReturnMoneyEmployeeEntity.class));
		model.addObject(ConstantsViews.DAILY, dailyService.getDailyEmployee());
		model.setViewName("employee/daily/daily");
		return model;
	}

	@GetMapping("/employee/newmoneyadvance")
	public ModelAndView newmoneyadvance() {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addObject(ConstantsViews.USER, user);
		model.addObject(FORMMONEYADVANCE, new ReturnMoneyEmployeeEntity());
		model.setViewName("employee/expenses/moneyadvance/newmoneyadvance");
		return model;
	}

	@PostMapping("/employee/moneyadvance")
	public Object moneyadvance(@ModelAttribute(FORMMONEYADVANCE) ReturnMoneyEmployee returnme) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		User employee = employeeservice.findUser(user);
		returnme.setEmployee(employee);
		ReturnMoneyEmployeeEntity entity = mapper.map(returnme, ReturnMoneyEmployeeEntity.class);
		// miramos primero que no supere el importe
		if (returnmoneyemployeeservice.isAllowedAdvances(entity)) {
			returnmoneyemployeeservice.savemoneyadvance(entity);
			model.addObject(ConstantsViews.DAILY, dailyService.getDailyEmployee());
			model.setViewName("employee/daily/daily");
			return model;
		} else {
			model.setViewName("employee/expenses/moneyadvance/notmoney");
			return model;
		}
	}
}
