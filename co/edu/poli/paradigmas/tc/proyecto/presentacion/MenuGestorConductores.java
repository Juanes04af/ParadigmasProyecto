package co.edu.poli.paradigmas.tc.proyecto.presentacion;

import co.edu.poli.paradigmas.tc.proyecto.entities.Conductor;
import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorConductores;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorVehiculos;


import java.util.Scanner;

public class MenuGestorConductores {
    public static void mostrarMenuConductores(Scanner scanner, GestorConductores gestorConductores, GestorVehiculos gestorVehiculos) {
        boolean volver = false;

        while (!volver) {
            System.out.println("=== Menú Conductores ===");
            System.out.println("1. Agregar Conductor.");
            System.out.println("2. Buscar Conductor.");
            System.out.println("3. Añadir Vehiculo a Conductor.");
            System.out.println("4. Eliminar Vehiculo de Conductor.");
            System.out.println("5. Mostrar Vehiculos de Conductor.");
            System.out.println("6. Actualizar Conductor.");
            System.out.println("7. Eliminar Conductor.");
            System.out.println("8. Mostrar todos los Conductores.");
            System.out.println("0. Volver al menú principal.");
            System.out.print("Seleccione una opción: ");
            int opcion = excepciones(scanner);

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del conductor: ");
                    String nombre = excepcionesString(scanner);

                    System.out.print("Ingrese el número de ID: ");
                    int numeroID = excepcionesInt(scanner);

                    System.out.print("¿Tiene licencia? (true/false): ");
                    boolean licencia = excepcionesBoolean(scanner);

                    System.out.print("Ingrese el tipo de conductor: ");
                    String tipoConductor = excepcionesString(scanner);

                    System.out.print("¿Está disponible? (true/false): ");
                    boolean disponibilidad = excepcionesBoolean(scanner);

                    Conductor nuevoConductor = new Conductor(nombre, numeroID, licencia, disponibilidad, tipoConductor);
                    gestorConductores.agregarConductor(nuevoConductor);

                    System.out.println("Conductor agregado correctamente.");
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                    break;

                case 2:
                    System.out.print("Ingrese el número de ID del conductor: ");
                    int idBuscar = excepcionesInt(scanner);

                    Conductor encontrado = gestorConductores.buscarConductorPorID(idBuscar);
                    if (encontrado != null) {
                        System.out.println("Nombre: " + encontrado.getNombre());
                        System.out.println("ID: " + encontrado.getNumeroID());
                        System.out.println("Licencia: " + encontrado.isLicencia());
                        System.out.println("Tipo: " + encontrado.getTipodeConductor());
                        System.out.println("Disponible: " + encontrado.isDisponibilidad());
                    } else {
                        System.out.println("No se encontró ningún conductor con ese ID.");
                    }
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println("Ingrese el ID del conductor en el que va añadir el vehiculo");
                    int idConductor = excepcionesInt(scanner);
                    Conductor conductor = gestorConductores.buscarConductorPorID(idConductor);
                    if (conductor != null) {
                        System.out.println("Escriba la placa del vehiculo que va añadir: ");
                        String placaBucar= excepcionesString(scanner);
                        Vehiculo vehiculo = gestorVehiculos.buscarVehiculo(placaBucar);
                        if (vehiculo!=null){
                            gestorConductores.agregarVehiculo(vehiculo);
                            System.out.println("Vehiculo "+ vehiculo.getNumeroPlaca()+"Fue añadido al conductor "+ conductor.getNombre());
                        }else{
                            System.out.println("No se encontro ningun vehiculo con esa placa.");
                            break;
                        }

                    }else {
                        System.out.println("No se encontró ningún conductor con ese ID.");
                    }
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.println("Ingrese el ID del conductor en el que va eliminar el vehiculo: ");
                    int idEliminarVehiculo = excepcionesInt(scanner);
                    Conductor conductorEliminarVehiculo = gestorConductores.buscarConductorPorID(idEliminarVehiculo);
                    if (conductorEliminarVehiculo != null) {
                        System.out.println("Ingrese la placa del vehiculo que va eliminar: ");
                        String Placa= excepcionesString(scanner);
                        gestorConductores.EliminarVehiculo(idEliminarVehiculo, Placa);
                    }
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                case 5:
                    System.out.println("Ingrese ID del conductor en el que se van a mostrar los vehiculos: ");
                    int idVehiculos = excepcionesInt(scanner);
                    Conductor conductorVehiculos = gestorConductores.buscarConductorPorID(idVehiculos);
                    if (conductorVehiculos != null) {
                        System.out.println("Vehiculos del conductor "+ conductorVehiculos.getNombre());
                        gestorConductores.MostrarVehiculos(idVehiculos);
                    }
                case 6:
                    System.out.print("Ingrese el número de ID del conductor a actualizar: ");
                    int idActualizar = excepcionesInt(scanner);

                    System.out.print("Ingrese el nuevo tipo de conductor: ");
                    String nuevoTipo = excepcionesString(scanner);

                    gestorConductores.ActualizarConductores(nuevoTipo, idActualizar);
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                    break;

                case 7:
                    System.out.print("Ingrese el número de ID del conductor a eliminar: ");
                    int idEliminar = excepcionesInt(scanner);

                    gestorConductores.EliminarConductor(idEliminar);
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                    break;

                case 8:
                    gestorConductores.MostrarConductores();
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
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

    public static int excepciones(Scanner scanner) {
        int opcion = -1;
        boolean valido = false;
        while (!valido) {
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
                valido = true;
            } catch (Exception e) {
                System.out.println("Inválido, intente de nuevo:");
                scanner.nextLine();
            }
        }
        return opcion;
    }

    public static int excepcionesInt(Scanner scanner) {
        int numero = -1;
        boolean valido = false;
        while (!valido) {
            String entrada = scanner.nextLine();
            try {
                numero = Integer.parseInt(entrada);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero:");
            }
        }
        return numero;
    }

    public static String excepcionesString(Scanner scanner) {
        String valor = "";
        boolean entradaValida = false;
        while (!entradaValida) {
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("¡La entrada no puede estar vacía! Intente de nuevo.");
            } else if (valor.matches("\\d+")) {
                System.out.println("No puede ingresar solo números. Intente de nuevo.");
            } else {
                entradaValida = true;
            }
        }
        return valor;
    }

    public static boolean excepcionesBoolean(Scanner scanner) {
        boolean valor = false;
        boolean valido = false;
        while (!valido) {
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("true") || entrada.equals("false")) {
                valor = Boolean.parseBoolean(entrada);
                valido = true;
            } else {
                System.out.println("Entrada inválida. Escriba 'true' o 'false':");
            }
        }
        return valor;
    }
}