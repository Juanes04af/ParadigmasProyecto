package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.Pasajeros;

import java.util.Scanner;

public class GestionarPasajeros {
    static Pasajeros[] pasajeros = new Pasajeros[100];
    static int totalPasajeros = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear pasajero");
            System.out.println("2. Comprar boleto");
            System.out.println("3. Ver viajes");
            System.out.println("4. Eliminar pasajero");
            System.out.println("5. Listar pasajeros");
            System.out.println("6. Editar nombre del pasajero");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1: crearPasajero(); break;
                case 2: comprarBoleto(); break;
                case 3: verViajes(); break;
                case 4: eliminarPasajero(); break;
                case 5: listarPasajeros(); break;
                case 6: editarNombre(); break;
                case 0: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    public static void crearPasajero() {
        if (totalPasajeros >= pasajeros.length) {
            System.out.println("No hay más espacio para nuevos pasajeros.");
            return;
        }

        System.out.print("Ingrese ID del pasajero: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (buscarPasajeroPorId(id) != -1) {
            System.out.println("Ya existe un pasajero con ese ID.");
            return;
        }

        System.out.print("Ingrese nombre del pasajero: ");
        String nombre = scanner.nextLine();

        pasajeros[totalPasajeros] = new Pasajeros(id, nombre);
        totalPasajeros++;
        System.out.println("Pasajero creado exitosamente.");
    }

    public static void comprarBoleto() {
        System.out.print("Ingrese ID del pasajero: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        int pos = buscarPasajeroPorId(id);
        if (pos == -1) {
            System.out.println("Pasajero no encontrado.");
            return;
        }

        System.out.print("Ingrese destino del viaje: ");
        String destino = scanner.nextLine();

        pasajeros[pos].comprarBoleto(destino);
    }

    public static void verViajes() {
        System.out.print("Ingrese ID del pasajero: ");
        int id = scanner.nextInt();

        int pos = buscarPasajeroPorId(id);
        if (pos != -1) {
            pasajeros[pos].mostrarViajes();
        } else {
            System.out.println("Pasajero no encontrado.");
        }
    }

    public static void eliminarPasajero() {
        System.out.print("Ingrese ID del pasajero a eliminar: ");
        int id = scanner.nextInt();

        int pos = buscarPasajeroPorId(id);
        if (pos == -1) {
            System.out.println("Pasajero no encontrado.");
            return;
        }

        for (int i = pos; i < totalPasajeros - 1; i++) {
            pasajeros[i] = pasajeros[i + 1];
        }

        pasajeros[totalPasajeros - 1] = null;
        totalPasajeros--;
        System.out.println("Pasajero eliminado correctamente.");
    }

    public static void listarPasajeros() {
        if (totalPasajeros == 0) {
            System.out.println("No hay pasajeros registrados.");
            return;
        }

        System.out.println("\n--- Lista de Pasajeros ---");
        for (int i = 0; i < totalPasajeros; i++) {
            System.out.println("ID: " + pasajeros[i].getId() + ", Nombre: " + pasajeros[i].getNombre());
        }
    }

    public static void editarNombre() {
        System.out.print("Ingrese ID del pasajero: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        int pos = buscarPasajeroPorId(id);
        if (pos == -1) {
            System.out.println("Pasajero no encontrado.");
            return;
        }

        System.out.print("Ingrese nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();

        pasajeros[pos].setNombre(nuevoNombre);
        System.out.println("Nombre actualizado correctamente.");
    }

    public static int buscarPasajeroPorId(int id) {
        for (int i = 0; i < totalPasajeros; i++) {
            if (pasajeros[i].getId() == id) {
                return i;
            }
        }
        return-1;
    }
}