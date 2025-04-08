package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Vehiculo {
    private int numeroPlaca;
    private String modelo;
    private byte numeroPasajeros;
    private Rutas ruta;
    private boolean disponibilidad;
    private boolean disponibilidadConductor;

    public Vehiculo(boolean disponibilidad, boolean disponibilidadConductor, String modelo, byte numeroPasajeros, int numeroPlaca, co.edu.poli.paradigmas.tc.proyecto.entities.Rutas ruta) {
        this.disponibilidad = disponibilidad;
        this.disponibilidadConductor = disponibilidadConductor;
        this.modelo = modelo;
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPlaca = numeroPlaca;
        this.ruta = ruta;
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

    public int getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(int numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public Rutas getRuta() {
        return ruta;
    }

    public void setRuta(Rutas ruta) {
        this.ruta = ruta;
    }

}
