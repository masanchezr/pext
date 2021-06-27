package com.sboot.golden.services.mails;

public interface EmailService {
	public void sendSimpleMessage(String to, String subject, String text);
}
