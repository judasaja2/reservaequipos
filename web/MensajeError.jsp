<%-- 
    Document   : MensajeError
    Created on : 23-may-2013, 10:32:26
    Author     : Programacion
--%>

<%@page import="udea.drai.utils.LeerMensajes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String error = (String)session.getAttribute("mensaje");
    LeerMensajes prop = new LeerMensajes();
    String mensaje = prop.getValor(error);
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
    </head>
    <body>
        <div id="Cabecera">
            <form name="imagen">
                <p align="center"><a><img src="Imagenes/banner.png"  align="middle"></a></p>
            </form>
        </div>
        <div id="cuerpo">
            <h1><p align="center">DRAI</p></h1>
            <h1><p align="center"><%out.println(mensaje); %></p></h1><br>
        </div>     
        <script>
            window.setTimeout("window.open('index.jsp', '_self')", 9000);
            history.go(1);
        </script>
    </body>
</html>
