package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.*;
import  co.edu.poli.paradigmas.tc.proyecto.negocio.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Sistema Gestion Transporte Publico ===");
            System.out.println("1. Gestionar Pasajeros");
            System.out.println("2. Gestionar Vehiculos");
            System.out.println("3. Gestionar Rutas");
            System.out.print("Seleccione una opción: ");
            opcion = excepciones(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    MenuGestorPasajeros.mostrarMenuPasajeros(scanner);
                    break;
                case 2:
                    MenuGestorVehiculos.mostrarMenuVehiculos(scanner);
                    break;
                case 3:
                    MenuGestorRutas.mostrarMenuRutas(scanner);
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 4);
    }

    public static int excepciones (Scanner scanner){
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
