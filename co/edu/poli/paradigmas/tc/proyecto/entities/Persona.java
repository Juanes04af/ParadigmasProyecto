package co.edu.poli.paradigmas.tc.proyecto.entities;

import java.util.ArrayList;

public class Persona {
    String nombre;
    int numeroID;

    /**
     * Costructor de la Super-Clase persona
     * @param id Id unico para cada persona
     * @param nombre Nombre unico para cada persona
     */
    public Persona(int id, String nombre) {
        this.numeroID = id;
        this.nombre = nombre;

    }

    // Getters & Setters

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
