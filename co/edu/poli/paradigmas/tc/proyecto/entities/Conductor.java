package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Conductor extends Persona {

    private boolean licencia;
    private boolean disponibilidad;
    private String TipodeConductor;

    public Conductor(boolean licencia, boolean disponibilidad, String tipodeConductor, String nombre, int id) {
        super(id, nombre);
        licencia = licencia;
        this.disponibilidad = disponibilidad;
        TipodeConductor = tipodeConductor;
    }


    public void CambiarEstadoLicencia() {
        if (licencia) {
            disponibilidad = false;
            licencia = false;
            System.out.println("El conductor: " + nombre + " con numero ID " + numeroID + "  no esta habilitado");
        } else {
            disponibilidad =true;
            System.out.println("El conductor: " + nombre + " con numero ID " + numeroID + "  esta habilitado");
        }
    }

    public void CambiarDisponibilidad() {

        if (disponibilidad) {
            disponibilidad = false;
            System.out.println("El conductor: " + nombre + " con numero ID " + numeroID + "  no esta disponible");
        } else {
            disponibilidad =true;
            System.out.println("El conductor: " + nombre + " con numero ID " + numeroID + "  esta disponible");
        }
    }

    public void setTipodeConductor(String tipodeConductor) {
        TipodeConductor = tipodeConductor;
    }

    public void setCambiarDatos(String nombredeConductor, int numeroID) {
        nombre =nombredeConductor;
        this.numeroID =numeroID;
        System.out.println("Los nuevos datos son:  "+ nombre +" Y "+ this.numeroID);
    }

}
