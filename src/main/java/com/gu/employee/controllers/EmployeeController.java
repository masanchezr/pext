package com.gu.employee.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.services.messages.MessageService;
import com.gu.services.register.RegisterService;

/**
 * The Class EmployeeController.
 */
@Controller
public class EmployeeController {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private MessageService messageservice;

	/**
	 * Login.
	 *
	 * @return the string
	 */
	@RequestMapping("/employee/login")
	public String login() {
		return "loginemployee";
	}

	/**
	 * Admin.
	 *
	 * @return the string
	 */
	@RequestMapping("/employee/admin")
	public ModelAndView admin(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("adminemployee");
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		registerService.register(user, ipAddress, Boolean.TRUE);
		model.addObject("messages", messageservice.getMessagesActiveNow());
		return model;
	}

	@RequestMapping("/employee/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/employee/login?logout";
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@RequestMapping("/403employee")
	public String accessDeniedPage() {
		return "403employee";
	}
}
