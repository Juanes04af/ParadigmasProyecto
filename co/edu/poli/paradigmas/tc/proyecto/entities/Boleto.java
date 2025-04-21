package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Boleto {

    private int NumeroID;
    private String Nombre;
    private boolean CompraBoletos;
    private Rutas ruta;
    private double precio;

    public Boleto(boolean compraBoletos, String nombre, int numeroID, Rutas ruta, double precio) {
        this.CompraBoletos = compraBoletos;
        this.Nombre = nombre;
        this.NumeroID = numeroID;
        this.ruta = ruta;
        this.precio = precio;

    }

    // Getters & Setters

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Rutas getRuta() {
        return ruta;
    }

    public String getRutaString() {
        return ruta.getOrigen()+"-"+ruta.getDestino();
    }

    public void setRuta(Rutas ruta) {
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

    public void setNumeroID(int numeroID) {
        NumeroID = numeroID;
    }

    /**
     * Devuelve una representacion en texto de la informacion del objeto Boleto.
     * @return La informacion con los datos principales del boleto.
     */
    @Override
    public String toString() {
        return "[ID: " + NumeroID + ", Nombre: " + Nombre + ", Ruta: " + ruta.getOrigen() + "-" + ruta.getDestino() + ", Compra: " + CompraBoletos + ", Precio: " + precio + "]";
    }
}
