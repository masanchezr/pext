package com.atmj.gsboot.boss.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class BossController.
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
	@GetMapping("/login")
	public String login() {
		log.warn("probando login");
		return "boss/login";
	}

	/**
	 * Admin.
	 *
	 * @return the model and view
	 */
	@GetMapping("/adminboss")
	public String admin() {
		return "boss/admin";
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@RequestMapping("/403")
	public String accessDeniedPage() {
		return "boss/403";
	}
}