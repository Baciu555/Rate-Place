package com.baciu.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(String to, String subject, String content) {
		MimeMessage mail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(to);
			helper.setReplyTo("cerealpl@wp.pl");
			helper.setFrom("wojtekbac95@gmail.com");
			helper.setSubject(subject);
			helper.setText(content, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		javaMailSender.send(mail);
	}
	
}
