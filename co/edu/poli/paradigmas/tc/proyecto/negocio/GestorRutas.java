package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.*;

import java.util.ArrayList;

public class GestorRutas {
    private ArrayList<Rutas> listaRutas = new ArrayList<>();

    public void agregarRuta(Rutas ruta) {
        listaRutas.add(ruta);
    }


    public void mostrarRutas() {
        for (Rutas ruta : listaRutas) {
            System.out.println("ID: " + ruta.getNumeroID());
            System.out.println("Origen: " + ruta.getOrigen());
            System.out.println("Destino: " + ruta.getDestino());
            System.out.println("Distancia: " + ruta.getDistancia());
            System.out.print("Horarios: ");
            for (String horario : ruta.getHorariosSalida()) {
                System.out.print(horario + " ");
            }
            System.out.println("\n------------------------");
        }
    }

    public boolean actualizarRuta(String id, String nuevoOrigen, int nuevaDistancia, String[] nuevosHorarios){
        for (Rutas ruta : listaRutas) {
            if (ruta.getNumeroID().equals(id)) {
                ruta.setOrigen(nuevoOrigen);
                ruta.setDistancia(nuevaDistancia);
                ruta.setHorariosSalida(nuevosHorarios);
                return true;
            }
        }
        return false;
    }


    public boolean eliminarRuta(String id) {
        for (Rutas ruta : listaRutas) {
            if (ruta.getNumeroID().equals(id)) {
                listaRutas.remove(ruta);
                return true;
            }
        }
        return false;
    }

    public Rutas buscarRutaPorID(String id) {
        for (Rutas ruta : listaRutas) {

            if (ruta.getNumeroID().equals(id)) {
                return ruta;
            }
        }
        return null;
    }

}