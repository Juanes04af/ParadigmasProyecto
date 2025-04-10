package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;
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
                System.out.println("Numero placa: " + vehiculo.getNumeroPlaca());
                System.out.println("Modelo: " + vehiculo.getModelo());
                System.out.println("Numero Pasajeros: " + vehiculo.getNumeroPasajeros());
                System.out.println("Ruta: " + vehiculo.getRuta());
                System.out.println("Disponibilidad: " + vehiculo.isDisponibilidad());
                System.out.println("Disponibilidad del conductor: " + vehiculo.isDisponibilidad());
            }
            System.out.println("\n------------------------");
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