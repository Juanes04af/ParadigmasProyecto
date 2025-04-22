package co.edu.poli.paradigmas.tc.proyecto.entities;

import java.util.ArrayList;

public class Conductor extends Persona {

    private boolean licencia;
    private boolean disponibilidad;
    private String TipodeConductor;
    private ArrayList<Vehiculo> vehiculos;

    /**
     * Metodo constructor del tipo persona conductor
     * @param nombre Nombre de cada Conductor
     * @param id ID unico para cada conductor
     * @param licencia Parametro que nos dira si la licencia del conductor esta activa o inactiva
     * @param disponibilidad Parametro que nos dira si el conductor esta disponible o no estara disponible
     * @param tipodeConductor String que nos dira el tipo de conducto es cada uno de los objetos
     */
    public Conductor(String nombre, int id, boolean licencia, boolean disponibilidad, String tipodeConductor ) {
        super(id, nombre);
        this.licencia = licencia;
        this.disponibilidad = disponibilidad;
        this.TipodeConductor = tipodeConductor;
        this.vehiculos = new ArrayList<>();
    }

    // Getters & Setters

    public boolean isLicencia() {
        return licencia;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public String getTipodeConductor() {
        return TipodeConductor;
    }

    public void setTipodeConductor(String tipodeConductor) {
        TipodeConductor = tipodeConductor;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    //Otros Metodos...

    /**
     * Metodo encargado de ingresar el vehiculo a la lista de vehiculos de cada conductor
      * @param v vehiculo que se va añadir a la lista de vehiculo
     */
    public void agregarVehiculo(Vehiculo v) {
        vehiculos.add(v);
    }

    /**
     * Metodo que elimina un vehiculo de la lista de vehiculos de cada conductor
     * @param v Vehiculo que se va eliminar de la lista
     */
    public void eliminarVehiculo(Vehiculo v) {
        vehiculos.remove(v);
    }

    /**
     * Metodo que buscara dentro de la lista un vehiculo por su placa
     * @param placa Placa del vehiculo que se va a buscar dentro de la Lista de vehiculos
     * @return Vehiculo que se va a retornar al ser encontrado, en caso de no encontrarlo retornara null
     */
    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getNumeroPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }


    /**
     * Metodo que recorre toda la lista de vehiculos y mostara los datos de cada uno
     */
    public void mostrarVehiculos() {
        if (vehiculos.isEmpty()) {
            System.out.println("Este conductor no tiene vehículos asignados.");
        } else {
            System.out.println("Vehículos asignados:");
            for (Vehiculo v : vehiculos) {
                System.out.println("Placa: " + v.getNumeroPlaca() +
                        "\nModelo: " + v.getModelo()+
                        "\nRuta: "+v.getRuta().getRutaString());
            }
        }
    }

    /**
     * Cambia el estado de la licencia del conductor y ajusta su disponibilidad en base al estado actual de la licencia.
     */
    public void CambiarEstadoLicencia() {
        if (licencia) {
            disponibilidad = false;
            licencia = false;
            System.out.println("El conductor: " + getNombre() + " con numero ID " + getNumeroID() + "  no esta habilitado");
        } else {
            disponibilidad =true;
            System.out.println("El conductor: " + getNombre() + " con numero ID " + getNumeroID() + "  esta habilitado");
        }
    }

    /**
     * Cambia el estado de disponibilidad del conductor entre disponible e indisponible y muestra un mensaje indicando su nuevo estado.
     *
     */
    public void CambiarDisponibilidad() {

        if (disponibilidad) {
            disponibilidad = false;
            System.out.println("El conductor: " + getNombre() + " con numero ID " + getNumeroID() + "  no esta disponible");
        } else {
            disponibilidad =true;
            System.out.println("El conductor: " + getNombre() + " con numero ID " + getNumeroID() + "  esta disponible");
        }
    }


}
