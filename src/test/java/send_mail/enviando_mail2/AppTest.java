package send_mail.enviando_mail2;

import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testMail() throws Exception {

        // Construção do texto do e-mail
    	
    	StringBuilder stringBuilderMailText = new StringBuilder();
    	stringBuilderMailText.append("Olá, <br/><br/>");
    	stringBuilderMailText.append("Este é um teste de envio de e-mail utilizando Java.<br/><br/>");
    	stringBuilderMailText.append("Atenciosamente, myself <br/><br/>");
    	stringBuilderMailText.append("<a target=\"_blank\" href=\"https://github.com/MartinsCb\" style=\"color:violet; padding:15px 25px; text-align:center; text-decoration:none; display:inline-block; border-radius:25px; font-size:20px; font-family:Courier; border:2px solid blue;\">Acessar GitHub</a>");


        // Configurando e enviando o e-mail
        objectSendMail sendMail = new objectSendMail(
            "andremartins.a@outlook.com", // Destinatário
            "Myself",                    // Nome do remetente
            "Teste de Envio",            // Assunto do e-mail
            stringBuilderMailText.toString() // Corpo do e-mail
        );

        sendMail.objectSendMail(true); // Enviar e-mail
    }
}
