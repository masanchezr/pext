package com.sboot.golden.admin.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.services.messages.Message;
import com.sboot.golden.services.messages.MessageService;
import com.sboot.golden.util.constants.Constants;

@Controller
public class MessagesController {

	@Autowired
	private MessageService messageservice;

	private static final String VIEWNEWMESSAGE = "admin/messages/newmessage";

	@GetMapping("/admin/newmessage")
	public ModelAndView newMessage() {
		ModelAndView model = new ModelAndView(VIEWNEWMESSAGE);
		model.addObject(Constants.MESSAGE, new Message());
		return model;
	}

	@PostMapping("/admin/savemessage")
	public ModelAndView savemessage(@Valid Message message, BindingResult result) {
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
