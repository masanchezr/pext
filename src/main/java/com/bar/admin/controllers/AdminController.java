package com.bar.admin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
	@RequestMapping(value = "/admin/login")
	public String login() {
		log.warn("probando login");
		return "loginadmin";
	}

	/**
	 * Admin.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/admin/admin")
	public String admin() {
		return "admin";
	}

	/**
	 * Error.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/admin/error")
	public String error() {
		return "error";
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@RequestMapping("/403admin")
	public String accessDeniedPage() {
		return "403admin";
	}
}