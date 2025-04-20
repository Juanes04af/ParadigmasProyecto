package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.*;
import  co.edu.poli.paradigmas.tc.proyecto.negocio.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        GestorVehiculos gestorVehiculos = new GestorVehiculos();
        GestorTaller gestorTaller = new GestorTaller(gestorVehiculos.obtenerListaVehiculos());
        GestorRutas gestorRutas = new GestorRutas();
        GestorConductores gestorConductores = new GestorConductores();
        do {
            System.out.println("=== Sistema Gestion Transporte Publico ===");
            System.out.println("1. Gestionar Pasajeros");
            System.out.println("2. Gestionar Vehiculos");
            System.out.println("3. Gestionar Rutas");
            System.out.println("4. Gestionar Taller de Mantenimiento");
            System.out.println("5. Gestionar Conductores");
            System.out.print("Seleccione una opción: ");
            opcion = excepciones(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    MenuGestorPasajeros.mostrarMenuPasajeros(scanner);
                    break;
                case 2:
                    MenuGestorVehiculos.mostrarMenuVehiculos(scanner, gestorVehiculos, gestorRutas);
                    break;
                case 3:
                    MenuGestorRutas.mostrarMenuRutas(scanner, gestorRutas);
                    break;
                case 4:
                    MenuGestorTaller.mostrarMenuTaller(scanner, gestorTaller);
                    break;
                case 5:
                    MenuGestorConductores.mostrarMenuConductores(scanner, gestorConductores, gestorVehiculos);
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 6);
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
