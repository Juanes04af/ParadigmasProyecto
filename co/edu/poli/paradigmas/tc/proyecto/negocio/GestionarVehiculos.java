package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;
import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
import java.util.ArrayList;

public class GestionarVehiculos {
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

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
                System.out.println("Numero placa: " + vehiculo.getNumeroPlaca());
                System.out.println("Modelo: " + vehiculo.getModelo());
                System.out.println("Numero Pasajeros: " + vehiculo.getNumeroPasajeros());
                System.out.println("Disponibilidad: " + vehiculo.isDisponibilidad());
                System.out.println("Disponibilidad del conductor: " + vehiculo.isDisponibilidad());
                System.out.println("\n------------------------");
            }
        }
    }

    // Actualizar
    public boolean actualizarVehiculo(String numeroPlaca, boolean estaEnTaller, boolean disponibilidadConductor) {
        Vehiculo vehiculo = buscarVehiculo(numeroPlaca); // Suponiendo que buscarVehiculo te devuelve un objeto Vehiculo
        if (vehiculo != null) {
            vehiculo.setDisponibilidad(!estaEnTaller);
            vehiculo.setDisponibilidadConductor(disponibilidadConductor);

            return true;
        }
        return false;
    }

    public Vehiculo buscarVehiculo(String numeroPlaca) {
        for (Vehiculo vehiculoTemp : vehiculos) {
            if (vehiculoTemp.getNumeroPlaca().equals(numeroPlaca)) {
                return vehiculoTemp;
            }
        }
        return null;
    }
    // Eliminar
    public boolean eliminarVehiculo(String numeroPlaca) {
        Vehiculo vehiculo = buscarVehiculo(numeroPlaca);
        if (vehiculo != null) {
            vehiculos.remove(vehiculo);
            return true;
        }
        return false;
    }
}