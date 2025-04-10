package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
import java.util.ArrayList;

public class GestionarVehiculos {
    private ArrayList<Vehiculo> vehiculos;

    public GestionarVehiculos(ArrayList<Vehiculo> vehiculos) {
        vehiculos = new ArrayList<>();
    }

    //Metodos CRUD
    // Crear
    public void agregarVehiculo (Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    // Leer
    public void mostrarVehiculos() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados.");
        } else {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        }
    }

    // Actualizar
    public boolean actualizarVehiculo(int numeroPlaca, boolean estaEnTaller) {
        Vehiculo vehiculo = buscarVehiculo(numeroPlaca);
        if (vehiculo != null) {
            vehiculo.setDisponibilidad(!estaEnTaller);
            return true;
        }
        return false;
    }

    public Vehiculo buscarVehiculo(int numeroPlaca) {
        for (Vehiculo vehiculoTemp : vehiculos) {
            if (vehiculoTemp.getNumeroPlaca() == numeroPlaca) {
                return vehiculoTemp;
            }
        }
        return null;
    }

    // Eliminar
    public boolean eliminarVehiculo(int numeroPlaca) {
        Vehiculo vehiculo = buscarVehiculo(numeroPlaca);
        if (vehiculo != null) {
            vehiculos.remove(vehiculo);
            return true;
        }
        return false;
    }
}