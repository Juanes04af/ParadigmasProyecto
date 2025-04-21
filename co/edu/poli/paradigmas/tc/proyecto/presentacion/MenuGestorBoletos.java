package co.edu.poli.paradigmas.tc.proyecto.presentacion;

import co.edu.poli.paradigmas.tc.proyecto.entities.Boleto;
import co.edu.poli.paradigmas.tc.proyecto.entities.Pasajeros;
import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;
import co.edu.poli.paradigmas.tc.proyecto.negocio.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuGestorBoletos {
    public static void mostrarMenuBoleto(Scanner scanner, GestorBoletos gestor, GestorRutas gestorRutas, GestorPasajeros gestorPasajeros) {
        boolean volver = false;
        int contador = 1;
        while (!volver) {
            System.out.println("=== Menu Boletos ===");
            System.out.println("1. Agregar Boleto");
            System.out.println("2. Buscar Boleto");
            System.out.println("3. Actualizar Boleto");
            System.out.println("4. Eliminar Boleto");
            System.out.println("5. Mostrar todos los Boletos");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            int opcion = (int) validarEntrada(scanner, "int");

            switch (opcion) {
                case 1:
                    int id;
                    id = contador++;
                    System.out.print("Ingrese nombre: ");
                    String nombre = (String) validarEntrada(scanner, "string");

                    System.out.print("¿Ha comprado boleto? (true/false): ");
                    boolean compra = (boolean) validarEntrada(scanner, "boolean");

                    System.out.print("Ingrese ruta: ");
                    gestorRutas.mostrarRutas();
                    String rutaID = (String) validarEntrada(scanner, "string");
                    Rutas ruta = gestorRutas.buscarRutaPorID(rutaID);

                    double precio = compra ? GestorBoletos.getPrecioFijo() : 0.0;

                    Boleto boleto = new Boleto(compra, nombre, id, ruta, precio);
                    gestor.crearBoleto(boleto);

                    Pasajeros pasajero = gestorPasajeros.buscarPasajeroPorNombre(nombre);
                    pasajero.agregarBoleto(boleto);

                    System.out.println("Boleto comprado exitosamente.");
                    break;

                case 2:
                    System.out.print("Ingrese el ID del boleto a buscar: ");
                    int idBuscar = (int) validarEntrada(scanner, "int");
                    Boleto boletoBuscar = gestor.buscarBoletoPorId(idBuscar);
                    if (boletoBuscar != null) {
                        System.out.println("Boleto encontrado: " + boletoBuscar);
                    } else {
                        System.out.println("Boleto no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID del boleto a actualizar: ");
                    int idActualizar = (int) validarEntrada(scanner, "int");
                    Boleto boletoActualizar = gestor.buscarBoletoPorId(idActualizar);

                    if (boletoActualizar != null) {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = (String) validarEntrada(scanner, "string");

                        System.out.print("¿Ha comprado boleto? (true/false): ");
                        boolean nuevaCompra = (boolean) validarEntrada(scanner, "boolean");

                        System.out.print("Nueva ruta: ");
                        gestorRutas.mostrarRutas();
                        rutaID = (String) validarEntrada(scanner, "string");
                        Rutas nuevaRuta = gestorRutas.buscarRutaPorID(rutaID);

                        double precioFijo = nuevaCompra ? GestorBoletos.getPrecioFijo() : 0.0;
                        gestor.actualizarBoleto(idActualizar, nuevoNombre, nuevaCompra, nuevaRuta, precioFijo);
                    } else {
                        System.out.println("Boleto no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID del boleto a eliminar: ");
                    int idEliminar = (int) validarEntrada(scanner, "int");
                    Boleto boletoEliminar = gestor.buscarBoletoPorId(idEliminar);
                    if (boletoEliminar != null) {
                        gestor.eliminarBoleto(idEliminar);
                    } else {
                        System.out.println("Boleto no encontrado.");
                    }
                    System.out.println("Escriba enter para continuar.");
                    scanner.nextLine();
                    break;

                case 5:
                    gestor.mostrarBoletos();
                    System.out.println("Escriba enter para continuar.");
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

    public static Object validarEntrada(Scanner scanner, String tipoDato) {
        while (true) {
            try {
                switch (tipoDato.toLowerCase()) {
                    case "int":
                        int numInt = scanner.nextInt();
                        scanner.nextLine();
                        return numInt;

                    case "string":
                        String texto = scanner.nextLine().trim();
                        if (texto.isEmpty()) {
                            System.out.println("Error: No puede estar vacío. Intente nuevamente:");
                            continue;
                        }
                        if (texto.matches("\\d+")) {
                            System.out.println("Error: No puede ser solo números. Intente nuevamente:");
                            continue;
                        }
                        return texto;

                    case "long":
                        if (scanner.hasNextLong()) {
                            long numLong = scanner.nextLong();
                            scanner.nextLine();
                            return numLong;
                        } else {
                            System.out.println("Error: Ingrese un número válido (long). Intente nuevamente:");
                            scanner.nextLine();
                            continue;
                        }

                    case "double":
                        if (scanner.hasNextDouble()) {
                            double numDouble = scanner.nextDouble();
                            scanner.nextLine();
                            return numDouble;
                        } else {
                            System.out.println("Error: Ingrese un número decimal válido. Intente nuevamente:");
                            scanner.nextLine();
                            continue;
                        }

                    case "boolean":
                        String input = scanner.nextLine().trim().toLowerCase();
                        if (input.equals("true") || input.equals("false")) {
                            return Boolean.parseBoolean(input);
                        } else {
                            System.out.println("Error: Ingrese 'true' o 'false'. Intente nuevamente:");
                            continue;
                        }

                    case "byte":
                        if (scanner.hasNextByte()) {
                            byte numByte = scanner.nextByte();
                            scanner.nextLine();
                            return numByte;
                        } else {
                            System.out.println("Error: Ingrese un número entre -128 y 127. Intente nuevamente:");
                            scanner.nextLine();
                            continue;
                        }

                    default:
                        throw new IllegalArgumentException("Tipo de dato no soportado: " + tipoDato);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Tipo de dato incorrecto. Intente nuevamente:");
                scanner.nextLine();
            }
        }
    }
}
