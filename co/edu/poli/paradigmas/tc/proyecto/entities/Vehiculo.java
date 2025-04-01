package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Vehiculo {

    private int NumeroPlaca;
    private String Modelo;
    private byte NumeroPasajeros;
    private Rutas ruta;
    private boolean Disponibilidad;
    private boolean DisponibilidadConductor;
    public Vehiculo(int numeroPlaca, String modelo, byte numeroPasajeros, Rutas ruta, boolean disponibilidad,
                     boolean disponibilidadConductor) {
        super();
        NumeroPlaca = numeroPlaca;
        Modelo = modelo;
        NumeroPasajeros = numeroPasajeros;
        this.ruta = ruta;
        Disponibilidad = disponibilidad;
        DisponibilidadConductor = disponibilidadConductor;
    }

    public boolean isDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        Disponibilidad = disponibilidad;
    }

}
