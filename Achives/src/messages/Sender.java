package messages;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
 
public class Sender {
 
    private String username;
    private String password;
    private Properties props;
 
    public Sender(String username, String password) {
        this.username = username;
        this.password = password;
 
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
    }
 
    public void send(String subject, String text, String fromEmail, String toEmail){
        Session session = Session.getInstance(props, new GMailAuthenticator(username, password));
 
        try {
            Message message = new MimeMessage(session);
            //�� ����
            message.setFrom(new InternetAddress(fromEmail));
            //����
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //���� ���������
            message.setSubject(subject);
            //�����
            message.setText(text);
            Transport transport = session.getTransport("smtp");
            //���������� ���������
            transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}