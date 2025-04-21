package co.edu.poli.paradigmas.tc.proyecto.entities;

import java.util.ArrayList;

public class Conductor extends Persona {

    private boolean licencia;
    private boolean disponibilidad;
    private String TipodeConductor;
    private ArrayList<Vehiculo> vehiculos;

    public Conductor(String nombre, int id, boolean licencia, boolean disponibilidad, String tipodeConductor ) {
        super(id, nombre);
        this.licencia = licencia;
        this.disponibilidad = disponibilidad;
        this.TipodeConductor = tipodeConductor;
        this.vehiculos = new ArrayList<>();
    }

    // Getters & Setters

    public boolean isLicencia() {
        return licencia;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public String getTipodeConductor() {
        return TipodeConductor;
    }

    public void setTipodeConductor(String tipodeConductor) {
        TipodeConductor = tipodeConductor;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    //Otros Metodos...

    public void agregarVehiculo(Vehiculo v) {
        vehiculos.add(v);
    }

    public void mostrarVehiculos() {
        if (vehiculos.isEmpty()) {
            System.out.println("Este conductor no tiene vehículos asignados.");
        } else {
            System.out.println("Vehículos asignados:");
            for (Vehiculo v : vehiculos) {
                System.out.println("Placa: " + v.getNumeroPlaca() +
                        "Modelo" + v.getModelo()+
                        "Ruta"+v.getRuta());
            }
        }
    }

    public void CambiarEstadoLicencia() {
        if (licencia) {
            disponibilidad = false;
            licencia = false;
            System.out.println("El conductor: " + getNombre() + " con numero ID " + getNumeroID() + "  no esta habilitado");
        } else {
            disponibilidad =true;
            System.out.println("El conductor: " + getNombre() + " con numero ID " + getNumeroID() + "  esta habilitado");
        }
    }

    public void CambiarDisponibilidad() {

        if (disponibilidad) {
            disponibilidad = false;
            System.out.println("El conductor: " + getNombre() + " con numero ID " + getNumeroID() + "  no esta disponible");
        } else {
            disponibilidad =true;
            System.out.println("El conductor: " + getNombre() + " con numero ID " + getNumeroID() + "  esta disponible");
        }
    }
}
