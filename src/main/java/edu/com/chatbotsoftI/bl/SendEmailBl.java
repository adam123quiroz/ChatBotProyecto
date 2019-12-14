package edu.com.chatbotsoftI.bl;

import com.sun.mail.smtp.SMTPTransport;
import edu.com.chatbotsoftI.invoice.PdfInvoiceBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

@Service
public class SendEmailBl {
    private PdfInvoiceBasic pdfInvoiceBasic;

    @Autowired
    public SendEmailBl() {
        pdfInvoiceBasic = new PdfInvoiceBasic();
    }

    public void sendMail(String from, String to, String  subject, String body) {

        String mailHost = "smtp.mailgun.org";
        String username1 = "postmaster@sandboxd3b9f22ed60c4a5ba063c9837031c9e4.mailgun.org";
        String password1 = "76567937db4e18afd6e41b4464a5be3f-5645b1f9-a6404435";

        String smtpHost = mailHost; //replace this with a valid host
        int smtpPort = 587; //replace this with a valid port

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);



        properties.setProperty("mail.smtp.user", username1);
        properties.setProperty("mail.smtp.password", password1);
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties,null);

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
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("adam123quiroz@gmail.com"));
            message.setSubject(subject);
            message.setRecipient(Message.RecipientType.TO, iaRecipient);
            message.setContent(mimeMultipart);

            SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
            t.connect(smtpHost, username1, password1);
//            t.sendMessage();
            //send off the email
            Transport.send(message);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
