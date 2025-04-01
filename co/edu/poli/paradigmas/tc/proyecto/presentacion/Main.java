package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.*;

public class Main {
    public static void main(String[] args) {
        Conductor miConductor = new Conductor(true, true, "Principal", "Juan", 12023939);
        miConductor.CambiarEstadoLicencia();
        miConductor.CambiarEstadoLicencia();
        miConductor.CambiarDisponibilidad();
        miConductor.CambiarDisponibilidad();

        String[] horarios=new String[1];
        horarios[0]="7:00am";

        Rutas ruta1= new Rutas(01, "Soacha", "Ricaute",58, horarios);
        ruta1.mostrar();
    }
}
