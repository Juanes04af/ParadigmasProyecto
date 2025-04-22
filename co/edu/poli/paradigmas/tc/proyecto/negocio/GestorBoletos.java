package co.edu.poli.paradigmas.tc.proyecto.negocio;
import co.edu.poli.paradigmas.tc.proyecto.entities.Boleto;
import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;

import java.util.ArrayList;

public class GestorBoletos {
    private ArrayList<Boleto> boletos = new ArrayList<>();
    private static final double precioFijo = 3500.0;

    /**
     * Metodo que retorna el precio fijo establecido para un boleto.
     * @return Precio fijo del boleto.
     */
    public static double getPrecioFijo() {
        return precioFijo;
    }

    /**
     * CRUD Crear - Agrega un nuevo boleto a la lista de boletos.
     * @param boleto Objeto de tipo Boleto a agregar.
     */
    public void crearBoleto(Boleto boleto) {
        boletos.add(boleto);
    }

    /**
     * CRUD Mostrar - Muestra todos los boletos registrados en consola.
     */
    public void mostrarBoletos() {
        if (boletos.isEmpty()) {
            System.out.println("No hay boletos registrados.");
        } else {
            for (Boleto b : boletos) {
                System.out.println("ID: " + b.getNumeroID() + ", Nombre: " + b.getNombre() + ", Compra: " + b.isCompraBoletos() + ", Ruta: " + b.getRutaString());
            }
        }
    }

    /**
     * CRUD Leer - Busca un boleto por su número de ID.
     * @param id Número de identificación del boleto.
     * @return El objeto Boleto si se encuentra, de lo contrario null.
     */
    public Boleto buscarBoletoPorId(int id) {
        for (Boleto b : boletos) {
            if (b.getNumeroID() == id) {
                return b;
            }
        }
        System.out.println("Boleto con ID " + id + " no encontrado.");
        return null;
    }

    /**
     * CRUD Actualizar - Actualiza los datos de un boleto existente.
     * @param id Número de identificación del boleto.
     * @param nuevoNombre Nuevo nombre del pasajero asociado al boleto.
     * @param compraBoleto Indica si se realizó la compra del boleto.
     * @param ruta Ruta asociada al boleto.
     * @param precio Precio actualizado del boleto.
     */
    public void actualizarBoleto(int id, String nuevoNombre, boolean compraBoleto, Rutas ruta, double precio) {
        Boleto b = buscarBoletoPorId(id);
        if (b != null) {
            b.setNombre(nuevoNombre);
            b.setCompraBoletos(compraBoleto);
            b.setRuta(ruta);
            b.setPrecio(precio);

            System.out.println("Boleto actualizado.");
        } else {
            System.out.println("Boleto no encontrado.");
        }
    }

    /**
     * CRUD Eliminar - Elimina un boleto de la lista a partir de su ID.
     * @param id Número de identificación del boleto a eliminar.
     */
    public void eliminarBoleto(int id) {
        for (int i = 0; i < boletos.size(); i++) {
            if (boletos.get(i).getNumeroID() == id) {
                boletos.remove(i);
                System.out.println("Boleto eliminado.");
                return;
            }
        }
        System.out.println("Boleto no encontrado.");
    }

}
