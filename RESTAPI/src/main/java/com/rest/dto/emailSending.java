package com.rest.dto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.ts.dao.StudentDAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Properties;

public class emailSending {
public void MailSending(Faculty faculty,Tasks task){

System.out.println("In mail sending");
StudentDAO studentDao = new StudentDAO();
List<Student> studentList = studentDao.getAllStudents();
       final String username = "learnandinnovate123@gmail.com";
       final String password = "learnovate@123";
    
       String sendMessage = "Hi, Greetings from LEARNOVATE!!\nNew task is given to you by "+faculty.getFacultyName()+" in "+faculty.getSubject()+"\n\nTask Description:"+task.getDescription()+"\nDeadline:"+task.getSubmitDate()+"\n\n\nRegards\nTEAM LEARNOVATE.";
       Properties prop = new Properties();
       prop.put("mail.smtp.host", "smtp.gmail.com");
       prop.put("mail.smtp.port", "587");
       prop.put("mail.smtp.auth", "true");
       prop.put("mail.smtp.starttls.enable", "true"); //TLS

       Session session = Session.getInstance(prop,
               new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(username, password);
                   }
               });

       try {

           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress( "learnandinnovate123@gmail.com"));
           for(Student student: studentList) {
        	   String emailId=student.getEmailId();
           message.setRecipients(
                   Message.RecipientType.TO,
                   InternetAddress.parse(emailId)
           );
   
           message.setSubject("New Task Announcement");
           
           message.setText(sendMessage);
       
           Transport.send(message);

           System.out.println("Done");
           }
       } catch (MessagingException e) {
           e.printStackTrace();
       }
   }

public void MailSending1(Student student){

System.out.println("In mail sending");
StudentDAO studentDao = new StudentDAO();
       final String username = "learnandinnovate123@gmail.com";
       final String password = "learnovate@123";
    
       String sendMessage = "Hi, Greetings from LEARNOVATE!!\nYour Password is: "+student.getPassword()+"\n\n\nRegards\nTEAM LEARNOVATE.";
       Properties prop = new Properties();
       prop.put("mail.smtp.host", "smtp.gmail.com");
       prop.put("mail.smtp.port", "587");
       prop.put("mail.smtp.auth", "true");
       prop.put("mail.smtp.starttls.enable", "true"); //TLS

       Session session = Session.getInstance(prop,
               new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(username, password);
                   }
               });

       try {

           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress( "learnandinnovate123@gmail.com"));
          
        	   String emailId=student.getEmailId();
           message.setRecipients(
                   Message.RecipientType.TO,
                   InternetAddress.parse(emailId)
           );
   
           message.setSubject("Find Your Password Here!!!");
           
           message.setText(sendMessage);
       
           Transport.send(message);

           System.out.println("Done");
       } catch (MessagingException e) {
           e.printStackTrace();
       }
   }
public String sendSms(Student student,Faculty faculty,Doubts doubt) {
	try {
		// Construct data
		String apiKey = "apikey=" + "owsdnOedgaw-CubuvUYLvXonajT9V1GK3hHDGDayNi";
		String message = "&message=" +("Hi,"+faculty.getFacultyName()+"\nNew doubt is posted by "+student.getStudentName()+"\nDoubt:"+doubt.getQuestion()+"\n\n\nRegards\nTEAM LEARNOVATE.");
		String sender = "&sender=" + "TXTLCL";
		String numbers = "&numbers=" + ("91"+faculty.getMobileNumber());
		
		// Send data
		HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
		String data = apiKey + numbers + message + sender;
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
		conn.getOutputStream().write(data.getBytes("UTF-8"));
		final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		final StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			stringBuffer.append(line);
		}
		rd.close();
		
		return stringBuffer.toString();
	} catch (Exception e) {
		System.out.println("Error SMS "+e);
		return "Error "+e;
	}
}

public String sendSms1(Student student,Faculty faculty,Doubts doubt) {
	try {
		// Construct data
		String apiKey = "apikey=" + "owsdnOedgaw-CubuvUYLvXonajT9V1GK3hHDGDayNi";
		String message = "&message=" +("Hi,"+student.getStudentName()+"\nYour doubt is answered by "+faculty.getFacultyName()+"\nQuestion:"+doubt.getQuestion()+"\nAnswer:"+doubt.getAnswer()+"\n\n\nRegards\nTEAM LEARNOVATE.");
		String sender = "&sender=" + "TXTLCL";
		String numbers = "&numbers=" + ("91"+student.getMobileNumber());
		
		// Send data
		HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
		String data = apiKey + numbers + message + sender;
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
		conn.getOutputStream().write(data.getBytes("UTF-8"));
		final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		final StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			stringBuffer.append(line);
		}
		rd.close();
		
		return stringBuffer.toString();
	} catch (Exception e) {
		System.out.println("Error SMS "+e);
		return "Error "+e;
	}
}

}

