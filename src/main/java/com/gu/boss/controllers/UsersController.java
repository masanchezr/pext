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

	private static final String VIEWNEWUSER = "newuser";

	@RequestMapping(value = "/enabledisableuser")
	public ModelAndView enabledisableuser() {
		ModelAndView model = new ModelAndView("allusers");
		model.addObject("users", userService.allUsers());
		model.addObject("userForm", new User());
		return model;
	}

	@RequestMapping(value = "/update")
	public ModelAndView resultenabledisableuser(@ModelAttribute(ConstantsJsp.USER) User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		userValidator.validate(user, result);
		model.addObject(ConstantsJsp.USER, user);
		if (result.hasErrors()) {
			model.setViewName("updateuser");
		} else {
			userService.update(user);
			model = enabledisableuser();
		}
		return model;
	}

	@RequestMapping(value = "/newuser")
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView(VIEWNEWUSER);
		model.addObject(ConstantsJsp.USER, new User());
		return model;
	}

	@RequestMapping(value = "/updateuser")
	public ModelAndView updateUser(@ModelAttribute("userForm") User user) {
		ModelAndView model = new ModelAndView("updateuser");
		model.addObject(ConstantsJsp.USER, userService.findUser(user.getUsername()));
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
			model.addObject(ConstantsJsp.USER, user);
			if (userService.findUser(user.getUsername()) != null) {
				model.setViewName(VIEWNEWUSER);
				result.rejectValue(Constants.USERNAME, "exists");
			} else {
				model.setViewName("resultuser");
				userService.newUser(user);
			}
		}
		return model;
	}
}
