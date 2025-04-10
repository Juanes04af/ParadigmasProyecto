package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.Pasajeros;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorPasajeros;
import java.util.Scanner;

public class MenuGestorPasajeros {
    public static void mostrarMenuPasajeros(Scanner scanner) {
        GestorPasajeros gestor = new GestorPasajeros();
        boolean volver = false;

        while (!volver) {
            System.out.println("=== Menu Pasajeros ===");
            System.out.println("1. Agregar pasajero");
            System.out.println("2. Buscar pasajeros");
            System.out.println("3. Actualizar pasajero");
            System.out.println("4. Eliminar pasajero");
            System.out.println("5. Mostrar todos los pasajeros");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            int opcion = excepciones(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el ID del pasajero: ");
                    int id = excepciones(scanner);
                    scanner.nextLine();

                    System.out.print("Ingrese el nombre del pasajero: ");
                    String nombre = scanner.nextLine();

                    Pasajeros pasajero = new Pasajeros(id, nombre);

                    gestor.agregarPasajero(pasajero);
                    System.out.println("Pasajero agregado exitosamente.");
                    break;

                case 2:
                    System.out.println("Ingrese el ID del pasajero que desea buscar: ");
                    id = excepciones(scanner);
                    scanner.nextLine();

                    Pasajeros pasajeroEncontrado = gestor.buscarPasajeroPorId(id);
                    if (pasajeroEncontrado != null) {
                        System.out.println("Pasajero encontrado:");
                        System.out.println("ID: " + pasajeroEncontrado.getNumeroID());
                        System.out.println("Nombre: " + pasajeroEncontrado.getNombre());
                    } else {
                        System.out.println("Pasajero no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID del pasajero a actualizar: ");
                    int idActualizar = excepciones(scanner);
                    scanner.nextLine();
                    pasajeroEncontrado = gestor.buscarPasajeroPorId(idActualizar);
                    if (pasajeroEncontrado != null) {
                        System.out.print("Ingrese el nuevo nombre para el pasajero: ");
                        String nuevoNombre = scanner.nextLine();
                        gestor.actualizarNombrePasajero(idActualizar, nuevoNombre);
                    } else {
                        System.out.println("Pasajero no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Ingrese el ID del pasajero que desea eliminar: ");
                    int idEliminar = excepciones(scanner);
                    scanner.nextLine();

                    Pasajeros pasajeroEliminar = gestor.buscarPasajeroPorId(idEliminar);
                    if (pasajeroEliminar != null) {
                        gestor.eliminarPasajero(idEliminar);
                        System.out.println("Pasajero eliminado.");
                    } else {
                        System.out.println("Pasajero no encontrado.");
                    }
                    break;

                case 5:
                    gestor.mostrarTodosLosPasajeros();
                    break;

                case 0:
                    volver = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
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
