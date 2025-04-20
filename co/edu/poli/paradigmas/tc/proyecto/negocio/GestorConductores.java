package co.edu.poli.paradigmas.tc.proyecto.negocio;
import co.edu.poli.paradigmas.tc.proyecto.entities.*;
import java.util.*;
public class GestorConductores {
    private ArrayList<Conductor> ListaConductores= new ArrayList<>();
    private ArrayList<Vehiculo> listaVehiculos= new ArrayList<>();

    public ArrayList<Vehiculo> getListaVehiculos() {return listaVehiculos;}
    public void agregarVehiculo(Vehiculo vehiculo){listaVehiculos.add(vehiculo);}
    public ArrayList<Conductor> getListaConductores() {
        return ListaConductores;
    }

    public void agregarConductor(Conductor conductor){ListaConductores.add(conductor);}

    Scanner sc = new Scanner(System.in);

    public void MostrarConductores(){
        if(ListaConductores.isEmpty()){
            System.out.println("No hay conductores registrados");
        }else{
            for(Conductor conductor:ListaConductores){
                System.out.println("Nombre: "+conductor.getNombre()+
                        "\nNumero ID: "+conductor.getNumeroID()+
                        "\nEstado de licencia: "+conductor.isLicencia()+
                        "\nTipo de conductor: "+conductor.getTipodeConductor()+
                        "\nDisponibilidad: "+conductor.isDisponibilidad()+
                        "\n---------------------------------------------------------");
            }
        }
    }

    public Conductor buscarConductorPorID(int ID){
        for(Conductor conductor:ListaConductores){
            if(conductor.getNumeroID()==ID){
                return conductor;
            }
        }
        return null;
    }
    public void ActualizarConductores(String TipoConductor, int ID){
        Conductor conductor = buscarConductorPorID(ID);
        if(conductor!=null){
            conductor.setTipodeConductor(TipoConductor);
            System.out.println("Tipo de conductor actualizado a: "+TipoConductor);
        }else{
            System.out.println("No se encontro ningun conductor con el ID: "+ID);
        }
        System.out.println("Va a cambiar el estado de licencia? (S/N):");
        if(sc.nextLine().equalsIgnoreCase("S")){
            conductor.CambiarEstadoLicencia();
        }
        System.out.println("Va a cambiar la disponibilidad? (S/N):");
        if(sc.nextLine().equalsIgnoreCase("S")){
            conductor.CambiarDisponibilidad();
        }
    }
    public void EliminarConductor(int ID){
        Conductor conductor = buscarConductorPorID(ID);
        if(conductor!=null){
            ListaConductores.remove(conductor);
            System.out.println("Conductor eliminado: "+conductor.getNombre());
        }else{
            System.out.println("No se encontro ningun conductor con el ID: "+ID);
        }
    }
    public Vehiculo buscarVehiculo(String numeroPlaca) {
        for (Vehiculo vehiculoTemp : listaVehiculos) {
            if (vehiculoTemp.getNumeroPlaca().equals(numeroPlaca)) {
                return vehiculoTemp;
            }
        }
        return null;
    }

    public void AñadirConductores(int ID, Vehiculo vehiculo){
        Conductor conductor = buscarConductorPorID(ID);
        if(conductor!=null){
            conductor.agregarVehiculo(vehiculo);
        }
    }

    public void EliminarVehiculo(int ID, String Placa){
        Conductor conductor=buscarConductorPorID(ID);
        if(conductor!=null){
            Vehiculo vehiculo = buscarVehiculo(Placa);
            if (vehiculo != null) {
               listaVehiculos.remove(vehiculo);
                System.out.println("Vehiculo eliminado: "+Placa);
            }else{
                System.out.println("No se encontro ningun vehiculo con el ID: "+Placa);
            }
        }
    }
    public void MostrarVehiculos(int ID){
        Conductor conductor = buscarConductorPorID(ID);
        if(conductor!=null){
            conductor.mostrarVehiculos();
        }else{
            System.out.println("No se encontro ningun conductor con el ID: "+ID);
        }
    }

}
