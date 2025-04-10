package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Persona {
    private String nombre;
    private int numeroID;
    
    public Persona() {
        int id = 0;
        this.numeroID = id;
        this.nombre = nombre;
    }

    public int getNumeroID() {
        return numeroID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
