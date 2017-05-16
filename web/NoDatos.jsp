<%-- 
    Document   : error
    Created on : 26/01/2011, 12:35:13 PM
    Author     : juanc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
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
            <h1><p align="center">No existen Equipos disponibles en este horario, por favor intentelo con otro horario</p></h1><br>

        </div>
        <script>

            window.setTimeout("window.open('usuario.jsp', '_self')", 9000);
            history.go(1);
        </script>
    </body>
</html>
