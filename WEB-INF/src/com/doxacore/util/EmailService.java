package com.doxacore.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
	
	Properties props;
	
	public void setParametros(String host, int port, String username, String password, boolean starttls, boolean auth, boolean ssl) {
	
	        props = new Properties();
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.port", port);
	        props.put("mail.smtp.starttls.enable", starttls);
	        props.put("mail.smtp.auth", auth);
	        props.put("mail.smtp.ssl.enable", ssl);
	       // props.put("mail.debug", "true");    
	
	}
	
	
	public void enviar(String from, String to, String subject, String body) throws AddressException, MessagingException {
		
		Session session = Session.getInstance(props);
		
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setContent(body,"text/html");
        
        Transport.send(msg);
				

		
	}
	

}
