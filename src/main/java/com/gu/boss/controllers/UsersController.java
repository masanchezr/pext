package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.boss.validators.UserValidator;
import com.gu.services.users.User;
import com.gu.services.users.UserService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class UsersController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	private static final String VIEWUSERS = "enabledisableuser";
	private static final String VIEWNEWUSER = "newuser";

	@RequestMapping(value = "/enabledisableuser")
	public ModelAndView enabledisableuser() {
		ModelAndView model = new ModelAndView(VIEWUSERS);
		model.addObject(ConstantsJsp.USER, new User());
		return model;
	}

	@RequestMapping(value = "/resultenabledisableuser")
	public ModelAndView resultenabledisableuser(@ModelAttribute(ConstantsJsp.USER) User user, BindingResult result) {
		ModelAndView model = new ModelAndView("resultenabledisableuser");
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWUSERS);
			model.addObject(ConstantsJsp.USER, new User());
		} else {
			user = userService.disableEnableUser(user.getUsername());
			if (user == null) {
				model.setViewName(VIEWUSERS);
				model.addObject(ConstantsJsp.USER, new User());
				result.rejectValue(Constants.USERNAME, "usernoexist");
			} else {
				model.addObject(ConstantsJsp.USER, user);
				model.setViewName("resultenabledisableuser");
			}
		}
		return model;
	}

	@RequestMapping(value = "/newuser")
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView(VIEWNEWUSER);
		model.addObject(ConstantsJsp.USER, new User());
		return model;
	}

	@RequestMapping(value = "/saveuser")
	public ModelAndView saveUser(@ModelAttribute(ConstantsJsp.USER) User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWUSER);
			model.addObject(ConstantsJsp.USER, new User());
		} else {
			model.setViewName("resultuser");
			model.addObject(ConstantsJsp.USER, user);
			userService.newUser(user);
		}
		return model;
	}
}
