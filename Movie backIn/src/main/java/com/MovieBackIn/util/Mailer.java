
package com.MovieBackIn.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.security.SecureRandom;

public class Mailer {

    public static String sendOtp(String toEmail) throws Exception {
        // Generate 6-digit OTP
        String otp = String.valueOf(100000 + new SecureRandom().nextInt(900000));

        String host = "smtp.gmail.com";
        String port = "587";
        String user = "peak13378@gmail.com";     // your Gmail
        String pass = "swkc wogi nxzb drlr";     // app password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        // 👇 enable SMTP debug logs
        session.setDebug(true);

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user)); // keep simple for Gmail
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your OTP Code");
            message.setContent("<h2>Your OTP is: <b>" + otp + "</b></h2>", "text/html");

            // Send email
            Transport.send(message);

            System.out.println("✅ OTP sent successfully to " + toEmail);
        } catch (MessagingException e) {
            System.out.println("❌ Email sending failed! Reason:");
            e.printStackTrace(); // full SMTP error
            throw e; // rethrow to servlet
        }

        System.out.println("Generated OTP: " + otp);
        return otp;
    }
}
