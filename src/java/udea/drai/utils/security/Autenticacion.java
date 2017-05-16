package udea.drai.utils.security;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Autenticacion extends Authenticator {

    private String user = null;
    private String password = null;

    public Autenticacion(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.user, this.password);
    }
}

