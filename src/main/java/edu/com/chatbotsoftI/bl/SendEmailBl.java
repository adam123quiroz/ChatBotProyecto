package edu.com.chatbotsoftI.bl;


import edu.com.chatbotsoftI.auxiliar.InvoiceMaker;
import com.sun.mail.smtp.SMTPTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.util.Properties;

@Service
public class SendEmailBl {


    private InvoiceMaker invoiceMaker;

    @Autowired
    public SendEmailBl() {
        invoiceMaker = new InvoiceMaker();
    }

    public void sendMail(String from, String to, String  subject, String body) {

        String mailHost = "smtp.gmail.com";
        String username = "boltonbot2019@gmail.com";
        String password = "software2019";

        String smtpHost = mailHost; //replace this with a valid host
        int smtpPort = 465; //replace this with a valid port

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);


        properties.setProperty("mail.smtp.user", username);
        properties.setProperty("mail.smtp.password", password);
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties,null);

        ByteArrayOutputStream outputStream = null;
        try {
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(body);

            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            invoiceMaker.createPdf(outputStream);
            byte[] bytes = outputStream.toByteArray();

            //construct the pdf body part
            ByteArrayDataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf");

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            //create the sender/recipient addresses
            InternetAddress iaRecipient = new InternetAddress(to);

            //construct the mime message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("adam123quiroz@gmail.com"));
            message.setSubject(subject);
            message.setRecipient(Message.RecipientType.TO, iaRecipient);
            message.setContent(mimeMultipart);

            SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
            t.connect(smtpHost, username, password);
//            t.sendMessage();
            //send off the email
            t.sendMessage(message, message.getAllRecipients());

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
