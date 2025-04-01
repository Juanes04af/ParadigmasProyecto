package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Pasajeros {

    private long NumeroID;
    private String Nombre;
    private boolean CompraBoletos;

    public Pasajeros (long numeroID, String nombre, boolean compraBoletos) {
        super();
        NumeroID = numeroID;
        Nombre = nombre;
        CompraBoletos = compraBoletos;
    }

}
