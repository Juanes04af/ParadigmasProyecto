package co.edu.poli.paradigmas.tc.proyecto.gui;

import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorVehiculos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.Optional;

public class MenuVehiculosController {

    private GestorVehiculos gestorVehiculos = new GestorVehiculos(); // Se guarda el gestor vehiculos para usar sus opciones

    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) { // Transferirr instancia del gestor
        this.gestorVehiculos = gestorVehiculos;
    }

    @FXML
    private void agregarVehiculo(ActionEvent event) {
        TextInputDialog dialogPlaca = new TextInputDialog(); // se crea el cuadro para los datos
        dialogPlaca.setTitle("Agregar Vehículo"); // nombre del cuadrito
        dialogPlaca.setHeaderText("Ingrese la placa del vehículo:"); // Establecer el mensaje salga en encabezado en el cuadro
        Optional<String> resultadoPlaca = dialogPlaca.showAndWait(); // espera que el usuario escriba algo
    }

    @FXML
    private void eliminarVehiculo(ActionEvent event) {
    }

    @FXML
    private void buscarVehiculo(ActionEvent event) {
    }

    @FXML
    private void actualizarEstado(ActionEvent event) {
    }

    @FXML
    private void mostrarTodos(ActionEvent event) {
    }

    @FXML
    private void volverMenuPrincipal(ActionEvent event) {
        try {
            // Cargar el archivo FXML del menu principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/gui/MenuPrincipal.fxml"));
            Parent root = loader.load();  // Cargar la jerarquía de nodos del archivo FXML

            MenuPrincipalController controller = loader.getController();  // Obtener el controlador asociado al archivo FXML del menu

            // Transferir la instancia del gestor de vehículos al controlador del menu principal
            controller.setGestorVehiculos(gestorVehiculos);

            // Obtener la ventana (Stage) actual a partir del evento generado por el boton
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Establecer una nueva escena en el Stage usando el contenido del menú principal(root)
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");

            // Mostrar la ventana con la nueva escena
            stage.show();

        } catch (Exception e) {
            e.printStackTrace(); // imprime el error (si es que llega a haberlo) en consola
        }
    }
}
