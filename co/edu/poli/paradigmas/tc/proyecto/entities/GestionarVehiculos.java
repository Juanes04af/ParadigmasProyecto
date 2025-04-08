package co.edu.poli.paradigmas.tc.proyecto.entities;

public class GestionarVehiculos {
    // Funcionalidades crud
    private Nodo cabeza;

    private class Nodo {
        Vehiculo dato;
        Nodo siguiente;

        public Nodo(Vehiculo dato) {
            this.dato = dato;
        }
    }

    public void añadirVehiculo(Vehiculo vehiculo) {
        Nodo nuevoNodo = new Nodo(vehiculo);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public void mostrarVehiculos() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println("Placa: " + actual.dato.getNumeroPlaca() +
                    ", Modelo: " + actual.dato.getModelo() +
                    ", Pasajeros: " + actual.dato.getNumeroPasajeros());
            actual = actual.siguiente;
        }
    }

    public Vehiculo buscarVehiculo(int numeroPlaca) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.getNumeroPlaca() == numeroPlaca) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean actualizarVehiculo(int numeroPlaca, Vehiculo nuevoVehiculo) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.getNumeroPlaca() == numeroPlaca) {
                actual.dato = nuevoVehiculo;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean eliminarVehiculo(int numeroPlaca) {
        if (cabeza == null) {
            return false;
        }
        if (cabeza.dato.getNumeroPlaca() == numeroPlaca) {
            cabeza = cabeza.siguiente;
            return true;
        }
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.getNumeroPlaca() == numeroPlaca) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}