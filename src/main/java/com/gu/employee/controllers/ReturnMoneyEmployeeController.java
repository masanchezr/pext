package com.gu.employee.controllers;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.gu.employee.forms.ReturnMoneyEmployee;
import com.gu.services.dailies.DailyService;
import com.gu.services.employees.EmployeeService;
import com.gu.services.returnmoneyemployees.ReturnMoneyEmployeeService;
import com.gu.util.constants.ConstantsViews;

@Controller
public class ReturnMoneyEmployeeController {

	@Autowired
	private EmployeeService employeeservice;

	@Autowired
	private DailyService dailyService;

	@Autowired
	private ReturnMoneyEmployeeService returnmoneyemployeeservice;

	@Autowired
	private Mapper mapper;

	private static final String FORMMONEYADVANCE = "moneyadvance";

	@GetMapping("/employee/newreturn")
	public ModelAndView newreturn() {
		ModelAndView model = new ModelAndView("employee/income/returnmoneyemployee");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		EmployeeEntity employee = employeeservice.getEmployeeByUserName(user);
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
		EmployeeEntity employee = employeeservice.getEmployeeByUserName(user);
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
