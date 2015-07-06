package application;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;  
import java.util.logging.Level;  
import java.util.logging.Logger;  
import javax.mail.Authenticator;   
import javax.mail.PasswordAuthentication;    

public class Email {
	//http://codesstore.blogspot.pt/2011/10/send-email-in-java-with-gmail.html
	//mensagens para gmail
	private static String SMTP_HOST = "smtp.gmail.com";  
    private static String FROM_ADDRESS = "blocodenotasvigion@gmail.com";  
    private static String PASSWORD = "P@ssC0de";  
    private static String FROM_NAME = "Bloco de Notas";  
    
    
    //ESTA FUNCAO SERVE APENAS PARA O REMETENTE MANDAR O EMAIL PELO GMAIL (mas pode enviar para qualquer outro email)
    public static boolean sendMail(Nota nota, Utilizador user) {  
        try {  
            Properties props = new Properties();  
            props.put("mail.smtp.host", SMTP_HOST);  
            props.put("mail.smtp.auth", "true");  
            props.put("mail.debug", "false");  
            props.put("mail.smtp.ssl.enable", "true");  
  
            //cria uma sessao
            Session session = Session.getInstance(props, new SocialAuth());  
            Message msg = new MimeMessage(session);  
  
            //define quem envia, definido pela variavei estaticas
            InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);  
            msg.setFrom(from);  
  
            //insere o destinatario ou cc
            InternetAddress toAddresses = new InternetAddress();             
            toAddresses = new InternetAddress(user.getEmail());   
            msg.setRecipient(Message.RecipientType.TO, toAddresses);  
  
            //insere o bcc
            InternetAddress bccAddresses = new InternetAddress(user.getEmail());   
            msg.setRecipient(Message.RecipientType.BCC, bccAddresses);  
  
	         // Set Subject: header field
	         msg.setSubject("Bloco de Notas apagou: " + nota.getTitulo());

	         // Now set the actual message
	         msg.setText("Tem uma recuperação da sua nota,\n"
	        		 + nota.getTitulo() + " cor hexadecimal: " + nota.getCor() + "\n"
	        		 + "Criada por: " + nota.getAutor() + " em " + nota.getData() + "\n"
	        		 + "  ---- * ----\n" + nota.getConteudo());
	         
            Transport.send(msg);  
            return true;  
        } catch (UnsupportedEncodingException ex) {  
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);  
            return false;  
  
        } catch (MessagingException ex) {  
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);  
            return false;  
        }  
    }  
  
    static class SocialAuth extends Authenticator {  
  
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
  
            return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);  
  
        }  
    }  
    
	//importado de http://www.tutorialspoint.com/java/java_sending_email.htm
	//adaptado e alterado
	static void EnviarMensagem(Nota nota, Utilizador user)
	{
		 // Recipient's email ID needs to be mentioned.
	      String to = user.getEmail();

	      // Sender's email ID needs to be mentioned
	      String from = "davifs7@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      
	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("Bloco de Notas apagou: " + nota.getTitulo());

	         // Now set the actual message
	         message.setText("Tem uma recuperação da sua nota,\n"
	        		 + nota.getTitulo() + " cor hexadecimal: " + nota.getCor() + "\n"
	        		 + "Criada por: " + nota.getAutor() + " em " + nota.getData() + "\n"
	        		 + "  ---- * ----\n" + nota.getConteudo());

	         // Send message
	         Transport.send(message);
	         System.out.println("Mensagem enviada com sucesso!");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
