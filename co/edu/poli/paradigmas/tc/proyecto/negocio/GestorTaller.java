package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.TallerMantenimiento;
import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;

import java.util.ArrayList;

public class GestorTaller {
    private ArrayList<TallerMantenimiento> registrosMantenimiento = new ArrayList<>();
    private ArrayList<Vehiculo> listaVehiculos;

    public GestorTaller(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public void agregarRegistro(TallerMantenimiento mantenimiento) {
        registrosMantenimiento.add(mantenimiento);
    }

    public TallerMantenimiento buscarMantenimientoId(int id) {
        for (TallerMantenimiento mantenimientoVehc : registrosMantenimiento) {
            if (mantenimientoVehc.getId() == id) {
                return mantenimientoVehc;
            }
        }
        System.out.println("No se encontró el mantenimiento con ID: " + id);
        return null;
    }

    public boolean eliminarMantenimiento(int id) {
        TallerMantenimiento mantenimiento = buscarMantenimientoId(id);
        if (mantenimiento != null) {
            registrosMantenimiento.remove(mantenimiento);
            System.out.println("Registro de mantenimiento eliminado correctamente.");
            return true;
        }
        System.out.println("No se pudo eliminar el mantenimiento, no existe.");
        return false;
    }

    public boolean actualizarEstado(int id, String nuevoEstado) {
        TallerMantenimiento mantenimiento = buscarMantenimientoId(id);
        if (mantenimiento != null) {
            mantenimiento.setEstado(nuevoEstado);
            System.out.println("Estado de mantenimiento actualizado a: " + nuevoEstado);

            Vehiculo vehiculo = mantenimiento.getVehiculo();
            if (vehiculo != null) {
                boolean estaEnTaller = nuevoEstado.equalsIgnoreCase("En Taller");
                vehiculo.setDisponibilidad(!estaEnTaller);
                System.out.println("Disponibilidad del vehículo actualizada a: " + !estaEnTaller);
            } else {
                System.out.println("No hay un vehículo asignado a este mantenimiento.");
            }

            return true;
        }
        return false;
    }

    public void mostrarTodos() {
        if (registrosMantenimiento.isEmpty()) {
            System.out.println("No hay registros de mantenimiento.");
        } else {
            for (TallerMantenimiento registro : registrosMantenimiento) {
                System.out.println(registro);
            }
        }
    }

    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getNumeroPlaca().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }
        System.out.println("No se encontró un vehículo con la placa: " + placa);
        return null;
    }
}
