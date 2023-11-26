package com.atmj.gsboot.boss.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.services.users.User;
import com.atmj.gsboot.services.users.UserService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;

@Controller
public class UsersController {

	@Autowired
	private UserService userService;

	private static final String VIEWNEWUSER = "boss/users/saveuser/newuser";

	@GetMapping("/enabledisableuser")
	public ModelAndView enabledisableuser() {
		ModelAndView model = new ModelAndView("boss/users/update/allusers");
		model.addObject("users", userService.allUsers());
		model.addObject("userForm", new User());
		return model;
	}

	@PostMapping("/update")
	public ModelAndView resultenabledisableuser(@Valid User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.USER, user);
		if (result.hasErrors()) {
			model.setViewName("boss/users/update/updateuser");
		} else {
			userService.newUser(user);
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
	public ModelAndView saveUser(@Valid User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
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
