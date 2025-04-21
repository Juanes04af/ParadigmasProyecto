package co.edu.poli.paradigmas.tc.proyecto.presentacion;
import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorRutas;
import java.util.*;

public class MenuGestorRutas {
    public static void mostrarMenuRutas(Scanner scanner, GestorRutas gestorRutas) {

        boolean volver = false;

        while (!volver) {
            System.out.println("=== Menu Rutas ===");
            System.out.println("1. Agregar Ruta.");
            System.out.println("2. Buscar Ruta.");
            System.out.println("3. Actualizar Ruta.");
            System.out.println("4. Eliminar Ruta.");
            System.out.println("5. Mostrar todas las Rutas.");
            System.out.println("0. Volver al menu principal.");
            System.out.print("Seleccione una opción: ");
            int opcion = excepciones(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    String Ruta = menuDestinos();
                    String idRuta = idRutas(Ruta);


                    System.out.print("Ingrese el origen: ");
                    String origen = excepcionesString(scanner);

                    String destino = destinos(Ruta);

                    System.out.print("Ingrese la distancia (en km): ");
                    int distancia = excepciones(scanner);
                    scanner.nextLine();

                    System.out.print("Ingrese el horario de salida: "); //Cambiar sistema de horarios
                    String horario = excepcionesString(scanner);

                    String[] horarios = new String[1];
                    horarios[0] = horario;

                    Rutas nuevaRuta = new Rutas(idRuta, origen, destino, distancia, horarios);
                    gestorRutas.agregarRuta(nuevaRuta);

                    System.out.println("Ruta agregada exitosamente.Su ID es: " + idRuta + ".\nPulse 'enter' para continuar");
                    scanner.nextLine();
                    break;


                case 2:
                    System.out.print("Ingrese el ID de la ruta: ");
                    String idBuscar = excepcionesString(scanner).toUpperCase();


                    Rutas rutaEncontrada = gestorRutas.buscarRutaPorID(idBuscar);

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
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.print("Ingrese el ID de la ruta que desea actualizar: ");
                    String idActualizar = excepcionesString(scanner).toUpperCase();

                    Rutas rutaActualizar = gestorRutas.buscarRutaPorID(idActualizar);
                    if (rutaActualizar != null) {
                        System.out.print("Ingrese el nuevo origen: ");
                        String nuevoOrigen = excepcionesString(scanner);

                        System.out.print("Ingrese la nueva distancia (en km): ");
                        int nuevaDistancia = excepciones(scanner);
                        scanner.nextLine();

                        System.out.print("¿Cuantos horarios de salida desea ingresar?: ");
                        int cantidadHorarios = excepciones(scanner);
                        scanner.nextLine();

                        String[] nuevosHorarios = new String[cantidadHorarios];
                        for (int i = 0; i < cantidadHorarios; i++) {
                            System.out.print("Ingrese el horario #" + (i + 1) + ": ");
                            nuevosHorarios[i] = excepcionesString(scanner);
                        }

                        boolean actualizado = gestorRutas.actualizarRuta(idActualizar, nuevoOrigen, nuevaDistancia, nuevosHorarios);

                        if (actualizado) {
                            System.out.println("Ruta actualizada exitosamente.");
                        } else {
                            System.out.println("No se pudo actualizar la ruta.");
                        }
                    } else {
                        System.out.println("Ruta no encontrada");
                    }
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.print("Ingrese el ID de la ruta que desea eliminar: ");
                    String idEliminar = excepcionesString(scanner);


                    boolean eliminado = gestorRutas.eliminarRuta(idEliminar);
                    if (eliminado) {
                        System.out.println("Ruta eliminada exitosamente.");
                    } else {
                        System.out.println("No se pudo encontrar la ruta con ID: " + idEliminar);
                    }
                    System.out.println("Pulse 'enter' para continuar.");
                    scanner.nextLine();
                    break;

                case 5:
                    gestorRutas.mostrarRutas();
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
    public static String idRutas(String LugarRuta){
        Random random = new Random();
        int numero = random.nextInt(90) + 10;
        String IDRuta = LugarRuta + numero;
        return IDRuta;
    }
    public static String destinos(String Ruta){
        if(Ruta.equals("J")){
            return "Centro de bogota.";
        }else if(Ruta.equals("D")){
            return "Portal 80.";
        }else if(Ruta.equals("B")){
            return "Portal Norte.";
        }else if(Ruta.equals("G")){
            return "Portal Sur.";
        }
        return null;
    }
    public static String menuDestinos (){
        System.out.println("Ingrese una de las sigientes letras para elegir el destino de la ruta: \n J - Centro de Bogota \n D - Portal 80 \n B - Portal Norte \n G - Portal Sur \nEscriba la letra correspondiente: ");
        String destino = excepcionesString(new Scanner(System.in));
        return destino.toUpperCase();
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
