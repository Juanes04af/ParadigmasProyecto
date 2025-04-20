package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Vehiculo {
    private String numeroPlaca;
    private String modelo;
    private byte numeroPasajeros;
    private Rutas ruta;
    private boolean disponibilidad;
    private boolean disponibilidadConductor;

    public Vehiculo(String placa, String modelo, byte numeroPasajeros, co.edu.poli.paradigmas.tc.proyecto.entities.Rutas ruta, boolean disponibilidad, boolean disponibilidadConductor) {
        this.numeroPlaca = placa;
        this.modelo = modelo;
        this.numeroPasajeros = numeroPasajeros;
        this.ruta = ruta;
        this.disponibilidad = disponibilidad;
        this.disponibilidadConductor = disponibilidadConductor;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public boolean isDisponibilidadConductor() {
        return disponibilidadConductor;
    }

    public void setDisponibilidadConductor(boolean disponibilidadConductor) {
        this.disponibilidadConductor = disponibilidadConductor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public byte getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(byte numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public Rutas getRuta() {
        return ruta;
    }
    public void setRuta(Rutas ruta) {
        this.ruta = ruta;
    }


}
