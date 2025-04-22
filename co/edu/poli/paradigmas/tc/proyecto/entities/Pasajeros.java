package co.edu.poli.paradigmas.tc.proyecto.entities;

import java.util.ArrayList;

public class Pasajeros extends Persona{
    ArrayList<Boleto> boletos = new ArrayList<>();

    /**
     * Metodo Constructor de pasajero que hereda de persona
     * @param id ID unico para cada pasajero
     * @param nombre Nombre Unico para cada pasajero
     */
    public Pasajeros(int id, String nombre) {
        super(id, nombre);
        this.boletos = new ArrayList<>();
    }

    public ArrayList<Boleto> getBoletos() {
        return boletos;
    }
    // Otros Metodos...

    /**
     * Metodo encargado de agregar los boletos a la lista de boletos de cada Pasajero
     * @param p Boleto que se va añadir a la lista de cada persona
     */
    public void agregarBoleto(Boleto p) {
        boletos.add(p);
    }

    /**
     * Metodo que mostrara a todos los boletos de cada usuario
     */
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