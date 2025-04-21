package co.edu.poli.paradigmas.tc.proyecto.entities;
import java.util.ArrayList;
public class Persona {
    String nombre;
    int numeroID;
    ArrayList<Boleto> Boletos = new ArrayList<>();
    public Persona(int id, String nombre) {
        this.numeroID = id;
        this.nombre = nombre;
        this.Boletos = new ArrayList<>();
    }

    public void agregarBoleto(Boleto p) {
        Boletos.add(p);
    }
    public void mostrarBoletos() {
        if (Boletos.isEmpty()) {
            System.out.println("Este pasajero no tiene boletos asignados.");
        } else {
            System.out.println("Boletos asignados:");
            for (Boleto p : Boletos) {
                System.out.println("Nombre: " + p.getNombre()+"\n ID: "+p.getNumeroID()+"Ruta: "+p.getRutaString());
            }
        }
    }
    public ArrayList<Boleto> getBoletos() {
        return Boletos;
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
