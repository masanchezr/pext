package com.atmj.gsboot.employee.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.services.messages.MessageService;
import com.atmj.gsboot.services.register.RegisterService;
import com.atmj.gsboot.util.constants.ConstantsViews;

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
	@GetMapping("/employee/login")
	public String login() {
		return "employee/login";
	}

	/**
	 * Admin.
	 *
	 * @return the string
	 */
	@GetMapping("/employee/admin")
	public ModelAndView admin(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("employee/admin");
		String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		registerService.registerIn(user, ipAddress);
		model.addObject("messages", messageservice.getMessagesActiveNow());
		return model;
	}

	@GetMapping("/employee/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			registerService.registerOut(auth.getName(), ipAddress);
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/employee/login?logout";
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@GetMapping("/403employee")
	public String accessDeniedPage() {
		return "employee/403";
	}
}
