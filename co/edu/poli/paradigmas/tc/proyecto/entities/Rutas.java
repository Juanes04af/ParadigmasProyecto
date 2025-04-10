package co.edu.poli.paradigmas.tc.proyecto.entities;

public class Rutas {

    private long NumeroID;
    private String Origen;
    private String Destino;
    private int Distancia;
    private String [] HorariosSalida;
    public Rutas(long numeroID, String origen, String destino, int distancia, String[] horariosSalida) {
        super();
        NumeroID = numeroID;
        Origen = origen;
        Destino = destino;
        Distancia = distancia;
        HorariosSalida = horariosSalida;
    }
    public long getNumeroID() {
        return NumeroID;
    }

    public void setNumeroID(long numeroID) {
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
}
