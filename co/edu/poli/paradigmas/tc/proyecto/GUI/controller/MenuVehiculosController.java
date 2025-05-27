package co.edu.poli.paradigmas.tc.proyecto.GUI.controller;

import co.edu.poli.paradigmas.tc.proyecto.GUI.MenuPrincipalController;
import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;
import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
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

public class MenuVehiculosController {

    private GestorVehiculos gestorVehiculos; // No inicializar aquí
    private GestorRutas gestorRutas;
    private GestorPasajeros gestorPasajeros;
    private GestorConductores gestorConductores;
    private GestorTaller gestorTaller;

    public void setGestorTaller(GestorTaller gestorTaller) {
        this.gestorTaller = gestorTaller;
    }

    public void setGestorConductores(GestorConductores gestorConductores) {
        this.gestorConductores = gestorConductores;
    }

    public void setGestorRutas(GestorRutas gestorRutas) {
        this.gestorRutas = gestorRutas;
    }
    public void setGestorPasajeros(GestorPasajeros gestorPasajeros) {
        this.gestorPasajeros = gestorPasajeros;
    }
    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) {
        this.gestorVehiculos = gestorVehiculos;
    }

    @FXML
    private void agregarNuevoVehiculo(ActionEvent event) {
        if (gestorRutas == null) {
            mostrarMensaje("Error", "El Gestor de Rutas no ha sido inicializado.");
            return;
        }

        Optional<String> placa = pedirInput("Agregar Vehículo", "Ingrese el número de placa:");
        if (placa.isEmpty()) return;

        Optional<String> modelo = pedirInput("Agregar Vehículo", "Ingrese el modelo del vehículo:");
        if (modelo.isEmpty()) return;

        // Obtener la lista de IDs de las rutas disponibles desde el GestorRutas
        List<String> listaIDsRutas = gestorRutas.obtenerListadoRutas().lines()
                .filter(line -> line.startsWith("ID:"))
                .map(line -> line.substring(line.indexOf(":") + 1).trim())
                .collect(Collectors.toList());

        if (listaIDsRutas.isEmpty()) {
            mostrarMensaje("Advertencia", "No hay rutas registradas. Debe crear una ruta antes de agregar un vehículo.");
            return;
        }

        // Mostrar un ChoiceDialog para que el usuario seleccione una ruta
        ChoiceDialog<String> rutaDialog = new ChoiceDialog<>(listaIDsRutas.get(0), listaIDsRutas);
        rutaDialog.setTitle("Agregar Vehículo");
        rutaDialog.setHeaderText("Seleccione la ruta asignada al vehículo:");
        rutaDialog.setContentText("Rutas disponibles:");
        Optional<String> idRutaSeleccionada = rutaDialog.showAndWait();

        if (idRutaSeleccionada.isEmpty()) return;

        Optional<String> pasajerosStr = pedirInput("Agregar Vehículo", "Ingrese el número de pasajeros:");
        if (pasajerosStr.isEmpty()) return;

        if (placa.isPresent() && modelo.isPresent() && idRutaSeleccionada.isPresent() && pasajerosStr.isPresent()) {
            String numeroPlaca = placa.get().trim().toUpperCase();
            String modeloVehiculo = modelo.get().trim();
            String idRuta = idRutaSeleccionada.get();
            try {
                int numeroPasajeros = Integer.parseInt(pasajerosStr.get().trim());
                Rutas ruta = gestorRutas.buscarRutaPorID(idRuta);
                if(numeroPasajeros <= 0) {
                    mostrarMensaje("Error", "El número de pasajeros debe ser mayor que cero.");
                    return;
                }
                if(gestorVehiculos.BuscarVehiculoBoolean(numeroPlaca)){
                    mostrarMensaje("Error", "Ya existe un vehículo con la placa: " + numeroPlaca);
                    return;
                }
                if (ruta != null) {
                    Vehiculo nuevoVehiculo = new Vehiculo(numeroPlaca, modeloVehiculo, (byte) numeroPasajeros, ruta, true, true);
                    gestorVehiculos.agregarVehiculo(nuevoVehiculo);
                    mostrarMensaje("Éxito", "Vehículo agregado correctamente con placa: " + numeroPlaca + " a la ruta " + idRuta);
                } else {
                    mostrarMensaje("Error", "No se encontró la ruta con ID: " + idRuta);
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error", "El número de pasajeros debe ser un número válido.");
            }
        } else {
            mostrarMensaje("Error", "Todos los campos deben estar llenos.");
        }
    }

    @FXML
    private void buscarVehiculoPorPlaca(ActionEvent event) {
        Optional<String> placa = pedirInput("Buscar Vehículo", "Ingrese el número de placa a buscar:");
        if (placa.isPresent()) {
            String numeroPlaca = placa.get().trim().toUpperCase();
            Vehiculo vehiculo = gestorVehiculos.buscarVehiculo(numeroPlaca);
            if (vehiculo != null) {
                String info = "Número de Placa: " + vehiculo.getNumeroPlaca() + "\n"
                        + "Modelo: " + vehiculo.getModelo() + "\n"
                        + "Ruta ID: " + vehiculo.getRuta().getNumeroID() + "\n"
                        + "Número de Pasajeros: " + vehiculo.getNumeroPasajeros() + "\n"
                        + "Disponible: " + (vehiculo.isDisponibilidad() ? "Sí" : "No") + "\n"
                        + "Conductor Disponible: " + (vehiculo.isDisponibilidadConductor() ? "Sí" : "No");
                mostrarMensaje("Vehículo encontrado", info);
            } else {
                mostrarMensaje("Error", "No se encontró ningún vehículo con la placa: " + numeroPlaca);
            }
        }
    }

    @FXML
    private void actualizarDisponibilidadVehiculo(ActionEvent event) {
        Optional<String> placa = pedirInput("Actualizar Vehículo", "Ingrese el número de placa del vehículo a actualizar:");
        if (placa.isEmpty()) return;

        Optional<Boolean> estaEnTaller = mostrarConfirmacion("Actualizar Vehículo", "¿El vehículo está en taller?");
        if (estaEnTaller.isEmpty()) return;

        Optional<Boolean> disponibilidadConductor = mostrarConfirmacion("Actualizar Vehículo", "¿El conductor está disponible?");
        if (disponibilidadConductor.isEmpty()) return;

        String numeroPlaca = placa.get().trim().toUpperCase();
        boolean enTaller = estaEnTaller.get();
        boolean conductorDisponible = disponibilidadConductor.get();

        if (gestorVehiculos.actualizarVehiculo(numeroPlaca, enTaller, conductorDisponible)) {
            mostrarMensaje("Éxito", "El vehículo con placa " + numeroPlaca + " ha sido actualizado.");
        } else {
            mostrarMensaje("Error", "No se encontró ningún vehículo con la placa: " + numeroPlaca);
        }
    }

    @FXML
    private void eliminarVehiculoPorPlaca(ActionEvent event) {
        Optional<String> placa = pedirInput("Eliminar Vehículo", "Ingrese el número de placa del vehículo a eliminar:");
        if (placa.isPresent()) {
            String numeroPlaca = placa.get().trim().toUpperCase();
            if (gestorVehiculos.eliminarVehiculo(numeroPlaca)) {
                mostrarMensaje("Éxito", "El vehículo con placa " + numeroPlaca + " ha sido eliminado.");
            } else {
                mostrarMensaje("Error", "No se encontró ningún vehículo con la placa: " + numeroPlaca);
            }
        }
    }

    @FXML
    private void mostrarTodosLosVehiculos(ActionEvent event) {
        StringBuilder listado = new StringBuilder();
        for (Vehiculo vehiculo : gestorVehiculos.obtenerListaVehiculos()) {
            listado.append("Número Placa: ").append(vehiculo.getNumeroPlaca()).append("\n");
            listado.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
            listado.append("Ruta ID: ").append(vehiculo.getRuta().getNumeroID()).append("\n");
            listado.append("Número Pasajeros: ").append(vehiculo.getNumeroPasajeros()).append("\n");
            listado.append("Disponible: ").append(vehiculo.isDisponibilidad() ? "Sí" : "No").append("\n");
            listado.append("Conductor Disponible: ").append(vehiculo.isDisponibilidadConductor() ? "Sí" : "No").append("\n");
            listado.append("------------------------\n");
        }

        if (listado.isEmpty()) {
            mostrarMensaje("Vehículos", "No hay vehículos registrados.");
        } else {
            mostrarMensaje("Vehículos Registrados", listado.toString());
        }
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

    // Metodo para pedir input y obligar al usuario a llenar el campo
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
                // Confirmacion para cancelar todo
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Cancelar");
                confirmacion.setHeaderText("¿Estás seguro que deseas cancelar?");
                confirmacion.setContentText("Si confirmas, se cancelará todo el proceso.");

                Optional<ButtonType> opcion = confirmacion.showAndWait();
                if (opcion.isPresent() && opcion.get() == ButtonType.OK) {
                    return Optional.empty(); // Se cancela todo
                }
                // Si elige Cancelar en la confirmación, se repite el input
            }
        }
    }

    // Metodo para mostrar mensajes
    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Metodo para mostrar una confirmación (Sí/No)
    private Optional<Boolean> mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        Optional<ButtonType> result = alert.showAndWait();
        return result.map(buttonType -> buttonType == ButtonType.OK);
    }
}