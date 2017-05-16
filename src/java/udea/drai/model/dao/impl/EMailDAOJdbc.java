package udea.drai.model.dao.impl;

import java.io.PrintStream;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import udea.drai.utils.security.Autenticacion;
import udea.drai.model.dto.EMail;

public class EMailDAOJdbc {

    private static final ResourceBundle PROPIEDADES = ResourceBundle.getBundle("udea.drai.utils.properties.PropiedadesEMail");
    private Properties propiedadesConexion;

    public EMailDAOJdbc() {
        this.propiedadesConexion = new Properties();
        
        this.propiedadesConexion.setProperty("mail.smtp.starttls.enable", PROPIEDADES.getString("tls"));
        //this.propiedadesConexion.setProperty("mail.smtp.starttls.enable", "true");
        this.propiedadesConexion.put("mail.smtp.host", PROPIEDADES.getString("host"));
        //this.propiedadesConexion.put("mail.smtp.host", "smtp-relay.gmail.com");
        //System.out.println("smtp-relay.gmail.com");
        this.propiedadesConexion.put("mail.smtp.port", PROPIEDADES.getString("puerto"));
        //this.propiedadesConexion.put("mail.smtp.port", "25");
        //this.propiedadesConexion.setProperty("mail.smtp.user", PROPIEDADES.getString("usuario"));
//        this.propiedadesConexion.put("mail.smtp.auth", PROPIEDADES.getString("autenticacion"));
        this.propiedadesConexion.put("mail.smtp.auth", "false");
    }

    public void sendTextPlane(EMail emai) {
        String username = PROPIEDADES.getString("usuario");
        String password = PROPIEDADES.getString("contrasena");

        //Session sesion = Session.getDefaultInstance(this.propiedadesConexion, new Autenticacion(username, password));
        Session sesion = Session.getDefaultInstance(this.propiedadesConexion);
        try {
            MimeMessage msg = new MimeMessage(sesion);
            msg.setFrom(new InternetAddress(PROPIEDADES.getString("usuario"), emai.getFrom()));
            //msg.setFrom(new InternetAddress("aplicacionreservaequiposremotos@udea.edu.co"));
            System.out.println(emai.getTo());
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emai.getTo()));
            msg.setSubject(emai.getSubject());
            msg.setText(emai.getBody());

            Transport t = sesion.getTransport("smtp");
            t.connect();
            //t.connect(username, password);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        } catch (Exception error) {
            System.err.println(error.getStackTrace());
        }
    }
}
