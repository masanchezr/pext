package com.bar.boss.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class AdminController.
 */
@Controller
public class BossController {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(BossController.class);

	/**
	 * Login.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/login")
	public String login() {
		log.warn("probando login");
		return "loginboss";
	}

	/**
	 * Admin.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/adminboss")
	public String admin() {
		return "adminboss";
	}

	/**
	 * Error.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/error")
	public String error() {
		return "error";
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@RequestMapping("/403")
	public String accessDeniedPage() {
		return "403";
	}
}