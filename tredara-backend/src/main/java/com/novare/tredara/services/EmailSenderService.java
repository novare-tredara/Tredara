package com.novare.tredara.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.novare.tredara.exceptions.TredaraException;

@Service
public class EmailSenderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);

	@Autowired
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String sender;

	public void sendSimpleEmail(String toEmail, String subject, String body) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(sender);
			message.setTo(toEmail);
			message.setText(body);
			message.setSubject(subject);
			mailSender.send(message);
		} catch (Exception e) {
			LOGGER.error("Error occured while sending the email: {}", e.getMessage());
			throw new TredaraException("Error occured while sending the email", HttpStatus.INTERNAL_SERVER_ERROR,
					e.toString());
		}
	}
}
