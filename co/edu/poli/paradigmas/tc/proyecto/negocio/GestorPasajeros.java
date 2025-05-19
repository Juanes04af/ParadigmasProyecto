package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.Pasajeros;
import java.util.ArrayList;

public class GestorPasajeros {
    private ArrayList<Pasajeros> listaPasajeros=new ArrayList<>();
    public ArrayList<Pasajeros> getListaPasajeros() {
        return listaPasajeros;
    }

    /**
     * CRUD Crear - Agrega un nuevo pasajero a la lista.
     * @param pasajero Objeto de tipo Pasajeros a agregar.
     */
    public void agregarPasajero(Pasajeros pasajero) {
        listaPasajeros.add(pasajero);
    }

    /**
     * CRUD Leer - Busca un pasajero por su número de ID.
     * @param id Número de identificación del pasajero.
     * @return El objeto Pasajeros si se encuentra, de lo contrario null.
     */
    public Pasajeros buscarPasajeroPorId(int id) {
        for (Pasajeros p : listaPasajeros) {
            if (p.getNumeroID() == id) {
                return p;
            }
        }
        System.out.println("Pasajero con ID " + id + " no encontrado.");
        return null;
    }

    /**
     * CRUD Leer - Busca un pasajero por su nombre.
     * @param nombre Nombre del pasajero a buscar.
     * @return El objeto Pasajeros si se encuentra, de lo contrario null.
     */
    public Pasajeros buscarPasajeroPorNombre(String nombre) {
        for (Pasajeros p : listaPasajeros) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        System.out.println("Pasajero con nombre " + nombre + " no encontrado.");
        return null;
    }

    /**
     * CRUD Actualizar - Actualiza el nombre de un pasajero dado su ID.
     * @param id Número de identificación del pasajero.
     * @param nuevoNombre Nuevo nombre que se asignará al pasajero.
     */
    public void actualizarNombrePasajero(int id, String nuevoNombre) {
        Pasajeros p = buscarPasajeroPorId(id);
        if (p != null) {
            p.setNombre(nuevoNombre);
            System.out.println("Nombre actualizado a: " + nuevoNombre);
        }
    }

    /**
     * CRUD Eliminar - Elimina un pasajero de la lista a partir de su ID.
     * @param id Número de identificación del pasajero a eliminar.
     */
    public void eliminarPasajero(int id) {
        Pasajeros p = buscarPasajeroPorId(id);
        if (p != null) {
            listaPasajeros.remove(p);
            System.out.println("Pasajero eliminado: " + p.getNombre());
        }
    }

    /**
     * CRUD Mostrar - Muestra todos los pasajeros registrados.
     */
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
    public int generarIdPasajero() {
        if (listaPasajeros.isEmpty()) {
            return 1; // Comenzamos desde 1
        } else {
            int maxId = 0;
            for (Pasajeros p : listaPasajeros) {
                if (p.getNumeroID() > maxId) {
                    maxId = p.getNumeroID();
                }
            }
            return maxId + 1;
        }
    }
}
