package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;
import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestionarVehiculos;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorRutas;

import java.util.Scanner;

public class MenuGestorVehiculos {
    public static void mostrarMenuVehiculos(Scanner scanner) {
        GestionarVehiculos gestor = new GestionarVehiculos();
        GestorRutas gestorRuta = new GestorRutas();
        boolean volver = false;

        while (!volver) {
            System.out.println("=== Menu Vehiculos ===");
            System.out.println("1. Agregar Vehiculo");
            System.out.println("2. Buscar Vehiculo");
            System.out.println("3. Actualizar Estado Vehiculo");
            System.out.println("4. Eliminar Vehiculo");
            System.out.println("5. Mostrar todos los Vehiculos");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            int opcion = excepciones(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el numero de placa: ");
                    String placa = scanner.nextLine();

                    System.out.print("Ingrese el modelo del vehiculo: ");
                    String modelo = scanner.nextLine();

                    System.out.print("Ingrese el numero de pasajeros: ");
                    byte numeroPasajeros = scanner.nextByte();
                    scanner.nextLine();

                    System.out.print("Ingrese la ruta por su ID: ");
                    long idRuta = scanner.nextLong();

                    Rutas rutaSeleccionada = gestorRuta.buscarRutaPorID(idRuta);
                    System.out.print("¿Esta disponible el vehiculo? (true/false): ");
                    boolean disponibilidadVehiculo = scanner.nextBoolean();
                    scanner.nextLine();

                    System.out.print("¿Esta disponible el conductor? (true/false): ");
                    boolean disponibilidadConductor = scanner.nextBoolean();
                    scanner.nextLine();

                    Vehiculo vehiculo = new Vehiculo(placa, modelo, numeroPasajeros, rutaSeleccionada, disponibilidadVehiculo, disponibilidadConductor);

                    gestor.agregarVehiculo(vehiculo);
                    System.out.println("Vehiculo agregado correctamente.");
                    break;

                case 2:
                    System.out.print("Ingrese el numero de placa del vehiculo: ");
                    String placaBuscar = scanner.nextLine();

                    Vehiculo vehiculoEncontrado = gestor.buscarVehiculo(placaBuscar);

                    if (vehiculoEncontrado != null) {
                        System.out.println("Vehiculo encontrado:");
                        System.out.println("Numero de placa: " + vehiculoEncontrado.getNumeroPlaca());
                        System.out.println("Modelo: " + vehiculoEncontrado.getModelo());
                        System.out.println("Numero de pasajeros: " + vehiculoEncontrado.getNumeroPasajeros());
                        System.out.println("Disponibilidad del vehiculo: " + vehiculoEncontrado.isDisponibilidad());
                        System.out.println("Disponibilidad del conductor: " + vehiculoEncontrado.isDisponibilidadConductor());
                    } else {
                        System.out.println("Vehiculo no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el numero de placa del vehiculo: ");
                    String placaActualizar = scanner.nextLine();

                    System.out.print("¿El vehiculo esta en el taller? (true/false): ");
                    boolean estaEnTaller = scanner.nextBoolean();
                    scanner.nextLine();

                    System.out.print("¿El conductor esta disponible? (true/false): ");
                    disponibilidadConductor = scanner.nextBoolean();
                    scanner.nextLine();

                    boolean actualizado = gestor.actualizarVehiculo(placaActualizar, estaEnTaller, disponibilidadConductor);
                    if (actualizado) {
                        System.out.println("Disponibilidad del vehiculo y del conductor actualizada.");
                    } else {
                        System.out.println("No se encontro un vehiculo con esa placa.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el numero de placa del vehiculo que quiere eliminar: ");
                    String placaEliminar = scanner.nextLine();

                    boolean eliminado = gestor.eliminarVehiculo(placaEliminar);
                    if (eliminado) {
                        System.out.println("Vehículo eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró el vehículo con esa placa.");
                    }
                    break;

                case 5:
                    gestor.mostrarVehiculos();
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
