package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Boleto {

    private long NumeroID;
    private String Nombre;
    private boolean CompraBoletos;

    public Boleto(long numeroID, String nombre, boolean compraBoletos) {
        super();
        NumeroID = numeroID;
        Nombre = nombre;
        CompraBoletos = compraBoletos;
    }

}
