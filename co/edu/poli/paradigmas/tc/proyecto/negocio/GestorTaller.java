package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.TallerMantenimiento;
import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
import java.util.*;

public class GestorTaller {
    private ArrayList<TallerMantenimiento> registrosMantenimiento = new ArrayList<>();
    private ArrayList<Vehiculo> listaVehiculos;

    /**
     * Metodo Constructor de 'GestorTaller' que recibe la lista de los vehiculos..
     * @param listaVehiculos La lista de vehiculos con la que se inicializa el gestor.
     */
    public GestorTaller(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    /**
     * CRUD Agregar -Agrega y guarda los registros de mantenimiento de cada vehiculo que entra al taller.
     * @param mantenimiento Es cada registro que se ingresa con la informacion dada por el usuario.
     */
    public void agregarRegistro(TallerMantenimiento mantenimiento) {
        registrosMantenimiento.add(mantenimiento);
    }

    /**
     * CRUD Busqueda -Busca el registro de mantenimiento segun el ID de cada registro.
     * @param id Identificador unico para cada registro de mantenimiento.
     * @return Devuelve el registro de mantenimiento correspondiente si se encuentra o 'null' si no se encuentra.
     */
    public TallerMantenimiento buscarMantenimientoId(int id) {
        for (TallerMantenimiento mantenimientoVehc : registrosMantenimiento) {
            if (mantenimientoVehc.getId() == id) {
                return mantenimientoVehc;
            }
        }
        return null;
    }

    /**
     * CRUD Eliminar -Elimina registros de mantenimiento segun el ID.
     * @param id -Identificador unico y parametro de busqueda para la eliminacion.
     * @return 'true' si se encuentra y elimina correctamente el registro; 'false' en caso contrario.
     */
    public boolean eliminarMantenimiento(int id) {
        TallerMantenimiento mantenimiento = buscarMantenimientoId(id);
        if (mantenimiento != null) {
            registrosMantenimiento.remove(mantenimiento);
            return true;
        }
        return false;
    }

    /**
     * CRUD Actualizar -Actualiza el estado del registro de mantenimiento y la disponibilidad del vehiculo asociado.
     * @param id El identificador del registro de mantenimiento a actualizar.
     * @param nuevoEstado El nuevo estado del mantenimiento ("En Taller", "Mantenimiento Completado")
     * @return 'true' si el estado se actualiza correctamente; 'false' en caso contrario.
     */
    public boolean actualizarEstado(int id, String nuevoEstado) {
        TallerMantenimiento mantenimiento = buscarMantenimientoId(id);
        if (mantenimiento != null) {
            mantenimiento.setEstado(nuevoEstado);
            System.out.println("Estado de mantenimiento actualizado a: " + nuevoEstado);

            Vehiculo vehiculo = mantenimiento.getVehiculo();
            if (vehiculo != null) {
                boolean estaEnTaller = nuevoEstado.equalsIgnoreCase("En Taller");
                vehiculo.setDisponibilidad(!estaEnTaller);
                System.out.println("Disponibilidad del vehiculo actualizada a: " + !estaEnTaller);
            } else {
                System.out.println("No hay un vehiculo asignado a este mantenimiento.");
            }

            return true;
        }
        return false;
    }

    /**
     * CRUD Mostrar -Muestra todos los registros de mantenimiento añadidos mediante un ciclo for.
     */
    public void mostrarTodos() {
        if (registrosMantenimiento.isEmpty()) {
            System.out.println("No hay registros de mantenimiento.");
        } else {
            for (TallerMantenimiento registro : registrosMantenimiento) {
                System.out.println(registro);
            }
        }
    }

    /**
     * CRUD Busqueda -Busca un determinado vehiculo en la lista segun su numero de placa.
     * @param placa El numero de placa del vehiculo a buscar.
     * @return El vehiculo correspondiente si se encuentra; 'null' si no se encuentra.
     */
    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getNumeroPlaca().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }
        System.out.println("No se encontro ningun vehiculo con la placa: " + placa);
        return null;
    }

    public ArrayList<TallerMantenimiento> getRegistrosMantenimiento() {
        return registrosMantenimiento;
    }

}
