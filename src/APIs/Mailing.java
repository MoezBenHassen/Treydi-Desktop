package APIs;

import javax.mail.*;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailing {
    public static void sendMail(String email,String text,String subject) throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("khedhribairem@gmail.com","esprin123.");
            }
        });
        Message message=new MimeMessage(session);
        message.setFrom(new InternetAddress("bairemkh@gmail.com"));
        message.setRecipient(RecipientType.TO,new InternetAddress(email));
        message.setSubject(subject);
        message.setContent(text,"text/html");
        message.setSentDate(new Date());
        Transport.send(message);
        System.out.println("sent!!");
    }
}