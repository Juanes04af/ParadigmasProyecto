package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;

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

    public boolean actualizarRuta(long id, String nuevoOrigen, String nuevoDestino, int nuevaDistancia, String[] nuevosHorarios) {
        for (Rutas ruta : listaRutas) {
            if (ruta.getNumeroID() == id) {
                ruta.setOrigen(nuevoOrigen);
                ruta.setDestino(nuevoDestino);
                ruta.setDistancia(nuevaDistancia);
                ruta.setHorariosSalida(nuevosHorarios);
                return true;
            }
        }
        return false;
    }


    public boolean eliminarRuta(long id) {
        for (Rutas ruta : listaRutas) {
            if (ruta.getNumeroID() == id) {
                listaRutas.remove(ruta);
                return true;
            }
        }
        return false;
    }

    public Rutas buscarRutaPorID(long id) {
        for (Rutas ruta : listaRutas) {
            if (ruta.getNumeroID() == id) {
                return ruta;
            }
        }
        return null;
    }
}