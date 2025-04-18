package co.edu.poli.paradigmas.tc.proyecto.entities;

//Clase Pasajero
public class Pasajeros extends Persona{
    private String[] boletos;
    private int totalBoletos;

    public Pasajeros(int id, String nombre) {
        super(id, nombre);
        this.boletos = new String[10];
        this.totalBoletos = 0;
    }

    public void comprarBoleto(String viaje) {
        if (totalBoletos < boletos.length) {
            boletos[totalBoletos] = viaje;
            totalBoletos++;
            System.out.println(nombre + " compró boleto para: " + viaje);
        } else {
            System.out.println("No puedes comprar más boletos.");
        }
    }

    public void mostrarViajes() {
        if (totalBoletos == 0) {
            System.out.println(nombre + " no tiene viajes.");
        } else {
            System.out.println("Viajes de " + nombre + ":");
            for (int i = 0; i < totalBoletos; i++) {
                System.out.println((i + 1) + ". " + boletos[i]);
            }
        }
    }

    public int getNumeroID() {
        return numeroID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }
}