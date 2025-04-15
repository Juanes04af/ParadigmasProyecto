package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.TallerMantenimiento;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorTaller;


import java.util.Scanner;

public class MenuGestorTaller{
    public static void mostrarMenuRutas(Scanner scanner) {
        GestorTaller gestor = new GestorTaller();
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
                    System.out.print("Ingrese el tipo de mantenimiento: ");
                    String tipoMantenimiento = excepcionesString(scanner);
                    System.out.print("Ingrese estado del mantenimiento: ");
                    String estado = excepcionesString(scanner);
                    System.out.print("Ingrese observaciones: ");
                    String observaciones = excepcionesString(scanner);

                    TallerMantenimiento nuevoRegistro = new TallerMantenimiento(id, tipoMantenimiento, estado, observaciones);
                    gestor.agregarRegistro(nuevoRegistro);
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
                    System.out.print("Ingrese ID del vehiculo a cambair el estado: ");
                    int id = excepciones(scanner);
                    gestor.actualizarEstado()

                    break;

                case 4:
                    System.out.print("Ingrese el ID del registro a eliminar: ");
                    int idEliminar = excepciones(scanner);
                    if (gestor.eliminarMantenimiento(idEliminar)) {
                        gestor.eliminarMantenimiento(idEliminar);
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
            if (entrada.matches("\\d+")) { // Solo acepta números positivos
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
}
