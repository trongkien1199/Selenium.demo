package com.bandoso.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SuiteListener implements ISuiteListener {
    @Override
    public void onFinish(ISuite suite1) {
        System.out.println("come here");
        // Recipient's email ID needs to be mentioned.
        String to = "trongkien1199@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "kienhttest1@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";


        // Setup mail server
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("kienhttest1@gmail.com", "w w m f l i p r w u f z w b i g");

            }

        });
        //session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                File f =new File("/home/nexle/com.bandoso/target/surefire-reports/emailable-report.html");

                attachmentPart.attachFile(f);
                textPart.setText("This is text");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    @Override
    public void onStart(ISuite suite2) {
        System.out.println("onStart function started of ISuiteListener " );
    }
}
