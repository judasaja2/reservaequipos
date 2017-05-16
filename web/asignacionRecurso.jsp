<%-- 
    Document   : usuario
    Created on : 16/11/2010, 08:06:14 PM
    Author     : USER
--%>

<%@page import="udea.drai.model.dao.impl.RecursoDAOJdbc"%>
<%@page import="udea.drai.model.dao.RecursoDAO"%>
<%@page import="udea.drai.model.dto.SoftwareType"%>
<%@page import="udea.drai.control.*"%>
<%@page import="java.util.Vector"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="udea.drai.model.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
String espera = (String)session.getAttribute("espera");
   if (espera != null ){
        String cedula1 = (String)session.getAttribute("cedula1");
        Usuario user1 = (Usuario)session.getAttribute("user1");
        if (espera.equalsIgnoreCase("si")){
            if(cedula1 == null || cedula1 == "" || user1 == null && espera.equalsIgnoreCase("si")){
                response.sendRedirect("index.jsp");
                return;
           }
       }else if (espera.equalsIgnoreCase("no")){
           if(cedula1 == null || cedula1 == "" || user1 == null && espera.equalsIgnoreCase("no")){
                response.sendRedirect("error_1.jsp");
                return;
           }
       }else if (espera.equalsIgnoreCase("error")){
            response.sendRedirect("error.jsp");
                return;
       }
   }else{
        response.sendRedirect("error.jsp");
                return;
   }
   %>

   <%
   String nombre = (String)session.getAttribute("nombre");
   String correo = (String)session.getAttribute("correo");
   RecursoDAO recu = new RecursoDAOJdbc();
   Vector<SoftwareType> tiposSWT = (Vector<SoftwareType>)recu.obtenerTiposDeSoftware();
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reserva</title>
        
        <link rel="icon" href="Imagenes/LogoUdea.png" type="image/x-icon" />
        <!-- JS -->
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="js/RegistrarReserva2.js"></script>
        <!-- CSS -->
        <link type="text/css" href="css/jquery.ui.all.css" rel="stylesheet" />               
        <link type="text/css" href="css/demos.css" rel="stylesheet" />
        
        <script type="text/javascript">
            
            $(function() {
		$( "#date" ).datepicker({ minDate: 0, maxDate: "-0M +1D" });
	});

            jQuery(function($){
   $.datepicker.regional['es'] = {
      closeText: 'Cerrar',
      prevText: '<Ant',
      nextText: 'Sig>',
      currentText: 'Hoy',
      monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
      dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
      weekHeader: 'Sm',
      dateFormat: 'yy-mm-dd',
      firstDay: 1,
      isRTL: false,
      showMonthAfterYear: false,
      yearSuffix: ''};
   $.datepicker.setDefaults($.datepicker.regional['es']);
}); 
        </script>


    </head>
    <body>
        <div id="Cabecera">
            <form name="imagen">
                <p align="center"><a><img src="Imagenes/banner.png"  align="middle"></a></p>
            </form>
        </div>
        <div id="Cuerpo" align="center">
            <h2 style="background-color: #eee"><b><p align="center">Bienvenido <%out.println(nombre); %></p></b></h2>
            
            <form name="Logout" action="Logout" method="POST">
                <h3 style="background: #FFFFFF"> <input type="submit" name="Submit" value="Desconectarme"/></h3>
            </form>
<%--FORM DATOS --%>          
            <form name="ConsultarRecursos"  method="POST" action="ConsultarRecursos">
                <fieldset style="font-size: small">
                    <legend>
                        <h2>Reserva de Equipos Disponibles</h2>
                    </legend>
                    <span>
                        <h3>Software Disponible:</h3>
                    </span>
                    <select name="software" id="software">

                        <option value="">Seleccion</option>
                            <%
                            for(int ii = 0; ii< tiposSWT.size(); ii++){
                                SoftwareType tipo = tiposSWT.elementAt(ii);
                                String code = Integer.toString(tipo.getCodeSWT());
                                String name = tipo.getNameSWT();
                            %>
                        <option value=<%=code%>><%=name%></option>
                            <%}%>
                    </select>
                    <br><br>
                  
                    <span>
                        <h3>Hora:</h3>
                    </span>
                    <select name="hora" id="hora">
                        <option value="">Seleccion</option>
                        <option>06:00-07:55</option>                        
                        <option>08:00-09:55</option>
                        <option>10:00-11:55</option>
                        <option>12:00-13:55</option>
                        <option>14:00-15:55</option>    
                        <option>16:00-17:55</option>     
                        <option>18:00-19:55</option>     
                        <option>20:00-21:55</option>                        
                        <option>22:00-23:55</option>
                        <option>00:00-01:55</option>
                        <option>02:00-03:55</option>
                        <option>04:00-05:55</option>
                    </select>
                    <br><br>
                    
                    <span>
                        <h3>Fecha: (AA/MM/DD)</h3>
                    </span>
                    <input type="text" id="date" name="date" readonly="readonly">                   
                    <br><br>
                    <input type="submit" value="Reservar" name="Consultar" id="Consultar"/>                    
                </fieldset>
            </form>
        </div>
    </body>
</html>
