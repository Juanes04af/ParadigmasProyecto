package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.*;

import java.util.ArrayList;

public class GestorRutas {
    private ArrayList<Rutas> listaRutas = new ArrayList<>();

    /**
     * Agrega una nueva ruta a la lista de rutas gestionadas.
     *
     * @param ruta La ruta que se desea agregar a la lista.
     */
    public void agregarRuta(Rutas ruta) {
        listaRutas.add(ruta);
    }

    /**
     * Metodo que Imprime todas las rutas junto a todos los atributos de estas
     */
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

    /**
     * Metodo que se encarga de actualizar los datos de las rutas
     * @param id ID de la ruta de la cual se le va a cambiar los datos
     * @param nuevoOrigen Nuevo origen de donde puede partir la ruta
     * @param nuevaDistancia La nueva distancia de la ruta
     * @param nuevosHorarios El arreglo de nuevos horarios de salida para la ruta
     * @return
     */
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

    /**
     * Metodo que se encarga de eliminar las rutas de la lista de rutas
     * @param id ID de la ruta la cual se va a eliminar
     * @return Retornara True si la ruta se pudo eliminar, pero retornara false si esta no se pudo eliminar
     */
    public boolean eliminarRuta(String id) {
        for (Rutas ruta : listaRutas) {
            if (ruta.getNumeroID().equals(id)) {
                listaRutas.remove(ruta);
                return true;
            }
        }
        return false;
    }

    /**
     * Busca y retorna una ruta de la lista de rutas gestionadas que coincida con el ID proporcionado.
     *
     * @param id El identificador único de la ruta que se desea buscar.
     * @return La ruta correspondiente al ID proporcionado, o null si no se encuentra ninguna ruta con ese ID.
     */
    public Rutas buscarRutaPorID(String id) {
        for (Rutas ruta : listaRutas) {

            if (ruta.getNumeroID().equals(id)) {
                return ruta;
            }
        }
        return null;
    }

}