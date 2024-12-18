package send_mail.enviando_mail2;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class objectSendMail {

    private String userName = "andredeveloperjava@gmail.com";
    private String password = "rlhp ggez xotk xbbo"; // Certifique-se de usar senha de aplicativo!

    private String listRecipient;
    private String nameSender;
    private String mailContent;
    private String textMail;

    public objectSendMail(String listRecipient, String nameSender, String mailContent, String textMail) {
        this.listRecipient = listRecipient;
        this.nameSender = nameSender;
        this.mailContent = mailContent;
        this.textMail = textMail;
    }

    public void objectSendMail(boolean isHTML) throws Exception {
        try {
            Properties properties = new Properties();

            properties.put("mail.smtp.ssl.trust", "*");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            });

            Address[] toUser = InternetAddress.parse(listRecipient);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName, nameSender));
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(mailContent);

            // Parte do texto (HTML ou texto simples)
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            if (isHTML) {
                messageBodyPart.setContent(textMail, "text/html; charset=utf-8");
            } else {
                messageBodyPart.setText(textMail);
            }

            // Criar e anexar arquivos PDF
            
            List<File> pdfFiles = new ArrayList<>();
            
            pdfFiles.add(generatePDF("Conteúdo do PDF de exemplo 1"));
            pdfFiles.add(generatePDF("Conteúdo do PDF de exemplo 2")); // Adicione mais PDFs, se necessário.

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            for (File pdfFile : pdfFiles) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(pdfFile);
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("E-mail enviado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private File generatePDF(String content) throws Exception {
        Document document = new Document();
        
        File pdfFile = new File("anexo" + System.currentTimeMillis() + ".pdf");
        
        PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        document.open();
        document.add(new Paragraph(content));
        document.close();
        return pdfFile;
    }
}
