package co.edu.poli.paradigmas.tc.proyecto.GUI.controller;

import co.edu.poli.paradigmas.tc.proyecto.GUI.MenuPrincipalController;
import co.edu.poli.paradigmas.tc.proyecto.entities.Boleto;
import co.edu.poli.paradigmas.tc.proyecto.entities.Pasajeros;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorPasajeros;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorRutas;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorVehiculos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MenuPasajerosController {

    private GestorRutas gestorRutas;
    private GestorVehiculos gestorVehiculos;
    private GestorPasajeros gestorPasajeros;

    public void setGestorPasajeros(GestorPasajeros gestorPasajeros) {
        this.gestorPasajeros = gestorPasajeros;
        if (this.gestorPasajeros == null) {
            System.err.println("Alerta: GestorPasajeros no se inicializó correctamente.");
        }
    }

    public void setGestorRutas(GestorRutas gestorRutas) {
        this.gestorRutas = gestorRutas;
    }

    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) {
        this.gestorVehiculos = gestorVehiculos;
    }

    @FXML
    private void agregarPasajero(ActionEvent event) {
        if (gestorPasajeros == null) {
            mostrarMensaje("Error", "El gestor de pasajeros no está inicializado.");
            return;
        }
        Optional<String> nombreInput = pedirInput("Agregar Pasajero", "Ingrese el nombre del pasajero:");
        if (nombreInput.isPresent()) {
            String nombre = nombreInput.get();
            int id = gestorPasajeros.generarIdPasajero();
            Pasajeros nuevoPasajero = new Pasajeros(id, nombre);
            gestorPasajeros.agregarPasajero(nuevoPasajero);
            mostrarMensaje("Éxito", "Pasajero agregado correctamente con ID: " + id);
        }
    }

    @FXML
    private void buscarPasajero(ActionEvent event) {
        if (gestorPasajeros == null) {
            mostrarMensaje("Error", "El gestor de pasajeros no esta inicializado.");
            return;
        }
        Optional<String> idInput = pedirInput("Buscar Pasajero", "Ingrese el ID del pasajero:");
        if (idInput.isPresent()) {
            try {
                int id = Integer.parseInt(idInput.get());
                Pasajeros pasajero = gestorPasajeros.buscarPasajeroPorId(id);
                if (pasajero != null) {
                    mostrarMensaje("Pasajero Encontrado", "ID: " + pasajero.getNumeroID() + "\nNombre: " + pasajero.getNombre());
                } else {
                    mostrarMensaje("No encontrado", "No se encontró ningún pasajero con ese ID.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error", "ID inválido. Debe ser un número.");
            }
        }
    }

    @FXML
    private void actualizarPasajero(ActionEvent event) {
        if (gestorPasajeros == null) {
            mostrarMensaje("Error", "El gestor de pasajeros no está inicializado.");
            return;
        }
        Optional<String> idInput = pedirInput("Actualizar Pasajero", "Ingrese el ID del pasajero a actualizar:");
        if (idInput.isPresent()) {
            try {
                int id = Integer.parseInt(idInput.get());
                Pasajeros pasajero = gestorPasajeros.buscarPasajeroPorId(id);
                if (pasajero != null) {
                    Optional<String> nuevoNombre = pedirInput("Actualizar Nombre", "Ingrese el nuevo nombre del pasajero:");
                    if (nuevoNombre.isPresent()) {
                        gestorPasajeros.actualizarNombrePasajero(id, nuevoNombre.get());
                        mostrarMensaje("Éxito", "Nombre del pasajero actualizado correctamente.");
                    }
                } else {
                    mostrarMensaje("No encontrado", "No se encontró ningún pasajero con ese ID.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error", "ID inválido. Debe ser un número.");
            }
        }
    }

    @FXML
    private void eliminarPasajero(ActionEvent event) {
        if (gestorPasajeros == null) {
            mostrarMensaje("Error", "El gestor de pasajeros no está inicializado.");
            return;
        }
        Optional<String> idInput = pedirInput("Eliminar Pasajero", "Ingrese el ID del pasajero a eliminar:");
        if (idInput.isPresent()) {
            try {
                int id = Integer.parseInt(idInput.get());
                Pasajeros pasajero = gestorPasajeros.buscarPasajeroPorId(id);
                if (pasajero != null) {
                    gestorPasajeros.eliminarPasajero(id);
                    mostrarMensaje("Éxito", "Pasajero eliminado correctamente.");
                } else {
                    mostrarMensaje("No encontrado", "No se encontró ningún pasajero con ese ID.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error", "ID inválido. Debe ser un número.");
            }
        }
    }

    @FXML
    private void mostrarTodosPasajeros(ActionEvent event) {
        if (gestorPasajeros == null) {
            mostrarMensaje("Error", "El gestor de pasajeros no está inicializado.");
            return;
        }
        List<Pasajeros> listaPasajeros = gestorPasajeros.getListaPasajeros();
        if (listaPasajeros == null || listaPasajeros.isEmpty()) {
            mostrarMensaje("Sin pasajeros", "No hay pasajeros registrados.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Lista de Pasajeros:\n\n");
        for (Pasajeros p : listaPasajeros) {
            mensaje.append("ID: ").append(p.getNumeroID())
                    .append("\nNombre: ").append(p.getNombre()).append("\n\n");
        }
        mostrarMensaje("Lista de Pasajeros", mensaje.toString());
    }

    @FXML
    private void mostrarBoletosPasajero(ActionEvent event) {
        if (gestorPasajeros == null) {
            mostrarMensaje("Error", "El gestor de pasajeros no está inicializado.");
            return;
        }
        Optional<String> idInput = pedirInput("Boletos de Pasajero", "Ingrese el ID del pasajero:");
        if (idInput.isPresent()) {
            try {
                int pasajeroId = Integer.parseInt(idInput.get().trim());
                Pasajeros pasajero = gestorPasajeros.buscarPasajeroPorId(pasajeroId);
                if (pasajero != null) {
                    List<Boleto> boletos = pasajero.getBoletos();
                    if (boletos == null || boletos.isEmpty()) {
                        mostrarMensaje("Boletos", "Este pasajero no tiene boletos.");
                    } else {
                        StringBuilder mensaje = new StringBuilder("Boletos de " + pasajero.getNombre() + ":\n\n");
                        for (Boleto b : boletos) {
                            mensaje.append(b.toString()).append("\n\n");
                        }
                        mostrarMensaje("Boletos del Pasajero", mensaje.toString());
                    }
                } else {
                    mostrarMensaje("No encontrado", "No se encontró ningún pasajero con ese ID.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error", "ID inválido. Debe ser un número.");
            }
        }
    }

    @FXML
    private void volverMenuPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuPrincipal.fxml"));
            Parent root = loader.load();

            MenuPrincipalController controller = loader.getController();
            controller.setGestorRutas(gestorRutas);
            controller.setGestorVehiculos(gestorVehiculos);
            controller.setGestorPasajeros(gestorPasajeros);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.setResizable(false);
            stage.show();

            // Cierra la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            mostrarMensaje("Error", "No se pudo cargar el menú principal: " + e.getMessage());
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

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
