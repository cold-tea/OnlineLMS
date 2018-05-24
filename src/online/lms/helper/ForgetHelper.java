package online.lms.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import online.lms.database.DBHandler;

public class ForgetHelper {
	DBHandler handler = null;
	
	public boolean getInfo(String username, ServletContext context) {
		boolean status = false;
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		this.handler = handler;
		ResultSet rs = handler.executePreparedQuery("select password, email from user_table where username = ?", username);
		
		try {
			while(rs.next()) {
				try {
					String email = rs.getString("email");
					String password =  rs.getString("password");
					sendMail(email, username, password);
					status = true;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public String getInfos(int facultyId, int id, String name, String author, ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		String emailStatus = "";
		ResultSet rs = handler.executePreparedQuery("select email from user_table where faculty_id = ?", facultyId);
		List<String> emails = new ArrayList<String>();
		try {
			while (rs.next()) {
				emails.add(rs.getString(1));
			}
			System.out.println(emails);
			emailStatus = mailToMultiple(emails, id, name, author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emailStatus;
	}
	
	private String mailToMultiple(List<String> emails, int id, String name, String author) {
		String emailStatus = "no_sent";
		try {
			
		
			final String emailSender = "sandesh.pokhrel5637@gmail.com";
			final String password = "sandesh37";
	
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
	
			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailSender, password);
				}
			  });
		
	
			try {
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(emailSender));
				Iterator<String> iterator = emails.iterator();
			
				while(iterator.hasNext())
					message.addRecipients(Message.RecipientType.TO,
						InternetAddress.parse(iterator.next()));
				message.setSubject("New Book Added :");
				message.setText("Id: "+ id + "\nBook Title: "+ name + "\nAuthor: "+ author);
				Transport.send(message);
				emailStatus = "sent";
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			
		}
		return emailStatus;
	} 
	
	private void sendMail(String email, String username, String pass) {
		final String emailSender = "sandesh.pokhrel5637@gmail.com";
		final String password = "sandesh37";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailSender, password);
			}
		  });
	

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailSender));
			
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Your account Information :");
			message.setText("Username: "+ username + "\nPassword: "+ pass + "\nEmail: "+ email);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
