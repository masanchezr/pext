package com.bar.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bar.services.users.User;
import com.bar.services.users.UserService;
import com.bar.validators.UserValidator;

@Controller
public class UsersController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/enabledisableuser")
	public ModelAndView enabledisableuser() {
		ModelAndView model = new ModelAndView("enabledisableuser");
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "/resultenabledisableuser")
	public ModelAndView resultenabledisableuser(@ModelAttribute("user") User user, BindingResult result) {
		ModelAndView model = new ModelAndView("resultenabledisableuser");
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			model.setViewName("enabledisableuser");
			model.addObject("user", new User());
		} else {
			user = userService.disableEnableUser(user.getUsername());
			if (user == null) {
				model.setViewName("enabledisableuser");
				model.addObject("user", new User());
				result.rejectValue("username", "usernoexist");
			} else {
				model.addObject("user", user);
				model.setViewName("resultenabledisableuser");
			}
		}
		return model;
	}

	@RequestMapping(value = "/newuser")
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView("newuser");
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "/saveuser")
	public ModelAndView saveUser(@ModelAttribute("user") User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			model.setViewName("newuser");
			model.addObject("user", new User());
		} else {
			model.setViewName("resultuser");
			model.addObject("user", user);
			// userService.newUser(user);
		}
		return model;
	}
}
