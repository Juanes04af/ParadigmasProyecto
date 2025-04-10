package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.*;
import  co.edu.poli.paradigmas.tc.proyecto.negocio.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorRutas gestor = new GestorRutas();
        Conductor miConductor = new Conductor(true, true, "Principal", "Juan", 12023939);
        miConductor.CambiarEstadoLicencia();
        miConductor.CambiarEstadoLicencia();
        miConductor.CambiarDisponibilidad();
        miConductor.CambiarDisponibilidad();

        String[] horarios = new String[1];
        horarios[0] = "7:00am";

        Rutas ruta1 = new Rutas(01, "Soacha", "Ricaute", 58, horarios);
        gestor.agregarRuta(ruta1);
        gestor.mostrarRutas();
        Rutas ruta2 = new Rutas(2, "Cali", "Cartagena", 800, new String[]{"06:00", "20:00"});
        gestor.agregarRuta(ruta2);
    }

    public static int excepciones (Scanner scanner) {
        int opcion = -1;
        boolean valido = false;
        while (!valido) {
            try {
                opcion = scanner.nextInt();
                valido = true;
            } catch (Exception e) {
                System.out.println("Invalido, Intente de nuevo");
                scanner.nextLine();
            }
        }
        return opcion;
    }
}
