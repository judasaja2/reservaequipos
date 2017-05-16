package udea.drai.control.servlet;

import udea.drai.model.dao.impl.EMailDAOJdbc;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import udea.drai.model.dto.EMail;

public class SendEMail extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EMail mensaje = (EMail) request.getAttribute("email");
        EMailDAOJdbc enviador = new EMailDAOJdbc();
        enviador.sendTextPlane(mensaje);
    }

    public String getServletInfo() {
        return "Servlet para el envio de correos electronicos mediante el servidor SMTP jaibana";
    }
}
