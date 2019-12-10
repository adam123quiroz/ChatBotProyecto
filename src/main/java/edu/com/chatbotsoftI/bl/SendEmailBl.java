package edu.com.chatbotsoftI.bl;

import com.itextpdf.zugferd.PdfInvoiceBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class SendEmailBl {

    private JavaMailSender javaMailSender;
    private PdfInvoiceBasic pdfInvoiceBasic;

    @Autowired
    public SendEmailBl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        pdfInvoiceBasic = new PdfInvoiceBasic();
    }

    public void sendMail(String from, String to, String  subject, String body) {

        String smtpHost = "smtp.mailgun.org"; //replace this with a valid host
        int smtpPort = 587; //replace this with a valid port

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.setProperty("mail.smtp.user", "postmaster@sandboxd7c0d74150234601ba2c3420a06d288e.mailgun.org ");
        properties.setProperty("mail.smtp.password", "d71e2d6025efcd0f257beb6fe330be96-5645b1f9-7891f081");
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = "postmaster@sandboxd7c0d74150234601ba2c3420a06d288e.mailgun.org";
                String password = "d71e2d6025efcd0f257beb6fe330be96-5645b1f9-7891f081";
                if ((username != null) && (username.length() > 0) && (password != null)
                        && (password.length   () > 0)) {

                    return new PasswordAuthentication(username, password);
                }

                return null;
            }
        });

        ByteArrayOutputStream outputStream = null;
        try {
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(body);

            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            pdfInvoiceBasic.createPdf(outputStream);
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
            InternetAddress iaSender = new InternetAddress(to);
            InternetAddress iaRecipient = new InternetAddress(from);

            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);

            //send off the email
            Transport.send(mimeMessage);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
