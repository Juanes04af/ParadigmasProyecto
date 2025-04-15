package co.edu.poli.paradigmas.tc.proyecto.negocio;

import co.edu.poli.paradigmas.tc.proyecto.entities.TallerMantenimiento;

import java.util.ArrayList;

public class GestorTaller {
    private ArrayList<TallerMantenimiento> registrosMantenimiento = new ArrayList<>();

   public void agregarRegistro (TallerMantenimiento mantenimiento) {
       registrosMantenimiento.add(mantenimiento);
   }

   public TallerMantenimiento buscarMantenimientoId (int id) {
       for (TallerMantenimiento mantenimientoVehc : registrosMantenimiento) {
           if (mantenimientoVehc.getId() == id) {
               return mantenimientoVehc;
           }
       }
       return null;
   }

    public boolean eliminarMantenimiento(int id) {
        TallerMantenimiento mantenimiento = buscarMantenimientoId(id);
        if (mantenimiento != null) {
            registrosMantenimiento.remove(mantenimiento);
            System.out.println("Registro eliminado correctamente.");
            return true;
        }
        return false;
    }

   public boolean actualizarEstado (int id, String nuevoEstado) {
       TallerMantenimiento mantenimiento = buscarMantenimientoId(id);
       if (mantenimiento != null) {
           mantenimiento.setEstado(nuevoEstado);
           System.out.println("Estado actualizado correctamente.");
           return true;
       }
       return false;
   }

    public void mostrarTodos() {
        if (registrosMantenimiento.isEmpty()) {
            System.out.println("No hay registros de mantenimiento.");
        } else {
            for (TallerMantenimiento registro : registrosMantenimiento) {
                System.out.println(registro);
            }
        }
    }

}
