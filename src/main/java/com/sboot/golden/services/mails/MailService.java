package com.sboot.golden.services.mails;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailService extends Thread {

	private String message;

	private String cc;

	private String subject;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(MailService.class);

	public MailService(String message, String cc, String subject) {
		this.message = message;
		this.cc = cc;
		this.subject = subject;
	}

	@Override
	public void run() {
		// La dirección de envío (to)
		String to = "mangeles.sanchez0807@gmail.com";
		// La dirección de la cuenta de envío (from)
		final String from = "webmariangeless@hotmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "angeles0466+");
			}
		});

		try {
			// Creamos un objeto mensaje tipo MimeMessage por defecto.
			Message mensaje = new MimeMessage(session);

			// Asignamos el “de o from” al header del correo.
			mensaje.setFrom(new InternetAddress(from));
			// Asignamos el “para o to” al header del correo.
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			if (cc != null) {
				mensaje.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			}
			// Asignamos el asunto
			mensaje.setSubject(subject);
			// Asignamos el mensaje como tal
			mensaje.setText(message);
			// Enviamos el correo
			Transport.send(mensaje);
		} catch (MessagingException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
		}
	}

}
