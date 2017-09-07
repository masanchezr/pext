package com.gu.services.mails;

public interface MailService {

	void sendMail(String message, String cc, String subject);

}
