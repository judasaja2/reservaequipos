package udea.drai.control;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

public class ManejoFechas {

    private String[] diasSemana = {"", "D", "L", "M", "W", "J", "V", "S"};
    private String[] meses = {"", "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
    private int tamano = 0;
    private int[] seleccionados;

    public int getNumDia(String dia) {
        for (int i = 1; i <= 7; i++) {
            if (this.diasSemana[i].equalsIgnoreCase(dia)) {
                return i;
            }
        }
        return 0;
    }

    public Calendar getReservaInicial(Calendar fechaReferencia, int dia) {
        Calendar calendario = fechaReferencia;
        int diaSemana = calendario.get(7);
        while (diaSemana != dia) {
            calendario.add(5, 1);
            diaSemana = calendario.get(7);
        }
        return calendario;
    }

    public String getDiaPuntual(Calendar fecha) {
        int diaSemana = fecha.get(7);
        return this.diasSemana[diaSemana];
    }

    public String convertirFecha(Calendar fechaCalendar) {
        Calendar calendario = fechaCalendar;
        String anoActual = String.valueOf(fechaCalendar.get(1));
        int mesActual = fechaCalendar.get(2) + 1;
        int dia = fechaCalendar.get(5);
        int diaSemana = fechaCalendar.get(7);
        String mes = "";
        String dias = "";
        if (mesActual < 10) {
            mes = "0" + Integer.toString(mesActual);
        } else {
            mes = Integer.toString(mesActual);
        }
        if (dia < 10) {
            dias = "0" + Integer.toString(dia);
        } else {
            dias = Integer.toString(dia);
        }
        String fecha = anoActual + "-" + mes + "-" + dias;
        return fecha;
    }

    public Vector<Calendar> getFechasXDia(Calendar inicioReserva, Calendar finalReserva, int dia) {
        Vector fechas = new Vector(100, 50);
        Calendar frecorrido = (Calendar) inicioReserva.clone();
        Calendar ffinal = (Calendar) finalReserva.clone();

        ffinal.add(7, 1);
        int diaEvaluado = frecorrido.get(7);

        int controlWhile = 0;
        while (diaEvaluado != dia) {
            frecorrido.add(7, 1);
            diaEvaluado = frecorrido.get(7);
            controlWhile++;
            if (controlWhile > 365) {
                break;
            }
        }

        while (frecorrido.before(ffinal)) {
            Calendar aux = (Calendar) frecorrido.clone();
            fechas.add(aux);

            frecorrido.add(7, 7);
            controlWhile++;
            if (controlWhile > 365) {
                break;
            }
        }
        return fechas;
    }

    public Calendar obtenerCalendar(Calendar original) {
        int anoActual = original.get(1);
        int mesActual = original.get(2);
        int diaActual = original.get(5);
        Calendar calendar = new GregorianCalendar(anoActual, mesActual, diaActual);
        return calendar;
    }

    public boolean sonIguales(Calendar a, Calendar b) {
        if ((a.get(1) == b.get(1)) && (a.get(2) == b.get(2)) && (a.get(5) == b.get(5))) {
            return true;
        }
        return false;
    }

    public Calendar[] calcularFechasParaReservar(String[] dias, Calendar iniReserva, Calendar finReserva) {
        Vector todos = new Vector(100, 50);
        Vector fechasAProgramar = new Vector(100, 50);
        this.tamano = 0;
        for (int i = 0; i < dias.length; i++) {
            String dia1 = dias[i];
            int numDia = getNumDia(dia1);
            Vector fechas = getFechasXDia(obtenerCalendar(iniReserva), obtenerCalendar(finReserva), numDia);
            this.tamano += fechas.size();
            fechasAProgramar.addElement(fechas);
        }
        Calendar[] arreglo = new Calendar[this.tamano];
        int pos = 0;
        for (int i = 0; i < fechasAProgramar.size(); i++) {
            Vector fechas11 = (Vector) fechasAProgramar.elementAt(i);
            for (int j = 0; j < fechas11.size(); j++) {
                Calendar fechaA = (Calendar) fechas11.elementAt(j);
                arreglo[pos] = fechaA;
                pos++;
            }
        }
        arreglo = ordenarFechas(arreglo);

        if (arreglo.length == 0);
        return arreglo;
    }

    public Calendar[] ordenarFechas(Calendar[] fechas) {
        Calendar[] ordenar = fechas;
        boolean ordenado = false;
        while (!ordenado) {
            ordenado = true;
            for (int i = 0; i < ordenar.length - 1; i++) {
                Calendar s = ordenar[i];
                Calendar s1 = ordenar[(i + 1)];
                if (s.after(s1)) {
                    Calendar s2 = s;
                    ordenar[i] = s1;
                    ordenar[(i + 1)] = s2;
                    ordenado = false;
                }
            }
        }
        return ordenar;
    }

    public Calendar establecerFecha(String fecha) {
        Calendar diaReserva;
        try {
            int anoInicial = Integer.parseInt(fecha.substring(6, fecha.length()));
            int mesInicial = Integer.parseInt(fecha.substring(3, 5));
            int diaInicial = Integer.parseInt(fecha.substring(0, 2));
            mesInicial -= 1;

            return new GregorianCalendar(anoInicial, mesInicial, diaInicial);
        } catch (Exception e) {
            int anoInicial = Integer.parseInt(fecha.substring(0, 4));
            int mesInicial = Integer.parseInt(fecha.substring(5, 7));
            int diaInicial = Integer.parseInt(fecha.substring(8, fecha.length()));
            mesInicial -= 1;

            diaReserva = new GregorianCalendar(anoInicial, mesInicial, diaInicial);
        }
        return diaReserva;
    }
}
