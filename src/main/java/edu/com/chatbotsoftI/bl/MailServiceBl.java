package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dto.Mail;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class  MailServiceBl {

    // private EveUserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    private Mail mail;

    public void sendEmail(String from, String to, String subject, String content) {

        /*JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.mailCfg.getHost());
        mailSender.setPort(this.mailCfg.getPort());
        mailSender.setUsername(this.mailCfg.getUsername());
        mailSender.setPassword(this.mailCfg.getPassword());
*/
        SimpleMailMessage mailmessage = new SimpleMailMessage();

        mailmessage.setFrom(from);
        mailmessage.setTo(to);
        mailmessage.setSubject(subject);
        mailmessage.setText(content);


       mailSender.send(mailmessage);
    }

    public void sendAllEmail(String from, String[] to, String subject, String content) throws MessagingException {

        Properties p=System.getProperties();
        String Host_Name= "smtp.mailtrap.io";
        p.put("mail.smtp.host",Host_Name);
        Session session=Session.getDefaultInstance(p,null);
        MimeMessage message=new MimeMessage(session);

        List<EveUserEntity> mailAddressto = new ArrayList<>();
            message.setFrom(from);

        //InternetAddress[] mailto=new InternetAddress[mailAddressto.size()];
        InternetAddress[] mailto =new InternetAddress[mailAddressto.size()];
        for (int i=0;i<mailAddressto.size();i++){
            mailto[i]=new InternetAddress(mailAddressto.get(i).getEmail());
        }
        message.addRecipients(Message.RecipientType.TO,mailto);
        message.setSubject(subject);
        message.setContent(content,"txt");
        mailSender.send(message);
//        //    SimpleMailMessage email = new SimpleMailMessage();
//
//            email.setFrom(from);
//            email.setTo(String.valueOf(to));
//            email.setSubject(subject);
//            email.setText(content);
//
//            mailSender.send(email);


    }
    public void sendAllEmailList(String from,List<EveUserEntity> listusers, String subject, String content) {


        for (EveUserEntity userEntity : listusers) {

            SimpleMailMessage email = new SimpleMailMessage();

            email.setFrom(from);
            email.setTo(userEntity.getEmail());
            email.setSubject(subject);
            email.setText(content);

            mailSender.send(email);
        }

    }


}



