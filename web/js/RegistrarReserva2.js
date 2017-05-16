/*
 Inicializamos el DOM de la pagina con JQuery.
 */
$(document).ready(function(){
    $("#Consultar").mousemove(casting);
});

/*
 Metodo para limpiar la consulta de recursos disponibles,esto aplica a cambios 
 en el tipo de recurso, la fecha, la hora inicial y final
 */
function casting()
{
    software = $("#software")[0].value;
    hora = $("#hora")[0].value;
    fecha = $("#date")[0].value;
    if(software == "")
    {
        alert("Seleccione la aplicación a la que desea tener acceso, Intentelo de Nuevo");
        return;
    }
    else if(hora == "")
    {
        alert("Elija una hora para la reserva, Intentelo de Nuevo");
        return;
    }
    else if(fecha == ""){
        alert("Elija una fecha para la reserva, Intentelo de Nuevo");
        return;
    }
        
}
