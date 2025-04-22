package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Rutas {

    private String NumeroID;
    private String Origen;
    private String Destino;
    private int Distancia;
    private String [] HorariosSalida;

    /**
     * Metodo constructor para la clase rutas
     * @param numeroID Numero unico para cada ruta
     * @param origen Lugar donde la ruta iniara la ruta
     * @param destino Lugar donde va a terminar la ruta
     * @param distancia Cantidad de km que recorre la ruta desde origen hasta el destino
     * @param horariosSalida Los horarios de salida de cada ruta
     */
    public Rutas(String numeroID, String origen, String destino, int distancia, String[] horariosSalida) {
        super();
        NumeroID = numeroID;
        Origen = origen;
        Destino = destino;
        Distancia = distancia;
        HorariosSalida = horariosSalida;
    }

    // Getters & Setters

    public String getNumeroID() {
        return NumeroID;
    }

    public void setNumeroID(String numeroID) {
        NumeroID = numeroID;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String origen) {
        Origen = origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public int getDistancia() {
        return Distancia;
    }

    public void setDistancia(int distancia) {
        Distancia = distancia;
    }

    public String[] getHorariosSalida() {
        return HorariosSalida;
    }

    public void setHorariosSalida(String[] horariosSalida) {
        HorariosSalida = horariosSalida;
    }

    public String getRutaString() {
        return Origen+"-"+Destino;
    }

}
