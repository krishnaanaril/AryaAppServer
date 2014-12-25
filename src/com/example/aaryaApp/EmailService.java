package com.example.aaryaApp;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailService {
	public static String from="krishnamohan.a.m@gmail.com";
	private static final Logger logger = Logger.getLogger(SendServlet.class.getCanonicalName());
	
	public void SendEmail(String from, String to, String subject, String body ) {
		// ...
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "...";

		try {
			
		    MimeMessage msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress(from,"AryaApp"));
		    msg.addRecipient(Message.RecipientType.TO,  new InternetAddress(to));
		    msg.setSubject(subject);
		    msg.setContent(body,"text/html; charset=utf-8");
		    Transport.send(msg);
		    logger.warning("Mail Sent");

		} catch (Exception e) {
		    // ...
			logger.warning("Exception: "+e.getMessage());
		} 
		

	}

}
