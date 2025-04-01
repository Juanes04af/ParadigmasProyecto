package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Conductor {

    private boolean Licencia;
    private boolean Disponibilidad;
    private String TipodeConductor;
    private String NombredeConductor;
    private long NumeroID;

    public Conductor(boolean licencia, boolean disponibilidad, String tipodeConductor, String nombredeConductor, long numeroID) {
        Licencia = licencia;
        Disponibilidad = disponibilidad;
        TipodeConductor = tipodeConductor;
        NombredeConductor = nombredeConductor;
        NumeroID = numeroID;
    }


    public void CambiarEstadoLicencia() {

        if (Licencia) {
            Disponibilidad = false;
            Licencia = false;
            System.out.println("El conductor: " + NombredeConductor + " con numero ID " + NumeroID + "  no esta habilitado");
        } else {
            Disponibilidad=true;
            System.out.println("El conductor: " + NombredeConductor + " con numero ID " + NumeroID + "  esta habilitado");
        }
    }

    public void CambiarDisponibilidad() {

        if (Disponibilidad) {
            Disponibilidad = false;
            System.out.println("El conductor: " + NombredeConductor + " con numero ID " + NumeroID + "  no esta disponible");
        } else {
            Disponibilidad=true;
            System.out.println("El conductor: " + NombredeConductor + " con numero ID " + NumeroID + "  esta disponible");
        }
    }

    public void setTipodeConductor(String tipodeConductor) {
        TipodeConductor = tipodeConductor;
    }

    public void setCambiarDatos(String nombredeConductor, long numeroID) {
        NombredeConductor=nombredeConductor;
        NumeroID=numeroID;
        System.out.println("Los nuevos datos son:  "+NombredeConductor+" Y "+NumeroID);
    }

}
