package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Boleto {

    private long NumeroID;
    private String Nombre;
    private boolean CompraBoletos;
    private String ruta;


    public Boleto(boolean compraBoletos, String nombre, long numeroID, String ruta) {
        CompraBoletos = compraBoletos;
        Nombre = nombre;
        NumeroID = numeroID;
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean isCompraBoletos() {
        return CompraBoletos;
    }

    public void setCompraBoletos(boolean compraBoletos) {
        CompraBoletos = compraBoletos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public long getNumeroID() {
        return NumeroID;
    }

    public void setNumeroID(long numeroID) {
        NumeroID = numeroID;
    }
}
