package co.edu.poli.paradigmas.tc.proyecto.entities;

public class TallerMantenimiento {
    private int id;
    private Vehiculo vehiculo;
    private String tipoMantenimiento;
    private String fechaIngreso;
    private String fechaSalidaEstimada;
    private String estado;
    private String observaciones;

    public TallerMantenimiento(int id, Vehiculo vehiculo, String tipoMantenimiento, String fechaIngreso, String fechaSalidaEstimada, String estado, String observaciones) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.tipoMantenimiento = tipoMantenimiento;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalidaEstimada = fechaSalidaEstimada;
        this.estado = estado;
        this.observaciones = observaciones;

    }

    public TallerMantenimiento(int id, String tipoMantenimiento, String estado, String observaciones) {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalidaEstimada() {
        return fechaSalidaEstimada;
    }

    public void setFechaSalidaEstimada(String fechaSalidaEstimada) {
        this.fechaSalidaEstimada = fechaSalidaEstimada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

