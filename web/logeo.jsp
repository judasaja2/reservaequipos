<%-- 
    Document   : logeo
    Created on : 16/11/2010, 06:46:57 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresar</title>
        <link rel="icon" href="Imagenes/LogoUdea.png" type="image/x-icon" />
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/BitInt.js"></script>
        <script type="text/javascript" src="js/encrypting.js"></script>
    </head>
    
    <body oncontextmenu="return false"> 

        <div id="Cabecera">
            <form name="imagen">
                <p align="center"><a><img src="Imagenes/banner.png"  align="middle"></a></p>
            </form>
        </div>
        <div align="center"  style="background-color: #FFFFFF">
            <table width="800" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">

            <b><p align="center">Si eres estudiante de la Facultad de ingeniería identifícate con tu 'USUARIO' y 'CLAVE' del portal.</p></b><br>
                <tr>
                    <td colspan="2" height="275" valign="top">
                        <p align="left">
    <%-- FORM LOGIN --%>
                        <form id="logeo" name="logeo" method="post" action="Login">
                            <table width="49%" border="0" cellspacing="0" cellpadding="0" align="center">
                                <tr bordercolor="#bebebe">
                                    <td colspan="4"  bgcolor="#bebebe">
                                        <div align="center">
                                            <font color="#000000" face="Arial, Helvetica, sans-serif" size="2">
                                                <b>Inicio de Sesi&oacute;n </b>
                                            </font>
                                        </div>              
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td width="1%" height="36" bordercolor="#bebebe">
                                        <font color="#006633" face="Arial, Helvetica, sans-serif" size="2"></font>
                                    </td>
                                    <td width="36%" height="36">
                                        <div align="right" class="style4">
                                            <font color="#006633">Usuario:</font>
                                        </div>
                                    </td>                                   
                                    <td width="61%" height="36">
                                        <div align="left">
                                            <input type="text" name="usuario" id="usuario" size="15" maxlength="20">
                                        </div>              
                                    </td>
                                    <td width="2%" height="36" bordercolor="#bEBEBE">&nbsp;</td>
                                </tr>
                                
                                <tr>
                                    <td width="1%" height="2" rowspan="2">&nbsp;</td>
                                    <td width="36%" height="1"><div align="right" class="style4"><font color="#006633">Clave:</font></div></td>
                                    <td width="61%" height="1">
                                        <div align="left">  
                                            <input id="clave" name="clave" type="password" size="15"/>
                                        </div>              
                                    </td>
                                    <td width="2%" height="2" rowspan="2">&nbsp;</td>
                                </tr>
                                
                                <tr>
                                    <td colspan="2" height="43">
                                        <div align="center">
                                            <input type="submit" name="Submit" id="Ingresar" value="Ingresar"/>
                                        </div>
                                    </td>
                                </tr>
                                
                            </table>
                        </form>      
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
<script type="text/javascript">
    $('#logeo').submit(function() {
        $('#clave').val(encrypt($('#clave').val()));
});
</script>