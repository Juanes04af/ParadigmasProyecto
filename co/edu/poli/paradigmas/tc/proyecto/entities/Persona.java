package co.edu.poli.paradigmas.tc.proyecto.entities;

import java.util.ArrayList;

public class Persona {
    String nombre;
    int numeroID;
    ArrayList<Boleto> boletos = new ArrayList<>();

    public Persona(int id, String nombre) {
        this.numeroID = id;
        this.nombre = nombre;
        this.boletos = new ArrayList<>();
    }

    // Getters & Setters

    public ArrayList<Boleto> getBoletos() {
        return boletos;
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

    // Otros Metodos...

    public void agregarBoleto(Boleto p) {
        boletos.add(p);
    }

    public void mostrarBoletos() {
        if (boletos.isEmpty()) {
            System.out.println("Este pasajero no tiene boletos asignados.");
        } else {
            System.out.println("Boletos asignados:");
            for (Boleto p : boletos) {
                System.out.println("Nombre: " + p.getNombre()+"\n ID: "+p.getNumeroID()+"\nRuta: "+p.getRutaString()+"\nNumero de Ruta: "+p.getNumeroID());
            }
        }
    }
}
