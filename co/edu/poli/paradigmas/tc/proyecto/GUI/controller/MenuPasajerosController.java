package co.edu.poli.paradigmas.tc.proyecto.GUI.controller;

import co.edu.poli.paradigmas.tc.proyecto.GUI.MenuPrincipalController;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorPasajeros;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class MenuPasajerosController {

    private GestorPasajeros gestorPasajeros;

    public void setGestorPasajeros(GestorPasajeros gestorPasajeros) {
        this.gestorPasajeros = gestorPasajeros;
        System.out.println("GestorPasajeros recibido en MenuPasajeroController: " + gestorPasajeros);
    }

    @FXML
    private void volverMenuPrincipal(ActionEvent event) { // se vincula al boton de volver - el event es la accion de darle click al boton
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuPrincipal.fxml"));
            Parent root = loader.load(); // se carga la instancia fxml del menu principal

            MenuPrincipalController controller = loader.getController(); // carga el controlador del menu principal fxml
            controller.setGestorPasajeros(gestorPasajeros); // pasa el gestorRutas actual al nuevo controlador del menu principal para que los datos queden ahi guardados

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow(); // Obtiene la ventana actual
            stage.setScene(new Scene(root)); // la cambia por la del menu principal
            stage.setTitle("Menú Principal"); // se le pone el titulo
            stage.show(); // se muestra el menu principal
        } catch (Exception e) {
            mostrarMensaje("Error", "No se pudo cargar el menu principal."); // en caso de que algo salga mal al cargar el menu principal, lanza error
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
}