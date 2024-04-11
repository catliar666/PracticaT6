package comunication;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

import static jakarta.mail.Transport.send;

public class Mensajes {
    private static final String host = "smtp.gmail.com";
    private static final String user = "maria.ordonez.1912@fernando3martos.com";
    private static final String pass = "uotu urtj rcda bspx";




    public static boolean enviarMensaje(String destino, String asunto, String mensaje){
        // Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


// Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try{
            //Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);

            //En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));

            //Establecemos el asunto
            message.setSubject(asunto);

            message.setFrom(new InternetAddress(user));

            //Añadimos el contenido del mensaje
            /*message.setText(mensaje);*/ //Para enviar texto plano
            message.setContent(mensaje, "text/html; charset=utf-8");

            //Intentamos mandar el mensaje
            send(message);

        } catch (Exception e){ //Si entra aquí hemos tenido fallo
            throw new RuntimeException(e);
        }

        return true;
    }
}
