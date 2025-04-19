package co.edu.poli.paradigmas.tc.proyecto.presentacion;

import co.edu.poli.paradigmas.tc.proyecto.entities.TallerMantenimiento;
import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorTaller;

import java.util.Scanner;

public class MenuGestorTaller {

    public static void mostrarMenuTaller(Scanner scanner, GestorTaller gestor) {
        boolean volver = false;

        while (!volver) {
            System.out.println("=== Menu Taller ===");
            System.out.println("1. Agregar Registro Mantenimiento");
            System.out.println("2. Buscar Registro Mantenimiento");
            System.out.println("3. Actualizar Estado Mantenimiento");
            System.out.println("4. Eliminar Registro Mantenimiento");
            System.out.println("5. Mostrar Registros de Mantenimiento");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            int opcion = excepciones(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID del mantenimiento: ");
                    int id = excepciones(scanner);

                    if (gestor.buscarMantenimientoId(id) != null) {
                        System.out.println("¡Error! Ya existe un registro de mantenimiento con ese ID.");
                    } else {
                        System.out.print("Ingrese la placa del vehículo: ");
                        String placa = excepcionesString(scanner);
                        Vehiculo vehiculo = gestor.buscarVehiculoPorPlaca(placa);

                        if (vehiculo != null) {
                            System.out.println("Ingrese la fecha de Ingreso al taller: ");
                            String fechaIngreso = excepcionesString(scanner);
                            System.out.print("Ingrese el tipo de mantenimiento: ");
                            String tipoMantenimiento = excepcionesString(scanner);
                            System.out.print("Ingrese estado del mantenimiento: ");
                            String estado = excepcionesString(scanner);
                            System.out.print("Ingrese las observaciones para el vehiculo: ");
                            String observaciones = excepcionesString(scanner);

                            TallerMantenimiento nuevoRegistro = new TallerMantenimiento(id, fechaIngreso, tipoMantenimiento, estado, observaciones);
                            nuevoRegistro.setVehiculo(vehiculo);
                            if (estado.equalsIgnoreCase("En Taller")) {
                                vehiculo.setDisponibilidad(false);
                                System.out.println("El vehiculo con placa " + placa + " ahora esta en el taller y no esta disponible.");
                            }

                            gestor.agregarRegistro(nuevoRegistro);
                            System.out.println("Registro de mantenimiento agregado correctamente.");
                        } else {
                            System.out.println("No se encontro un vehiculo con la placa " + placa);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el ID del registro a buscar: ");
                    int idBuscar = excepciones(scanner);
                    TallerMantenimiento taller = gestor.buscarMantenimientoId(idBuscar);
                    if (taller != null) {
                        System.out.println("Registro encontrado: " + taller);
                    } else {
                        System.out.println("Registro no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID del mantenimiento: ");
                    int idMantenimiento = excepciones(scanner);

                    TallerMantenimiento mantenimiento = gestor.buscarMantenimientoId(idMantenimiento);
                    if (mantenimiento != null) {
                        System.out.print("Ingrese el nuevo estado del mantenimiento: ");
                        String nuevoEstado = excepcionesString(scanner);

                        boolean actualizado = gestor.actualizarEstado(idMantenimiento, nuevoEstado);
                        if (actualizado) {
                            System.out.println("Estado actualizado correctamente.");

                            Vehiculo vehiculo = mantenimiento.getVehiculo();
                            if (vehiculo != null) {
                                boolean estaEnTaller = nuevoEstado.equalsIgnoreCase("En Taller");
                                vehiculo.setDisponibilidad(!estaEnTaller);
                                System.out.println("Disponibilidad del vehículo actualizada: " + !estaEnTaller);
                            } else {
                                System.out.println("No se encontro vehiculo asociado al mantenimiento.");
                            }
                        } else {
                            System.out.println("No se pudo actualizar el estado del mantenimiento.");
                        }
                    } else {
                        System.out.println("No se encontro el mantenimiento con ese ID.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID del registro a eliminar: ");
                    int idEliminar = excepciones(scanner);
                    if (gestor.eliminarMantenimiento(idEliminar)) {
                        System.out.println("Registro de mantenimiento eliminado correctamente.");
                    } else {
                        System.out.println("No se pudo eliminar el registro.");
                    }
                    break;

                case 5:
                    gestor.mostrarTodos();
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
