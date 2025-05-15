package co.edu.poli.paradigmas.tc.proyecto.gui;

import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorVehiculos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class MenuPrincipalController {

    private GestorVehiculos gestorVehiculos;

    // trae gestorVehiculos desde MainFX
    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) {
        this.gestorVehiculos = gestorVehiculos;
    }

    @FXML
    private void gestionarVehiculos(ActionEvent event) {
        try {
            // Cargar el archivo FXML del menú de vehículos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/gui/MenuVehiculos.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la vista
            MenuVehiculosController controller = loader.getController();

            // se ponen (comparten) los datos del gestor vehiculo
            controller.setGestorVehiculos(gestorVehiculos);

            // Crear y mostrar una nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Gestión de Vehículos");
            stage.setScene(new Scene(root));
            stage.show();

            // cerrar la ventana de menu principal
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
