package co.edu.poli.paradigmas.tc.proyecto.GUI.controller;

import co.edu.poli.paradigmas.tc.proyecto.GUI.MenuPrincipalController;
import co.edu.poli.paradigmas.tc.proyecto.entities.Conductor;
import co.edu.poli.paradigmas.tc.proyecto.entities.Vehiculo;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorConductores;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorVehiculos;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorRutas;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorPasajeros;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuConductoresController {

    private GestorConductores gestorConductores;
    private GestorVehiculos gestorVehiculos; // Para asignar vehículos a conductores, si es necesario
    private GestorRutas gestorRutas; // Para volver al menú principal
    private GestorPasajeros gestorPasajeros; // Para volver al menú principal

    // Métodos set para recibir los gestores
    public void setGestorConductores(GestorConductores gestorConductores) {
        this.gestorConductores = gestorConductores;
        if (this.gestorConductores == null) {
            System.err.println("Alerta: GestorConductores no se inicializó correctamente.");
        }
    }

    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) {
        this.gestorVehiculos = gestorVehiculos;
    }
    public void setGestorRutas(GestorRutas gestorRutas) {
        this.gestorRutas = gestorRutas;
    }
    public void setGestorPasajeros(GestorPasajeros gestorPasajeros) {
        this.gestorPasajeros = gestorPasajeros;
    }

    @FXML
    private void agregarConductor(ActionEvent event) {
        if (gestorConductores == null) {
            mostrarMensaje("Error", "El gestor de conductores no está inicializado.");
            return;
        }

        Optional<String> nombreInput = pedirInput("Agregar Conductor", "Ingrese el nombre del conductor:");
        if (nombreInput.isEmpty()) return;

        Optional<String> idInput = pedirInput("Agregar Conductor", "Ingrese el número de ID del conductor:");
        if (idInput.isEmpty()) return;

        Optional<String> tipoConductorInput = pedirChoice("Agregar Conductor", "Seleccione el tipo de conductor:", "Principal", "Auxiliar");
        if (tipoConductorInput.isEmpty()) return;

        Optional<Boolean> licenciaValida = mostrarConfirmacion("Agregar Conductor", "¿Tiene licencia válida?");
        if (licenciaValida.isEmpty()) return;

        Optional<Boolean> disponibilidad = mostrarConfirmacion("Agregar Conductor", "¿Está disponible?");
        if (disponibilidad.isEmpty()) return;

        try {
            String nombre = nombreInput.get();
            int id = Integer.parseInt(idInput.get());
            String tipoConductor = tipoConductorInput.get();
            boolean licencia = licenciaValida.get();
            boolean disponible = disponibilidad.get();

            Conductor nuevoConductor = new Conductor(nombre, id, licencia, disponible, tipoConductor);
            gestorConductores.agregarConductor(nuevoConductor);
            mostrarMensaje("Éxito", "Conductor agregado correctamente con ID: " + id);

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "El ID debe ser un número válido.");
        }
    }

    @FXML
    private void buscarConductor(ActionEvent event) {
        if (gestorConductores == null) {
            mostrarMensaje("Error", "El gestor de conductores no está inicializado.");
            return;
        }

        Optional<String> idInput = pedirInput("Buscar Conductor", "Ingrese el ID del conductor a buscar:");
        if (idInput.isEmpty()) return;

        try {
            int id = Integer.parseInt(idInput.get());
            Conductor conductor = gestorConductores.buscarConductorPorID(id);
            if (conductor != null) {
                String info = "Nombre: " + conductor.getNombre() + "\n"
                        + "Número ID: " + conductor.getNumeroID() + "\n"
                        + "Estado de licencia: " + (conductor.isLicencia() ? "Válida" : "No válida") + "\n"
                        + "Tipo de conductor: " + conductor.getTipodeConductor() + "\n"
                        + "Disponibilidad: " + (conductor.isDisponibilidad() ? "Disponible" : "No disponible");
                mostrarMensaje("Conductor Encontrado", info);
            } else {
                mostrarMensaje("No encontrado", "No se encontró ningún conductor con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID inválido. Debe ser un número.");
        }
    }

    @FXML
    private void actualizarConductor(ActionEvent event) {
        if (gestorConductores == null) {
            mostrarMensaje("Error", "El gestor de conductores no está inicializado.");
            return;
        }

        Optional<String> idInput = pedirInput("Actualizar Conductor", "Ingrese el ID del conductor a actualizar:");
        if (idInput.isEmpty()) return;

        try {
            int id = Integer.parseInt(idInput.get());
            Conductor conductor = gestorConductores.buscarConductorPorID(id);

            if (conductor != null) {
                Optional<String> nuevoTipoConductor = pedirChoice("Actualizar Conductor", "Seleccione el nuevo tipo de conductor:", "Principal", "Auxiliar");
                if (nuevoTipoConductor.isEmpty()) return;

                // Actualiza directamente usando el método de GestorConductores si fuera público
                // Aquí adaptamos la lógica que tenías en la consola
                gestorConductores.ActualizarConductores(nuevoTipoConductor.get(), id);

                // Como tu método ActualizarConductores en el gestor interactúa con el Scanner
                // y tiene lógica para cambiar licencia/disponibilidad, podríamos reestructurarlo
                // o hacer llamadas separadas aquí. Por simplicidad, adaptaremos.

                if (mostrarConfirmacion("Actualizar Licencia", "¿Desea cambiar el estado de la licencia?").orElse(false)) {
                    conductor.CambiarEstadoLicencia(); // Asumiendo que Conductor tiene este método
                }
                if (mostrarConfirmacion("Actualizar Disponibilidad", "¿Desea cambiar la disponibilidad?").orElse(false)) {
                    conductor.CambiarDisponibilidad(); // Asumiendo que Conductor tiene este método
                }

                mostrarMensaje("Éxito", "Conductor actualizado correctamente.");
            } else {
                mostrarMensaje("No encontrado", "No se encontró ningún conductor con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID inválido. Debe ser un número.");
        }
    }


    @FXML
    private void eliminarConductor(ActionEvent event) {
        if (gestorConductores == null) {
            mostrarMensaje("Error", "El gestor de conductores no está inicializado.");
            return;
        }

        Optional<String> idInput = pedirInput("Eliminar Conductor", "Ingrese el ID del conductor a eliminar:");
        if (idInput.isEmpty()) return;

        try {
            int id = Integer.parseInt(idInput.get());
            gestorConductores.EliminarConductor(id); // Tu método ya tiene lógica para mostrar mensaje si no existe
            // Podrías añadir un mensaje de éxito si la eliminación fue efectiva
            if(gestorConductores.buscarConductorPorID(id) == null){ // Verificar si realmente se eliminó
                mostrarMensaje("Éxito", "Conductor eliminado correctamente.");
            } else {
                mostrarMensaje("Error", "No se encontró ningún conductor con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID inválido. Debe ser un número.");
        }
    }

    @FXML
    private void mostrarTodosLosConductores(ActionEvent event) {
        if (gestorConductores == null) {
            mostrarMensaje("Error", "El gestor de conductores no está inicializado.");
            return;
        }

        List<Conductor> listaConductores = gestorConductores.getListaConductores();
        if (listaConductores == null || listaConductores.isEmpty()) {
            mostrarMensaje("Conductores", "No hay conductores registrados.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Lista de Conductores:\n\n");
        for (Conductor conductor : listaConductores) {
            mensaje.append("Nombre: ").append(conductor.getNombre()).append("\n");
            mensaje.append("Número ID: ").append(conductor.getNumeroID()).append("\n");
            mensaje.append("Estado de licencia: ").append(conductor.isLicencia() ? "Válida" : "No válida").append("\n");
            mensaje.append("Tipo de conductor: ").append(conductor.getTipodeConductor()).append("\n");
            mensaje.append("Disponibilidad: ").append(conductor.isDisponibilidad() ? "Disponible" : "No disponible").append("\n");
            mensaje.append("---------------------------------------------------------\n");
        }
        mostrarMensaje("Lista de Conductores", mensaje.toString());
    }

    @FXML
    private void mostrarVehiculosConductor(ActionEvent event) {
        if (gestorConductores == null) {
            mostrarMensaje("Error", "El gestor de conductores no está inicializado.");
            return;
        }

        Optional<String> idInput = pedirInput("Vehículos de Conductor", "Ingrese el ID del conductor:");
        if (idInput.isEmpty()) return;

        try {
            int id = Integer.parseInt(idInput.get());
            Conductor conductor = gestorConductores.buscarConductorPorID(id);
            if (conductor != null) {
                ArrayList<Vehiculo> vehiculos = conductor.getVehiculos(); // Asumiendo que Conductor tiene getVehiculos()
                if (vehiculos == null || vehiculos.isEmpty()) {
                    mostrarMensaje("Vehículos", "El conductor " + conductor.getNombre() + " no tiene vehículos asignados.");
                } else {
                    StringBuilder infoVehiculos = new StringBuilder("Vehículos de " + conductor.getNombre() + ":\n\n");
                    for (Vehiculo v : vehiculos) {
                        infoVehiculos.append("Placa: ").append(v.getNumeroPlaca()).append(", Modelo: ").append(v.getModelo()).append("\n");
                    }
                    mostrarMensaje("Vehículos del Conductor", infoVehiculos.toString());
                }
            } else {
                mostrarMensaje("No encontrado", "No se encontró ningún conductor con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID inválido. Debe ser un número.");
        }
    }

    @FXML
    private void asignarVehiculoAConductor(ActionEvent event) {
        if (gestorConductores == null || gestorVehiculos == null) {
            mostrarMensaje("Error", "Los gestores de conductores o vehículos no están inicializados.");
            return;
        }

        Optional<String> idConductorInput = pedirInput("Asignar Vehículo", "Ingrese el ID del conductor:");
        if (idConductorInput.isEmpty()) return;

        try {
            int idConductor = Integer.parseInt(idConductorInput.get());
            Conductor conductor = gestorConductores.buscarConductorPorID(idConductor);

            if (conductor == null) {
                mostrarMensaje("Error", "No se encontró ningún conductor con el ID: " + idConductor);
                return;
            }

            // Obtener la lista de placas de vehículos disponibles (no asignados)
            List<String> placasDisponibles = gestorVehiculos.obtenerListaVehiculos().stream()
                    .filter(Vehiculo::isDisponibilidad) // Filtrar por vehículos disponibles
                    .map(Vehiculo::getNumeroPlaca)
                    .collect(Collectors.toList());

            if (placasDisponibles.isEmpty()) {
                mostrarMensaje("Advertencia", "No hay vehículos disponibles para asignar en este momento.");
                return;
            }

            // Mostrar un ChoiceDialog para que el usuario seleccione una placa
            Optional<String> placaSeleccionada = pedirChoice("Asignar Vehículo", "Seleccione la placa del vehículo a asignar:",
                    placasDisponibles.toArray(new String[0]));

            if (placaSeleccionada.isEmpty()) return; // Usuario canceló

            String placa = placaSeleccionada.get().trim().toUpperCase();
            Vehiculo vehiculo = gestorVehiculos.buscarVehiculo(placa);

            // Doble verificación para asegurar que el vehículo aún exista y sea el correcto
            if (vehiculo != null) {
                conductor.agregarVehiculo(vehiculo); // Asumiendo que Conductor tiene un método agregarVehiculo
                mostrarMensaje("Éxito", "Vehículo " + placa + " asignado a conductor " + conductor.getNombre() + ".");
            } else {
                mostrarMensaje("Error", "El vehículo seleccionado no se encontró o no está disponible.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID de conductor inválido. Debe ser un número.");
        }
    }

    @FXML
    private void eliminarVehiculoDeConductor(ActionEvent event) {
        if (gestorConductores == null || gestorVehiculos == null) {
            mostrarMensaje("Error", "Los gestores de conductores o vehículos no están inicializados.");
            return;
        }

        Optional<String> idConductorInput = pedirInput("Eliminar Vehículo de Conductor", "Ingrese el ID del conductor:");
        if (idConductorInput.isEmpty()) return;

        try {
            int idConductor = Integer.parseInt(idConductorInput.get());
            Conductor conductor = gestorConductores.buscarConductorPorID(idConductor);

            if (conductor == null) {
                mostrarMensaje("Error", "No se encontró ningún conductor con el ID: " + idConductor);
                return;
            }

            // Obtener la lista de vehículos *actualmente asignados a este conductor*
            List<String> placasAsignadas = conductor.getVehiculos().stream() // Asumiendo getVehiculos() en Conductor
                    .map(Vehiculo::getNumeroPlaca)
                    .collect(Collectors.toList());

            if (placasAsignadas.isEmpty()) {
                mostrarMensaje("Advertencia", "El conductor " + conductor.getNombre() + " no tiene vehículos asignados.");
                return;
            }

            // Mostrar un ChoiceDialog para que el usuario seleccione una placa de los asignados
            Optional<String> placaSeleccionada = pedirChoice("Eliminar Vehículo", "Seleccione la placa del vehículo a eliminar:",
                    placasAsignadas.toArray(new String[0]));

            if (placaSeleccionada.isEmpty()) return; // Usuario canceló

            String placa = placaSeleccionada.get().trim().toUpperCase();
            Vehiculo vehiculoAEliminar = conductor.buscarVehiculo(placa); // Asumiendo que Conductor tiene buscarVehiculo(placa)

            if(vehiculoAEliminar != null){
                conductor.eliminarVehiculo(vehiculoAEliminar); // Asumiendo que Conductor tiene eliminarVehiculo(Vehiculo)
                mostrarMensaje("Éxito", "Vehículo " + placa + " eliminado del conductor " + conductor.getNombre() + ".");
            } else {
                mostrarMensaje("Error", "El conductor no tiene asignado el vehículo con placa: " + placa + ".");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID de conductor inválido. Debe ser un número.");
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
            controller.setGestorPasajeros(gestorPasajeros); // No tienes gestor de pasajeros aquí, o si lo tuvieses, pásalo
            controller.setGestorConductores(gestorConductores); // ¡Importante!

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

    // --- Métodos de utilidad compartidos ---

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
        ChoiceDialog<String> dialog = new ChoiceDialog<>(opciones[0], opciones);
        dialog.setTitle(titulo);
        dialog.setHeaderText(header);
        dialog.setContentText("Seleccione una opción:");
        return dialog.showAndWait();
    }

    private Optional<Boolean> mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        Optional<ButtonType> result = alert.showAndWait();
        return result.map(buttonType -> buttonType == ButtonType.OK);
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}