package co.edu.poli.paradigmas.tc.proyecto.negocio;
import co.edu.poli.paradigmas.tc.proyecto.entities.*;
import java.util.*;
public class GestorConductores {
    private ArrayList<Conductor> ListaConductores= new ArrayList<>();

    public ArrayList<Conductor> getListaConductores() {
        return ListaConductores;
    }

    /**
     * Agrega un nuevo conductor a la lista de conductores gestionados.
     *
     * @param conductor El conductor que se desea agregar a la lista.
     */
    public void agregarConductor(Conductor conductor){ListaConductores.add(conductor);}

    Scanner sc = new Scanner(System.in);

    /**
     * Metodo el cual se encarga de mostrar toda la lista de conductores junto a sus atributos y los estados de Disponibilidad y Licencia
     */
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

    /**
     * Busca un conductor en la lista de conductores gestionados basado en su ID único.
     *
     * @param ID El identificador único del conductor que se desea buscar.
     * @return El objeto Conductor correspondiente al ID proporcionado, o null si no se encuentra ningún conductor con ese ID.
     */
    public Conductor buscarConductorPorID(int ID){
        for(Conductor conductor:ListaConductores){
            if(conductor.getNumeroID()==ID){
                return conductor;
            }
        }
        return null;
    }

    /**
     * Actualiza los atributos de un conductor específico, como su tipo de conductor, estado de licencia y disponibilidad.
     *
     * @param TipoConductor El nuevo tipo de conductor que se asignará.
     * @param ID El identificador único del conductor cuyo estado será actualizado.
     */
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

    /**
     * Elimina un conductor de la lista de conductores gestionados, basado en su ID único.
     *
     * @param ID El identificador único del conductor que se desea eliminar.
     */
    public void EliminarConductor(int ID){
        Conductor conductor = buscarConductorPorID(ID);
        if(conductor!=null){
            ListaConductores.remove(conductor);
            System.out.println("Conductor eliminado: "+conductor.getNombre());
        }else{
            System.out.println("No se encontro ningun conductor con el ID: "+ID);
        }
    }

    /**
     * Busca un vehículo dentro de la lista de vehículos asignados a un conductor específico
     * utilizando su número de placa y el ID del conductor.
     *
     * @param numeroPlaca El número de placa del vehículo que se desea buscar.
     * @param IDconductor El ID único del conductor al cual está asociado el vehículo.
     * @return El vehículo correspondiente al número de placa proporcionado,
     *         si el vehículo es encontrado en la lista del conductor. Retorna null
     *         si no se encuentra el vehículo o si el conductor no existe.
     */
    public Vehiculo buscarVehiculo(String numeroPlaca, int IDconductor) {
        Conductor conductor = buscarConductorPorID(IDconductor);
        if (conductor != null) {
            return conductor.buscarVehiculo(numeroPlaca);
        }
        return null;
    }

    /**
     * Elimina un vehículo asociado a un conductor específico basado en la placa proporcionada.
     *
     * @param ConductorEliminar El conductor al cual pertenece el vehículo que se desea eliminar.
     * @param Placa La placa del vehículo que se desea eliminar de la lista del conductor.
     */
    public void EliminarVehiculo(Conductor ConductorEliminar, String Placa){
            Vehiculo vehiculo = buscarVehiculo(Placa, ConductorEliminar.getNumeroID());
            if (vehiculo != null) {
               ConductorEliminar.eliminarVehiculo(vehiculo);
                System.out.println("Vehiculo eliminado: "+Placa);
            }else{
                System.out.println("No se encontro ningun vehiculo con el ID: "+Placa);
            }
    }

    /**
     * Muestra los vehículos asociados a un conductor específico identificado por un ID proporcionado.
     * Si no se encuentra ningún conductor con el ID, muestra un mensaje indicando que no se encontró.
     *
     * @param ID El identificador único del conductor cuyos vehículos se desean mostrar.
     */
    public void MostrarVehiculos(int ID){
        Conductor conductor = buscarConductorPorID(ID);
        if(conductor!=null){
            conductor.mostrarVehiculos();
        }else{
            System.out.println("No se encontro ningun conductor con el ID: "+ID);
        }
    }

}
