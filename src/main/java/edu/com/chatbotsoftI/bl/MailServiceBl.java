package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.EveUserRepository;
import edu.com.chatbotsoftI.dto.Mail;
import edu.com.chatbotsoftI.dto.MailCfg;
import edu.com.chatbotsoftI.dto.UserDto;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceBl {

       // private EveUserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    //pertenece al mailsender es la configuracion
//    private MailCfg mailCfg;
//
//    private Mail mail;
//
//    public void sendMail(MailCfg mailCfg)
//    {
//        this.mailCfg= mailCfg;
//    //    this.mail=mail;
//    }



    public void sendEmail(String to, String subject, String content ){

        /*JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.mailCfg.getHost());
        mailSender.setPort(this.mailCfg.getPort());
        mailSender.setUsername(this.mailCfg.getUsername());
        mailSender.setPassword(this.mailCfg.getPassword());
*/
        SimpleMailMessage mailmessage = new SimpleMailMessage();

        mailmessage.setFrom("welldone@example.com");

        mailmessage.setTo(to);
        mailmessage.setSubject(subject);
        mailmessage.setText(content);
//        mailmessage.setTo("cr@feedback.com");
//        mailmessage.setSubject("Email de prueba "+ mail.getSubject());
//        mailmessage.setText("Funciono el correo"+ mail.getContent());

        mailSender.send(mailmessage);
    }

    /*public void sendEmail(List<EveUserEntity> listusers, String subject, String content){

        for (EveUserEntity userEntity: listusers){

            SimpleMailMessage email= new SimpleMailMessage();

            email.setTo(userEntity.getEmail());
            email.setSubject(subject);
            email.setText(content);

           mailSender.send(email);
        }
    */

}



