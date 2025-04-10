package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorRutas;


import java.util.Scanner;

public class MenuGestorRutas {
    public static void mostrarMenuRutas(Scanner scanner) {
        GestorRutas gestor = new GestorRutas();
        boolean volver = false;

        while (!volver) {
            System.out.println("=== Menu Rutas ===");
            System.out.println("1. Agregar Ruta");
            System.out.println("2. Buscar Ruta");
            System.out.println("3. Actualizar Ruta");
            System.out.println("4. Eliminar Ruta");
            System.out.println("5. Mostrar todas las Rutas");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            int opcion = excepciones(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el ID de la ruta: ");
                    long idRuta = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Ingrese el origen: ");
                    String origen = scanner.nextLine();

                    System.out.print("Ingrese el destino: ");
                    String destino = scanner.nextLine();

                    System.out.print("Ingrese la distancia (en km): ");
                    int distancia = excepciones(scanner);
                    scanner.nextLine();

                    System.out.print("Ingrese el horario de salida: "); //***
                    String horario = scanner.nextLine();

                    String[] horarios = new String[1];
                    horarios[0] = horario;

                    Rutas nuevaRuta = new Rutas(idRuta, origen, destino, distancia, horarios);
                    gestor.agregarRuta(nuevaRuta);

                    System.out.println("Ruta agregada exitosamente.");
                    break;


                case 2:
                    System.out.print("Ingrese el ID de la ruta: ");
                    long idBuscar = scanner.nextLong();
                    scanner.nextLine();

                    Rutas rutaEncontrada = gestor.buscarRutaPorID(idBuscar);

                    if (rutaEncontrada != null) {
                        System.out.println("Ruta encontrada:");
                        System.out.println("ID: " + rutaEncontrada.getNumeroID());
                        System.out.println("Origen: " + rutaEncontrada.getOrigen());
                        System.out.println("Destino: " + rutaEncontrada.getDestino());
                        System.out.println("Distancia: " + rutaEncontrada.getDistancia() + " Km.");
                        System.out.print("Horario de salida: ");
                        for (String horarioS : rutaEncontrada.getHorariosSalida()) {
                            System.out.print(horarioS + " ");
                        }
                        System.out.println();
                    } else {
                        System.out.println("Ruta no encontrada con ID: " + idBuscar);
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID de la ruta que desea actualizar: ");
                    long idActualizar = scanner.nextLong();
                    scanner.nextLine();

                    Rutas rutaActualizar = gestor.buscarRutaPorID(idActualizar);
                    if (rutaActualizar != null) {
                        System.out.print("Ingrese el nuevo origen: ");
                        String nuevoOrigen = scanner.nextLine();

                        System.out.print("Ingrese el nuevo destino: ");
                        String nuevoDestino = scanner.nextLine();

                        System.out.print("Ingrese la nueva distancia (en km): ");
                        int nuevaDistancia = excepciones(scanner);
                        scanner.nextLine();

                        System.out.print("¿Cuantos horarios de salida desea ingresar?: ");
                        int cantidadHorarios = excepciones(scanner);
                        scanner.nextLine();

                        String[] nuevosHorarios = new String[cantidadHorarios]; //***
                        for (int i = 0; i < cantidadHorarios; i++) {
                            System.out.print("Ingrese el horario #" + (i + 1) + ": ");
                            nuevosHorarios[i] = scanner.nextLine();
                        }

                        boolean actualizado = gestor.actualizarRuta(idActualizar, nuevoOrigen, nuevoDestino, nuevaDistancia, nuevosHorarios);

                        if (actualizado) {
                            System.out.println("Ruta actualizada exitosamente.");
                        } else {
                            System.out.println("No se pudo actualizar la ruta.");
                        }
                    } else {
                        System.out.println("Ruta no encontrada");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID de la ruta que desea eliminar: ");
                    long idEliminar = scanner.nextLong();
                    scanner.nextLine();

                    boolean eliminado = gestor.eliminarRuta(idEliminar);
                    if (eliminado) {
                        System.out.println("Ruta eliminada exitosamente.");
                    } else {
                        System.out.println("No se pudo encontrar la ruta con ID: " + idEliminar);
                    }
                    break;

                case 5:
                    gestor.mostrarRutas();
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
