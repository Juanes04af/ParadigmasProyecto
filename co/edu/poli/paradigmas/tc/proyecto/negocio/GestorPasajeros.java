package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.Pasajeros;
import java.util.ArrayList;

public class GestorPasajeros {
    private ArrayList<Pasajeros> listaPasajeros;

    public GestorPasajeros() {
        listaPasajeros = new ArrayList<>();
    }

    // Crear
    public void agregarPasajero(Pasajeros pasajero) {
        listaPasajeros.add(pasajero);
    }

    // Leer por ID
    public Pasajeros buscarPasajeroPorId(int id) {
        for (Pasajeros p : listaPasajeros) {
            if (p.getNumeroID() == id) {
                return p;
            }
        }
        System.out.println("Pasajero con ID " + id + " no encontrado.");
        return null;
    }

    // Actualizar nombre
    public void actualizarNombrePasajero(int id, String nuevoNombre) {
        Pasajeros p = buscarPasajeroPorId(id);
        if (p != null) {
            p.setNombre(nuevoNombre);
            System.out.println("Nombre actualizado a: " + nuevoNombre);
        }
    }

    // Eliminar
    public void eliminarPasajero(int id) {
        Pasajeros p = buscarPasajeroPorId(id);
        if (p != null) {
            listaPasajeros.remove(p);
            System.out.println("Pasajero eliminado: " + p.getNombre());
        }
    }

    // Mostrar todos
    public void mostrarTodosLosPasajeros() {
        if (listaPasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
        } else {
            System.out.println("Lista de pasajeros:");
            for (Pasajeros p : listaPasajeros) {
                System.out.println("ID: " + p.getNumeroID() + ", Nombre: " + p.getNombre());
            }
        }
    }
}
