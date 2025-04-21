package co.edu.poli.paradigmas.tc.proyecto.negocio;
import co.edu.poli.paradigmas.tc.proyecto.entities.Boleto;
import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;

import java.util.ArrayList;

public class GestorBoletos {
    private ArrayList<Boleto> boletos = new ArrayList<>();
    private static final double precioFijo = 3500.0;

    public static double getPrecioFijo() {
        return precioFijo;
    }

    // Crear
    public void crearBoleto(Boleto boleto) {
        boletos.add(boleto);
    }

    // Leer
    public void mostrarBoletos() {
        if (boletos.isEmpty()) {
            System.out.println("No hay boletos registrados.");
        } else {
            for (Boleto b : boletos) {
                System.out.println("ID: " + b.getNumeroID() + ", Nombre: " + b.getNombre() + ", Compra: " + b.isCompraBoletos() + ", Ruta: " + b.getRuta());
            }
        }
    }

    public Boleto buscarBoletoPorId(int id) {
        for (Boleto b : boletos) {
            if (b.getNumeroID() == id) {
                return b;
            }
        }
        System.out.println("Boleto con ID " + id + " no encontrado.");
        return null;
    }

    // Actualizar
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

    // Eliminar
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
