package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.auxiliar.InvoiceMaker;
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

    private JavaMailSender javaMailSender;

    private InvoiceMaker invoiceMaker;

    @Autowired
    public SendEmailBl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        invoiceMaker = new InvoiceMaker();
    }

    public void sendMail(String from, String to, String  subject, String body) {

        String smtpHost = "smtp.mailtrap.io"; //replace this with a valid host
        int smtpPort = 2525; //replace this with a valid port

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        String username1 = "4b6a8a34d44361";
        String password1 = "2f5624fd2c0d24";

        properties.setProperty("mail.smtp.user", username1);
        properties.setProperty("mail.smtp.password", password1);
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = username1;
                String password = password1;
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
