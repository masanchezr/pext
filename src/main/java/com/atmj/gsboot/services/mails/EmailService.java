package com.atmj.gsboot.services.mails;

public interface EmailService {
	public void sendSimpleMessage(String to, String subject, String text);
}
