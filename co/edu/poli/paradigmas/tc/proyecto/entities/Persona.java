package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Persona {
    String nombre;
    int numeroID;

    public Persona(int id, String nombre) {
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
