package co.edu.poli.paradigmas.tc.proyecto.GUI;

import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuRutasController;
import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuVehiculosController;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorRutas;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorVehiculos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class MenuPrincipalController {

    private GestorRutas gestorRutas;
    private GestorVehiculos gestorVehiculos; // Declarar GestorVehiculos

    // trae gestores desde MainFX
    public void setGestorRutas(GestorRutas gestorRutas) {
        this.gestorRutas = gestorRutas;
    }

    // Método para recibir el GestorVehiculos desde MainFX
    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) {
        this.gestorVehiculos = gestorVehiculos;
        System.out.println("setGestorVehiculos llamado en MenuPrincipalController con: " + gestorVehiculos);

    }

    @FXML
    private void irAMenuRutas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuRutas.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de rutas
            MenuRutasController Rcontroller = loader.getController();
            System.out.println("MenuRutasController instance created: " + Rcontroller); // Debug

            // Compartir el gestor de rutas
            System.out.println("GestorRutas in MenuPrincipal: " + gestorRutas); // Debug
            Rcontroller.setGestorRutas(gestorRutas);
            Rcontroller.setGestorVehiculos(gestorVehiculos);

            // Mostrar nueva ventana para el menu de rutas
            Stage stage = new Stage();
            stage.setTitle("Gestión de Rutas");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

            // Cerrar ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarMenuVehiculos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuVehiculos.fxml"));
            Parent root = loader.load();
            MenuVehiculosController controller = loader.getController();
            controller.setGestorRutas(gestorRutas); // Pasar GestorRutas (si es necesario)
            controller.setGestorVehiculos(gestorVehiculos); // Pasar GestorVehiculos
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú de Vehículos");
            stage.setResizable(false);
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
