package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
import java.util.ArrayList;

public class GestorVehiculos {
    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    /**
     * Metodo para pasarle la lista de vehiculos al gestor de 'taller' en el main
     * @return La lista de los vehiculos agregados.
     */
    public ArrayList<Vehiculo> obtenerListaVehiculos() {
        return listaVehiculos;
    }

    /**
     * CRUD Agregar -Agrega cada vehiculo al arrayList de 'listaVehiculos'.
     * @param vehiculo Es el vehiculo que se agrega.
     */
    public void agregarVehiculo(Vehiculo vehiculo) {
        listaVehiculos.add(vehiculo);
    }

    /**
     * CRUD Mostrar -Muestra todos los vehiculos agregados dentro de la lista mediante un ciclo for.
     */
    public void mostrarVehiculos() {
        if (listaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            for (Vehiculo vehiculo : listaVehiculos) {
                System.out.println("Numero placa: " + vehiculo.getNumeroPlaca());
                System.out.println("Modelo: " + vehiculo.getModelo());
                System.out.println("Ruta: " + vehiculo.getRuta().getNumeroID());
                System.out.println("Numero Pasajeros: " + vehiculo.getNumeroPasajeros());
                System.out.println("Disponibilidad del vehiculo: " + vehiculo.isDisponibilidad());
                System.out.println("Disponibilidad del conductor: " + vehiculo.isDisponibilidadConductor());
                System.out.println("\n------------------------");
            }
        }
    }

    /**
     * CRUD Actualizar -Actualiza los parametros ingresados para el vehiculo segun la informacion que proporcione el usuario.
     * @param numeroPlaca es el numero de placa con el que se registra el vehiculo.
     * @param estaEnTaller valida si el vehiculo esta disponible o en mantenimiento.
     * @param disponibilidadConductor valida si el conductor esta capacitado para conducir el vehiculo.
     * @return 'true' si el vehiculo fue encontrado y actualizado correctamente; 'false' si no se encontro el vehiculo
     */
    public boolean actualizarVehiculo(String numeroPlaca, boolean estaEnTaller, boolean disponibilidadConductor) {
        Vehiculo vehiculo = buscarVehiculo(numeroPlaca);
        if (vehiculo != null) {
            vehiculo.setDisponibilidad(!estaEnTaller);
            vehiculo.setDisponibilidadConductor(disponibilidadConductor);//La disponobilidad del conductor no cambia si el vehiculo no esta disponible, Pero la disponibilidad del vehiculo cambia cuando el conductor cambia
            return true;
        }
        return false;
    }

    /**
     * CRUD Busqueda -Busca el vehiculo en la lista dependiendo de su placa.
     * @param numeroPlaca La placa es el parametro de busqueda.
     * @return El vehiculo correspondiente si se encuentra y 'null' si no se encuentra.
     */
    public Vehiculo buscarVehiculo(String numeroPlaca) {
        for (Vehiculo vehiculoTemp : listaVehiculos) {
            if (vehiculoTemp.getNumeroPlaca().equals(numeroPlaca)) {
                return vehiculoTemp;
            }
        }
        return null;
    }

    public boolean BuscarVehiculoBoolean(String numeroPlaca) {
        for (Vehiculo vehiculoTemp : listaVehiculos) {
            if (vehiculoTemp.getNumeroPlaca().equals(numeroPlaca)) {
                return true;
            }
        }
        return false;
    }

    /**
     * CRUD Eliminar -Elimina el vehiculo con la placa que el usuario ingrese.
     * @param numeroPlaca -Parametro de busqueda para eliminar un determinado vehiculo.
     * @return 'true' Si se encuentra y elimina el vehiculo; 'false' en caso contrario.
     */
    public boolean eliminarVehiculo(String numeroPlaca) {
        Vehiculo vehiculo = buscarVehiculo(numeroPlaca);
        if (vehiculo != null) {
            listaVehiculos.remove(vehiculo);
            return true;
        }
        return false;
    }
}
