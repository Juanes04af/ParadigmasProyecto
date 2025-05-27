package co.edu.poli.paradigmas.tc.proyecto.GUI.controller;

import co.edu.poli.paradigmas.tc.proyecto.GUI.MenuPrincipalController;
import co.edu.poli.paradigmas.tc.proyecto.entities.*;

import co.edu.poli.paradigmas.tc.proyecto.negocio.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuTallerController {

    private GestorConductores gestorConductores;
    private GestorVehiculos gestorVehiculos;
    private GestorRutas gestorRutas;
    private GestorPasajeros gestorPasajeros;
    private GestorTaller gestorTaller;
    private GestorBoletos gestorBoletos;

    // Métodos set para recibir los gestores
    public void setGestorBoletos(GestorBoletos gestorBoletos) {
        this.gestorBoletos = gestorBoletos;
        if (this.gestorBoletos == null) {
            System.err.println("Alerta: GestorBoletos no se inicializo correctamente.");
        }
    }
    public void setGestorConductores(GestorConductores gestorConductores) {
        this.gestorConductores = gestorConductores;
        if (this.gestorConductores == null) {
            System.err.println("Alerta: GestorConductores no se inicializó correctamente.");
        }
    }
    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) {
        this.gestorVehiculos = gestorVehiculos;
    }
    public void setGestorTaller(GestorTaller gestorTaller) {
        this.gestorTaller = gestorTaller;
    }
    public void setGestorRutas(GestorRutas gestorRutas) {
        this.gestorRutas = gestorRutas;
    }
    public void setGestorPasajeros(GestorPasajeros gestorPasajeros) {
        this.gestorPasajeros = gestorPasajeros;
    }

    @FXML
    private void agregarRegistroMantenimiento(ActionEvent event) {
        if (gestorTaller == null || gestorVehiculos == null) {
            mostrarMensaje("Error", "Los gestores no están inicializados correctamente.");
            return;
        }

        Optional<String> idRegistroInput = pedirInput("Agregar Registro", "Ingrese el ID del nuevo registro de mantenimiento:");
        if (idRegistroInput.isEmpty()) return;
        int idRegistro;
        try {
            idRegistro = Integer.parseInt(idRegistroInput.get());
            if (gestorTaller.buscarMantenimientoId(idRegistro) != null) {
                mostrarMensaje("Error", "Ya existe un registro con este ID.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID de registro inválido. Debe ser un número.");
            return;
        }


        List<String> placasVehiculos = gestorVehiculos.obtenerListaVehiculos().stream()
                .map(Vehiculo::getNumeroPlaca)
                .collect(Collectors.toList());

        if (placasVehiculos.isEmpty()) {
            mostrarMensaje("Advertencia", "No hay vehículos registrados para asignar un mantenimiento.");
            return;
        }

        Optional<String> placaVehiculoInput = pedirChoice("Agregar Registro", "Seleccione la placa del vehículo:",
                placasVehiculos.toArray(new String[0]));
        if (placaVehiculoInput.isEmpty()) return;
        String placa = placaVehiculoInput.get();
        Vehiculo vehiculoSeleccionado = gestorVehiculos.buscarVehiculo(placa);

        if (vehiculoSeleccionado == null) {
            mostrarMensaje("Error", "Vehículo no encontrado. Por favor, intente de nuevo.");
            return;
        }


        Optional<String> tipoMantenimientoInput = pedirInput("Agregar Registro", "Ingrese el tipo de mantenimiento (Ej: Preventivo, Correctivo):");
        if (tipoMantenimientoInput.isEmpty()) return;
        String tipoMantenimiento = tipoMantenimientoInput.get();


        Optional<String> fechaIngresoInput = pedirInput("Agregar Registro", "Ingrese la fecha de ingreso (Ej: DD/MM/AAAA):");
        if (fechaIngresoInput.isEmpty()) return;
        String fechaIngreso = fechaIngresoInput.get();


        Optional<String> estadoInput = pedirChoice("Agregar Registro", "Seleccione el estado inicial del mantenimiento:", "En Taller", "Mantenimiento Completado");
        String estado = estadoInput.orElse("En Taller");


        Optional<String> observacionesInput = pedirInput("Agregar Registro", "Ingrese las observaciones adicionales (opcional):");
        String observaciones = observacionesInput.orElse(""); // Si no se ingresa, será una cadena vacía



        TallerMantenimiento nuevoMantenimiento = new TallerMantenimiento(
                idRegistro,
                tipoMantenimiento,
                fechaIngreso,
                estado,
                observaciones,
                vehiculoSeleccionado
        );
        gestorTaller.agregarRegistro(nuevoMantenimiento);

        // Actualizar la disponibilidad del vehículo
        if (estado.equalsIgnoreCase("En Taller")) {
            vehiculoSeleccionado.setDisponibilidad(false);
        } else {
            vehiculoSeleccionado.setDisponibilidad(true);
        }

        mostrarMensaje("Éxito", "Registro de mantenimiento agregado correctamente.");
    }

    @FXML
    private void buscarRegistroMantenimiento(ActionEvent event) {
        if (gestorTaller == null) {
            mostrarMensaje("Error", "El gestor de taller no está inicializado.");
            return;
        }

        Optional<String> idInput = pedirInput("Buscar Registro", "Ingrese el ID del registro de mantenimiento a buscar:");
        if (idInput.isEmpty()) return;

        try {
            int id = Integer.parseInt(idInput.get());
            TallerMantenimiento mantenimiento = gestorTaller.buscarMantenimientoId(id);
            if (mantenimiento != null) {
                mostrarMensaje("Registro Encontrado", mantenimiento.toString());
            } else {
                mostrarMensaje("No encontrado", "No se encontró ningún registro de mantenimiento con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID inválido. Debe ser un número.");
        }
    }

    @FXML
    private void eliminarRegistroMantenimiento(ActionEvent event) {
        if (gestorTaller == null) {
            mostrarMensaje("Error", "El gestor de taller no está inicializado.");
            return;
        }

        Optional<String> idInput = pedirInput("Eliminar Registro", "Ingrese el ID del registro de mantenimiento a eliminar:");
        if (idInput.isEmpty()) return;

        try {
            int id = Integer.parseInt(idInput.get());
            if (gestorTaller.eliminarMantenimiento(id)) {
                mostrarMensaje("Éxito", "Registro de mantenimiento eliminado correctamente.");
            } else {
                mostrarMensaje("No encontrado", "No se encontró ningún registro de mantenimiento con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID inválido. Debe ser un número.");
        }
    }

    @FXML
    private void actualizarEstadoMantenimiento(ActionEvent event) {
        if (gestorTaller == null) {
            mostrarMensaje("Error", "El gestor de taller no está inicializado.");
            return;
        }

        Optional<String> idInput = pedirInput("Actualizar Estado", "Ingrese el ID del registro de mantenimiento a actualizar:");
        if (idInput.isEmpty()) return;

        try {
            int id = Integer.parseInt(idInput.get());
            TallerMantenimiento mantenimiento = gestorTaller.buscarMantenimientoId(id);

            if (mantenimiento != null) {
                Optional<String> nuevoEstado = pedirChoice("Actualizar Estado", "Seleccione el nuevo estado:", "En Taller", "Mantenimiento Completado");
                if (nuevoEstado.isEmpty()) return;

                if (gestorTaller.actualizarEstado(id, nuevoEstado.get())) {
                    mostrarMensaje("Éxito", "Estado de mantenimiento actualizado correctamente.");
                } else {
                    mostrarMensaje("Error", "No se pudo actualizar el estado.");
                }
            } else {
                mostrarMensaje("No encontrado", "No se encontró ningún registro de mantenimiento con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID inválido. Debe ser un número.");
        }
    }

    @FXML
    private void mostrarTodosLosRegistros(ActionEvent event) {
        if (gestorTaller == null) {
            mostrarMensaje("Error", "El gestor de taller no está inicializado.");
            return;
        }

        List<TallerMantenimiento> registros = gestorTaller.getRegistrosMantenimiento();
        if (registros == null || registros.isEmpty()) {
            mostrarMensaje("Registros de Mantenimiento", "No hay registros de mantenimiento.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Lista de Registros de Mantenimiento:\n\n");
        for (TallerMantenimiento registro : registros) {
            mensaje.append(registro.toString()).append("\n---------------------------------------------------------\n");
        }
        mostrarMensaje("Lista de Registros", mensaje.toString());
    }

    @FXML
    private void volverMenuPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuPrincipal.fxml"));
            Parent root = loader.load();

            MenuPrincipalController controller = loader.getController();
            // Pasa todos los gestores de vuelta al MenuPrincipalController
            controller.setGestorRutas(gestorRutas);
            controller.setGestorVehiculos(gestorVehiculos);
            controller.setGestorPasajeros(gestorPasajeros);
            controller.setGestorConductores(gestorConductores);
            controller.setGestorTaller(gestorTaller);
            controller.setGestorBoletos(gestorBoletos);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal");
            stage.setResizable(false);
            stage.show();

            // Cierra la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            mostrarMensaje("Error", "No se pudo cargar el menu principal: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private Optional<String> pedirInput(String titulo, String mensaje) {
        while (true) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle(titulo);
            dialog.setHeaderText(mensaje);
            Optional<String> resultado = dialog.showAndWait();

            if (resultado.isPresent()) {
                String valor = resultado.get().trim();
                if (!valor.isEmpty()) {
                    return Optional.of(valor);
                } else {
                    mostrarMensaje("Campo requerido", "Este campo no puede estar vacío. Por favor, ingrese un valor.");
                }
            } else {
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Cancelar");
                confirmacion.setHeaderText("¿Estás seguro que deseas cancelar?");
                confirmacion.setContentText("Si confirmas, se cancelará todo el proceso.");

                Optional<ButtonType> opcion = confirmacion.showAndWait();
                if (opcion.isPresent() && opcion.get() == ButtonType.OK) {
                    return Optional.empty(); // Se cancela todo
                }
            }
        }
    }

    private Optional<String> pedirChoice(String titulo, String header, String... opciones) {
        if (opciones.length == 0) {
            mostrarMensaje("Error", "No hay opciones disponibles.");
            return Optional.empty();
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(opciones[0], opciones);
        dialog.setTitle(titulo);
        dialog.setHeaderText(header);
        dialog.setContentText("Seleccione una opción:");
        return dialog.showAndWait();
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}