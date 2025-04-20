package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Boleto {

    private int NumeroID;
    private String Nombre;
    private boolean CompraBoletos;
    private String ruta;
    private double precio;
    private boolean enCarrito;

    public Boleto(boolean compraBoletos, String nombre, int numeroID, String ruta, double precio, boolean enCarrito) {
        this.CompraBoletos = compraBoletos;
        this.Nombre = nombre;
        this.NumeroID = numeroID;
        this.ruta = ruta;
        this.precio = precio;
        this.enCarrito = enCarrito;
    }

    public boolean isEnCarrito() {
        return enCarrito;
    }

    public void setEnCarrito(boolean enCarrito) {
        this.enCarrito = enCarrito;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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

    public void setNumeroID(int numeroID) {
        NumeroID = numeroID;
    }

    @Override
    public String toString() {
        return "[ID: " + NumeroID + ", Nombre: " + Nombre + ", Ruta: " + ruta + ", Compra: " + CompraBoletos + ", Precio: " + precio + "]";
    }
}
