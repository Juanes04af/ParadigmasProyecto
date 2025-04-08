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
    public void mostrar(){
        System.out.println(this.NumeroID + " " + this.Origen + " " + this.Destino + " " + this.Distancia + " " + this.HorariosSalida[0]);
    }

}
