package co.edu.poli.paradigmas.tc.proyecto.GUI;

import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuRutasController;
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

    // trae gestores desde MainFX
    public void setGestorRutas(GestorRutas gestorRutas) {
        this.gestorRutas = gestorRutas;
    }

    @FXML
    private void irAMenuRutas(ActionEvent event) { // se crea el metodo de cada gestor para poder acceder a el con el boton (debe iniciarse en el onAction del scene builder del menu principal)
        try { // se debe poner el try-catch para excepciones por errores del fmxl o controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuRutas.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de rutas
            MenuRutasController controller = loader.getController();

            // Compartir el gestor de rutas
            controller.setGestorRutas(gestorRutas);

            // Mostrar nueva ventana para el menu de rutas
            Stage stage = new Stage();
            stage.setTitle("Gestión de Rutas");
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
