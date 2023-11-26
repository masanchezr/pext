package com.atmj.gsboot.admin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class AdminController.
 */
@Controller
public class AdminController {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(AdminController.class);

	/**
	 * Login.
	 * 
	 * @return the string
	 */
	@GetMapping("/admin/login")
	public String login() {
		log.warn("probando login");
		return "admin/login";
	}

	/**
	 * Admin.
	 *
	 * @return the model and view
	 */
	@GetMapping("/admin/admin")
	public String admin() {
		return "admin/admin";
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@RequestMapping("/403admin")
	public String accessDeniedPage() {
		return "admin/403";
	}
}