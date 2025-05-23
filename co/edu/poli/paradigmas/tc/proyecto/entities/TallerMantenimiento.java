package co.edu.poli.paradigmas.tc.proyecto.entities;

public class TallerMantenimiento {
    private int id;
    private Vehiculo vehiculo;
    private String tipoMantenimiento;
    private String fechaIngreso;
    private String estado;
    private String observaciones;

    /**
     * Metodo constructor de la clase TallerMantenimiento con sus respectivos atributos.
     * @param id El identificador unico del registro de mantenimiento.
     * @param tipoMantenimiento Informacion del tipo de mantenimiento realizado.
     * @param fechaIngreso La fecha en la que el vehiculo ingreso al taller
     * @param estado El estado actual del vehiculo ('En Taller' o 'Mantenimiento Completo')
     * @param observaciones Comentarios o informcacion adicionales sobre el mantenimiento o el vehiculo.
     * @param vehiculo El vehiculo asociado al registro de mantenimiento
     */
    public TallerMantenimiento(int id, String tipoMantenimiento, String fechaIngreso, String estado, String observaciones, Vehiculo vehiculo) {
        this.id = id;
        this.tipoMantenimiento = tipoMantenimiento;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.observaciones = observaciones;
        this.vehiculo = vehiculo;

    }

    // Getters & Setters

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

    /**
     * Devuelve una representacion en texto del registro de mantenimiento.
     * @return una cadena con la informacion resumida del registro de mantenimiento.
     */
    @Override
    public String toString() {
        return "ID: " + id +
                ", Fecha de Ingreso: " + fechaIngreso +
                ", Tipo de Mantenimiento: " + tipoMantenimiento +
                ", Estado: " + estado +
                ", Observaciones: " + observaciones +
                ", Vehiculo: " + vehiculo.getNumeroPlaca();
    }
}

