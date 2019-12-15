package edu.com.chatbotsoftI.bl;

import com.sun.mail.smtp.SMTPTransport;
import edu.com.chatbotsoftI.invoice.PdfInvoiceBasic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

    @Autowired
    public SendEmailBl() {
        pdfInvoiceBasic = new PdfInvoiceBasic();
    }

    public void sendMail(String from, String to, String  subject, String body) {

        String mailHost = "smtp.mailtrap.io";
        String username1 = "4b6a8a34d44361";
        String password1 = "2f5624fd2c0d24";

        String smtpHost = mailHost; //replace this with a valid host
        int smtpPort = 2525; //replace this with a valid port

        Properties properties = System.getProperties();
//        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);



//        properties.setProperty("mail.smtp.user", username1);
//        properties.setProperty("mail.smtp.password", password1);
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


            //construct the mime message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("adam123quiroz@gmail.com"));

            InternetAddress[] address = InternetAddress.parse(from, false);
            message.setRecipients(Message.RecipientType.TO, address);


            message.setSubject(subject);
            message.setText("testing");
            message.setContent(mimeMultipart);

            SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
            t.connect(smtpHost,  username1, password1);
//            t.sendMessage();
            //send off the email
            t.sendMessage(message, message.getAllRecipients());
            LOGGER.info("Send Message {}", t.getLastServerResponse());
            t.close();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
