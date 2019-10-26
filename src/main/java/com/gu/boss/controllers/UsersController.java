package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.boss.validators.UserValidator;
import com.gu.services.users.User;
import com.gu.services.users.UserService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsViews;

@Controller
public class UsersController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	private static final String VIEWNEWUSER = "boss/users/saveuser/newuser";

	@GetMapping("/enabledisableuser")
	public ModelAndView enabledisableuser() {
		ModelAndView model = new ModelAndView("boss/users/update/allusers");
		model.addObject("users", userService.allUsers());
		model.addObject("userForm", new User());
		return model;
	}

	@PostMapping("/update")
	public ModelAndView resultenabledisableuser(@ModelAttribute(ConstantsViews.USER) User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		userValidator.validate(user, result);
		model.addObject(ConstantsViews.USER, user);
		if (result.hasErrors()) {
			model.setViewName("boss/users/update/updateuser");
		} else {
			userService.update(user);
			model = enabledisableuser();
		}
		return model;
	}

	@GetMapping("/newuser")
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView(VIEWNEWUSER);
		model.addObject(ConstantsViews.USER, new User());
		return model;
	}

	@PostMapping("/updateuser")
	public ModelAndView updateUser(@ModelAttribute("userForm") User user) {
		ModelAndView model = new ModelAndView("boss/users/update/updateuser");
		model.addObject(ConstantsViews.USER, userService.findUser(user.getUsername()));
		return model;
	}

	@PostMapping("/saveuser")
	public ModelAndView saveUser(@ModelAttribute(ConstantsViews.USER) User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWUSER);
			model.addObject(ConstantsViews.USER, new User());
		} else {
			model.addObject(ConstantsViews.USER, user);
			if (userService.findUser(user.getUsername()) != null) {
				model.setViewName(VIEWNEWUSER);
				result.rejectValue(Constants.USERNAME, "exists");
			} else {
				model.setViewName("boss/users/saveuser/resultuser");
				userService.newUser(user);
			}
		}
		return model;
	}
}
