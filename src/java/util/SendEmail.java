/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author TRANTATDAT
 */
import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    String toEmail;
    String subject;
    String message;

    public SendEmail(String toEmail, String subject, String message) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.message = message;
        this.sentEmail(toEmail, subject, message);
    }

    public static void alo(File a) {
        if (a.isDirectory()) {
            System.out.println("Folder " + a.getAbsolutePath());
            File[] b = a.listFiles();
            for (File c : b) {
                alo(c);
            }
            System.out.println(a.delete());
        } else {
            System.out.println("File " + a.getAbsolutePath());
            System.out.println(a.delete());
        }
    }
    private final String MAIL = "trantatdatdz2001@gmail.com";
    private final String PASSWORD = "Mirotic1898";

    public void sentEmail(String toEmail, String subject, String text) {

        // Config
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");

        // Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL, PASSWORD);
            }
        });

        // Mail info
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            System.out.println("Message sent successfully...");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
