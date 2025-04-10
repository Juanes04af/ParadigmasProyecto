package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.*;
import  co.edu.poli.paradigmas.tc.proyecto.negocio.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        Conductor miConductor = new Conductor(true, true, "Principal", "Juan", 12023939);
        miConductor.CambiarEstadoLicencia();
        miConductor.CambiarEstadoLicencia();
        miConductor.CambiarDisponibilidad();
        miConductor.CambiarDisponibilidad();

        String[] horarios=new String[1];
        horarios[0]="7:00am";

        Rutas ruta1= new Rutas(01, "Soacha", "Ricaute",58, horarios);
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear pasajero");
            System.out.println("2. Comprar boleto");
            System.out.println("3. Ver viajes");
            System.out.println("4. Eliminar pasajero");
            System.out.println("5. Listar pasajeros");
            System.out.println("6. Editar nombre del pasajero");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = excepciones(scanner);

            switch (opcion) {
                case 1: GestionarPasajeros.crearPasajero(); break;
                case 2: GestionarPasajeros.comprarBoleto(); break;
                case 3: GestionarPasajeros.verViajes(); break;
                case 4: GestionarPasajeros.eliminarPasajero(); break;
                case 5: GestionarPasajeros.listarPasajeros(); break;
                case 6: GestionarPasajeros.editarNombre(); break;
                case 0: System.out.println("¡Hasta luego! Gracias por usar el programa"); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
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
