<%-- 
    Document   : Despedida
    Created on : 20/01/2011, 02:28:48 PM
    Author     : juanc
--%>

<%@page import="sun.font.Script"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>U de A</title>
        <link rel="icon" href="Imagenes/LogoUdea.png" type="image/x-icon" />
    </head>

    <body>
        <div id="Cabecera">
            <form name="imagen">
                <p align="center"><a><img src="Imagenes/banner.png"  align="middle"></a></p>
            </form>
        </div>
        <div id="cuerpo">
            <h1><p align="center">DRAI</p></h1>
            <h1><p align="center">Ha Finalizado con exito su sesión</p></h1><br><br>
        </div>
        
        <script>            
            window.setTimeout("window.open('index.jsp', '_self')", 1000);
            history.go(1);
        </script>

    </body>
</html>
