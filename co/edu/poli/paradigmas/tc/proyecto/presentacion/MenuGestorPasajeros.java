package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.Pasajeros;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorPasajeros;
import java.util.Scanner;

public class MenuGestorPasajeros {
    public static void mostrarMenuPasajeros(Scanner scanner, GestorPasajeros gestorPasajeros) {

        boolean volver = false;
        int contador = 1;
        while (!volver) {
            System.out.println("=== Menu Pasajeros ===");
            System.out.println("1. Agregar pasajero.");
            System.out.println("2. Buscar pasajeros.");
            System.out.println("3. Actualizar pasajero.");
            System.out.println("4. Eliminar pasajero.");
            System.out.println("5. Mostrar todos los pasajeros.");
            System.out.println("6. Mostrar Boletos de pasajero por pasajero.");
            System.out.println("0. Volver al menu principal.");
            System.out.print("Seleccione una opción: ");
            int opcion = excepciones(scanner);
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    int id;
                    id=contador++;

                    System.out.print("Ingrese el nombre del pasajero: ");
                    String nombre = excepcionesString(scanner);

                    Pasajeros pasajero = new Pasajeros(id, nombre);

                    gestorPasajeros.agregarPasajero(pasajero);
                    System.out.println("Pasajero agregado exitosamente.");
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("Ingrese el ID del pasajero que desea buscar: ");
                    id = excepciones(scanner);
                    scanner.nextLine();

                    Pasajeros pasajeroEncontrado = gestorPasajeros.buscarPasajeroPorId(id);
                    if (pasajeroEncontrado != null) {
                        System.out.println("Pasajero encontrado:");
                        System.out.println("ID: " + pasajeroEncontrado.getNumeroID());
                        System.out.println("Nombre: " + pasajeroEncontrado.getNombre());
                        System.out.println("Pulse 'enter' para continuar.");
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el ID del pasajero a actualizar: ");
                    int idActualizar = excepciones(scanner);
                    scanner.nextLine();
                    pasajeroEncontrado = gestorPasajeros.buscarPasajeroPorId(idActualizar);
                    if (pasajeroEncontrado != null) {
                        System.out.print("Ingrese el nuevo nombre para el pasajero: ");
                        String nuevoNombre = excepcionesString(scanner);
                        gestorPasajeros.actualizarNombrePasajero(idActualizar, nuevoNombre);
                        System.out.println("Pulse 'enter' para continuar.");
                        scanner.nextLine();
                    } else {
                        System.out.println("Pasajero no encontrado.");
                        System.out.println("Pulse 'enter' para continuar.");
                        scanner.nextLine();
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el ID del pasajero que desea eliminar: ");
                    int idEliminar = excepciones(scanner);
                    scanner.nextLine();

                    Pasajeros pasajeroEliminar = gestorPasajeros.buscarPasajeroPorId(idEliminar);
                    if (pasajeroEliminar != null) {
                        gestorPasajeros.eliminarPasajero(idEliminar);
                        System.out.println("Pulse 'enter' para continuar.");
                        scanner.nextLine();
                    } else {
                        System.out.println("Pasajero no encontrado.");
                        System.out.println("Pulse 'enter' para continuar.");
                        scanner.nextLine();
                    }
                    break;
                case 5:
                    gestorPasajeros.mostrarTodosLosPasajeros();
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                    break;
                case 6:
                    System.out.println("Ingrese el ID del pasajero: ");
                    int idBuscar = excepciones(scanner);
                    Pasajeros pasajeroBuscar = gestorPasajeros.buscarPasajeroPorId(idBuscar);
                    if (pasajeroBuscar != null) {
                        pasajeroBuscar.mostrarBoletos();
                        System.out.println("Pulse 'enter' para continuar.");
                        scanner.nextLine();
                    }
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
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

    public static String excepcionesString(Scanner scanner) {
        String valor = "";
        boolean entradaValida = false;

        while (!entradaValida) {
            valor = scanner.nextLine().trim();

            if (valor.isEmpty()) {
                System.out.println("¡La entrada no puede estar vacia! Intente de nuevo.");
            } else if (valor.matches("\\d+")) {
                System.out.println("No puede ingresar solo numeros. Intente de nuevo.");
            } else {
                entradaValida = true;
            }
        }
        return valor;
    }
}
