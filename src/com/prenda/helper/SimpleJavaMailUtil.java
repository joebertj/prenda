package com.prenda.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

public class SimpleJavaMailUtil {
	
	public static void send(String targetUser, String toEmail, String key) {
		Properties props = new Properties();
		try {
			props.load(SimpleJavaMailUtil.class.getResourceAsStream("/sjm.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String url = props.getProperty("sjm.url");
		String host = props.getProperty("sjm.host");
		int port = new Integer(props.getProperty("sjm.port"));
		String user = props.getProperty("sjm.user");
		String pass = props.getProperty("sjm.pass");
		int timeout = new Integer(props.getProperty("sjm.timeout"));
		boolean debug = new Boolean(props.getProperty("sjm.debug"));
		String fromEmail = props.getProperty("sjm.fromEmail");
		String subject = props.getProperty("sjm.subject");
		String preBody= "Hi, <br/><br/>Your key is " + key +"<br/><br/>";
		String body = "Go to <a href=\"" + url + "/register/activate.jsp\">" + url + "/register/activate.jsp</a>, enter your key and click Activate.<br/>";
		String link = "Or click this <a href=\"" + url + "/register/ActivateOwner.htm" + "?user=" + targetUser + "&key=" + key + "\">link</a><br/><br/>";
		String postBody = props.getProperty("sjm.postBody");
		Email email = EmailBuilder.startingBlank()
			    .from(fromEmail)
			    .to(toEmail)
			    .withSubject(subject)
			    .withHTMLText(preBody + body + link + postBody)
			    .buildEmail();
		Mailer mailer = MailerBuilder
		          .withSMTPServer(host, port, user, pass)
		          .withTransportStrategy(TransportStrategy.SMTP_TLS)
		          .withSessionTimeout(timeout * 1000)
		          .withDebugLogging(debug)
		          .buildMailer();
		mailer.sendMail(email);
	}
}
