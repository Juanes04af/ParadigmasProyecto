package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;
import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorVehiculos;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorRutas;

import java.util.Scanner;

public class MenuGestorVehiculos {
    public static void mostrarMenuVehiculos(Scanner scanner, GestorVehiculos gestor) {
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
                    String placa = excepcionesString(scanner);

                    System.out.print("Ingrese el modelo del vehiculo: ");
                    String modelo = excepcionesString(scanner);

                    System.out.print("Ingrese el numero de pasajeros: ");
                    byte numeroPasajeros = excepcionesByte(scanner);

                    System.out.print("Ingrese la ruta por su ID: ");
                    long idRuta = excepcionesLong(scanner);

                    Rutas rutaSeleccionada = gestorRuta.buscarRutaPorID(idRuta);
                    System.out.print("¿Esta disponible el vehiculo? (true/false): ");
                    boolean disponibilidadVehiculo = excepcionesBoolean(scanner);

                    System.out.print("¿Esta disponible el conductor? (true/false): ");
                    boolean disponibilidadConductor = excepcionesBoolean(scanner);

                    Vehiculo vehiculo = new Vehiculo(placa, modelo, numeroPasajeros, rutaSeleccionada, disponibilidadVehiculo, disponibilidadConductor);

                    gestor.agregarVehiculo(vehiculo);
                    System.out.println("Vehiculo agregado correctamente.");
                    break;

                case 2:
                    System.out.print("Ingrese el numero de placa del vehiculo: ");
                    String placaBuscar = excepcionesString(scanner);

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
                    String placaActualizar = excepcionesString(scanner);

                    System.out.print("¿El vehiculo esta en el taller? (true/false): ");
                    boolean estaEnTaller = excepcionesBoolean(scanner);

                    System.out.print("¿El conductor esta disponible? (true/false): ");
                    disponibilidadConductor = excepcionesBoolean(scanner);

                    boolean actualizado = gestor.actualizarVehiculo(placaActualizar, estaEnTaller, disponibilidadConductor);
                    if (actualizado) {
                        System.out.println("Disponibilidad del vehiculo y del conductor actualizada.");
                    } else {
                        System.out.println("No se encontro un vehiculo con esa placa.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el numero de placa del vehiculo que quiere eliminar: ");
                    String placaEliminar = excepcionesString(scanner);

                    boolean eliminado = gestor.eliminarVehiculo(placaEliminar);
                    if (eliminado) {
                        System.out.println("Vehiculo eliminado correctamente.");
                    } else {
                        System.out.println("No se encontro el vehiculo con esa placa.");
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

    public static long excepcionesLong(Scanner scanner) {
        long numero = 0;
        boolean valido = false;
        while (!valido) {
            String entrada = scanner.nextLine();
            if (entrada.matches("\\d+")) { // Solo acepta numeros positivos
                try {
                    numero = Long.parseLong(entrada);
                    valido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Número demasiado grande. Intente nuevamente:");
                }
            } else {
                System.out.println("Entrada inválida. Solo se permiten números. Intente nuevamente:");
            }
        }
        return numero;
    }

    public static boolean excepcionesBoolean(Scanner scanner) {
        boolean valor = false;
        boolean valido = false;
        while (!valido) {
            String entrada = scanner.nextLine().trim().toLowerCase(); // Limpia espacios y pasa a minusculas
            if (entrada.equals("true") || entrada.equals("false")) {
                valor = Boolean.parseBoolean(entrada);
                valido = true;
            } else {
                System.out.println("Entrada inválida. Escriba 'true' o 'false':");
            }
        }
        return valor;
    }

    public static byte excepcionesByte(Scanner scanner) {
        byte valor = 0;
        boolean valido = false;

        while (!valido) {
            String entrada = scanner.nextLine().trim();
            try {
                int temp = Integer.parseInt(entrada);
                if (temp >= Byte.MIN_VALUE && temp <= Byte.MAX_VALUE) {
                    valor = (byte) temp;
                    valido = true;
                } else {
                    System.out.println("Número fuera del rango de byte (-128 a 127). Intente de nuevo:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número válido para byte:");
            }
        }

        return valor;
    }

}
