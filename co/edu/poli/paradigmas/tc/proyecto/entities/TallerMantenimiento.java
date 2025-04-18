package co.edu.poli.paradigmas.tc.proyecto.entities;

public class TallerMantenimiento {
    private int id;
    private Vehiculo vehiculo;
    private String tipoMantenimiento;
    private String fechaIngreso;
    private String estado;
    private String observaciones;

    public TallerMantenimiento(int id, String tipoMantenimiento, String fechaIngreso, String estado, String observaciones) {
        this.id = id;
        this.tipoMantenimiento = tipoMantenimiento;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.observaciones = observaciones;

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

    public int getId() { return id; }

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

    @Override
    public String toString() {
        return "ID: " + id +
                ", Fecha de Ingreso: " + fechaIngreso +
                ", Tipo de Mantenimiento: " + tipoMantenimiento +
                ", Estado: " + estado +
                ", Observaciones: " + observaciones +
                ", Vehiculo: " + (vehiculo != null ? vehiculo.toString() : "No asignado");
    }
}

