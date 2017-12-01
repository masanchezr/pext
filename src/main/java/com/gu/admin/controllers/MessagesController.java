package com.gu.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.validators.MessageValidator;
import com.gu.dbaccess.entities.MessageEntity;
import com.gu.services.messages.MessageService;

@Controller
public class MessagesController {

	@Autowired
	private MessageService messageservice;

	@Autowired
	private MessageValidator messagevalidator;

	@RequestMapping(value = "/admin/newmessage")
	public ModelAndView newMessage() {
		ModelAndView model = new ModelAndView("newmessage");
		model.addObject("message", new MessageEntity());
		return model;
	}

	@RequestMapping(value = "/admin/savemessage")
	public ModelAndView savemessage(@ModelAttribute("message") MessageEntity message, BindingResult result) {
		messagevalidator.validate(message, result);
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("newmessage");
			model.addObject("message", message);
			return model;
		} else {
			messageservice.save(message);
			return messages();
		}
	}

	@RequestMapping(value = "/admin/messages")
	public ModelAndView messages() {
		ModelAndView model = new ModelAndView("allmessages");
		Iterable<MessageEntity> messages = messageservice.getAllMessages();
		model.addObject("messages", messages);
		return model;
	}

	@RequestMapping(value = "/admin/updatemessage")
	public ModelAndView updatemessage(@ModelAttribute("message") MessageEntity message) {
		ModelAndView model = new ModelAndView("newmessage");
		model.addObject("message", messageservice.findById(message.getIdmessage()));
		return model;
	}
}
