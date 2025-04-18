package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
import java.util.ArrayList;

public class GestorVehiculos {
    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    public ArrayList<Vehiculo> obtenerListaVehiculos() {
        return listaVehiculos;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        listaVehiculos.add(vehiculo);
    }

    // Leer
    public void mostrarVehiculos() {
        if (listaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            for (Vehiculo vehiculo : listaVehiculos) {
                System.out.println("Numero placa: " + vehiculo.getNumeroPlaca());
                System.out.println("Modelo: " + vehiculo.getModelo());
                System.out.println("Numero Pasajeros: " + vehiculo.getNumeroPasajeros());
                System.out.println("Disponibilidad del vehiculo: " + vehiculo.isDisponibilidad());
                System.out.println("Disponibilidad del conductor: " + vehiculo.isDisponibilidadConductor());
                System.out.println("\n------------------------");
            }
        }
    }

    // Actualizar
    public boolean actualizarVehiculo(String numeroPlaca, boolean estaEnTaller, boolean disponibilidadConductor) {
        Vehiculo vehiculo = buscarVehiculo(numeroPlaca);
        if (vehiculo != null) {
            vehiculo.setDisponibilidad(!estaEnTaller);
            vehiculo.setDisponibilidadConductor(disponibilidadConductor);
            return true;
        }
        return false;
    }

    public Vehiculo buscarVehiculo(String numeroPlaca) {
        for (Vehiculo vehiculoTemp : listaVehiculos) {
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
            listaVehiculos.remove(vehiculo);
            return true;
        }
        return false;
    }
}
