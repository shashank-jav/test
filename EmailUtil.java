package com.dgh.utility;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;

public class EmailUtil {

	final static String SMTP_HOST_NAME = "";
	final static String SMTP_PORT = "";
	final static String SMTP_AUTH_USER = "n";
	final static String SMTP_AUTH_PWD = "";
	final static Properties props = new Properties();
	
	static {
		// Set the host smtp address
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "true");
	}

	/**
	 * @param subject
	 * @param message
	 * @param attachmentFilePath
	 * @param recipients
	 * @param cc
	 * @throws MessagingException
	 */
	public static void sendMail(String subject, String message, String attachmentFilePath, String recipients, String cc) throws MessagingException {

		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getInstance(props, auth);
		
		session.setDebug(true);
		
		MimeMessage msg = new MimeMessage(session);
		if(StringUtils.isEmpty(attachmentFilePath)) {
			msg.setContent(message, "text/html;charset=utf-8");
		} else {
			// multipart contains both message and attachment
			Multipart multipart = new MimeMultipart();
			
			// setting message to multipart
			MimeBodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(message, "text/html");
			multipart.addBodyPart(contentPart);
			
			// setting attachment to multipart
			MimeBodyPart attachmentPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentFilePath);
			attachmentPart.setDataHandler(new DataHandler(source));
			int indx = attachmentFilePath.lastIndexOf("/");
			attachmentFilePath = attachmentFilePath.substring(indx + 1);
			attachmentPart.setFileName(source.getName());
			multipart.addBodyPart(attachmentPart);
			
			// setting multipart to MimeMessage
			msg.setContent(multipart);
		}
		
		msg.setFrom(new InternetAddress(SMTP_AUTH_USER));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
		if(StringUtils.isNotEmpty(cc))
			//msg.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));*/
			msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
		msg.setSubject(subject);
		
		Transport.send(msg);
		System.out.println("Mail sent successfully!");
	}


	private static class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
		}
	}
	
	
	public static void main(String[] args) {
		
		
	}
}
