<%-- 
    Document   : asignacionDeTurno
    Created on : 16/11/2010, 09:01:11 PM
    Author     : USER
--%>

<%@page import="udea.drai.model.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
String datos = (String)session.getAttribute("nodatos");
   if (datos != null){
      response.sendRedirect("NoDatos.jsp");
      datos = null;
      return;
   }
    String cedula1 = (String)session.getAttribute("cedula1");
    Usuario user1 = (Usuario)session.getAttribute("user1");

   if(cedula1 == null || cedula1 == "" || user1 == null){
        response.sendRedirect("index.jsp");
        return;
   }%>

   <%
   String hora = (String)session.getAttribute("hora");
   String fecha = (String)session.getAttribute("fecha");
   String correo = (String)session.getAttribute("correo");
   String nombre = (String)session.getAttribute("nombre");
   String estado = (String)session.getAttribute("estado");
   String mail = (String)session.getAttribute("correo");
    %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignación de turno</title>
        <link rel="icon" href="Imagenes/LogoUdea.png" type="image/x-icon" />
    </head>
    <body>
        <div id="Cabecera">
            <form name="imagen">
                <p align="center"><a><img src="Imagenes/banner.png"  align="middle"></a></p>
            </form>
        </div>
        <div align="center">
            
            <div id="cuerpo">
            
            <h2 style="background-color: #eee"><b><p align="center">Bienvenido <%out.println(nombre); %></p></b></h2>
            <form name="Logout"  method="POST" action="Logout">
                <h3 style="background: #FFFFFF"> <input type="submit" name="Submit" value="Desconectarme"/></h3>
            </form>
            <h1><p align="center">DRAI</p></h1>
            <h1><p align="center">Sistema de acceso remoto a las salas de cómputo</p></h1><br>
            <b><p align="center">Su reserva ha sido <%out.println(estado); %></p></b>
            <% if (mail != "no") { %>
            <b><p align="center">FECHA: <%out.println(fecha); %></p></b>
            <b><p align="center">HORA: <%out.println(hora); %></p></b>
            
            <b><p align="center">Los datos precisos y las instrucciones para conectarse se han enviado a su correo registrado en la Universidad:</p></b>
             <b><p align="center">Correo: <%out.println(correo); %></p></b><br>
             <b><p align="center">(Si no aparece en su bandeja de entrada, por favor revise la carpeta de Correo no deseado)</p></b>
            <%} session.setAttribute("noMail","si");%>
        </div>
        </div>
    </body>
</html>
