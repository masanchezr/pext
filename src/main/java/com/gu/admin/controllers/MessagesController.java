package com.gu.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.validators.MessageValidator;
import com.gu.services.messages.Message;
import com.gu.services.messages.MessageService;
import com.gu.util.constants.Constants;

@Controller
public class MessagesController {

	@Autowired
	private MessageService messageservice;

	@Autowired
	private MessageValidator messagevalidator;

	private static final String VIEWNEWMESSAGE = "admin/messages/newmessage";

	@GetMapping("/admin/newmessage")
	public ModelAndView newMessage() {
		ModelAndView model = new ModelAndView(VIEWNEWMESSAGE);
		model.addObject(Constants.MESSAGE, new Message());
		return model;
	}

	@PostMapping("/admin/savemessage")
	public ModelAndView savemessage(@ModelAttribute(Constants.MESSAGE) Message message, BindingResult result) {
		messagevalidator.validate(message, result);
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView(VIEWNEWMESSAGE);
			model.addObject(Constants.MESSAGE, message);
			return model;
		} else {
			messageservice.save(message);
			return messages();
		}
	}

	@GetMapping("/admin/messages")
	public ModelAndView messages() {
		ModelAndView model = new ModelAndView("admin/messages/allmessages");
		model.addObject("messages", messageservice.getAllMessages());
		model.addObject(Constants.MESSAGE, new Message());
		return model;
	}

	@PostMapping("/admin/updatemessage")
	public ModelAndView updatemessage(@ModelAttribute(Constants.MESSAGE) Message message) {
		ModelAndView model = new ModelAndView(VIEWNEWMESSAGE);
		model.addObject(Constants.MESSAGE, messageservice.findById(message.getIdmessage()));
		return model;
	}
}
