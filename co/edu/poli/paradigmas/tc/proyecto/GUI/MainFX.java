package co.edu.poli.paradigmas.tc.proyecto.GUI;

import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorVehiculos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    private GestorVehiculos gestorVehiculos; // unica instancia compartida para el gestor vehiculos

    @Override
    public void start(Stage primaryStage) {
        try {
            // Inicializar los gestores para que se compartan los datos
            gestorVehiculos = new GestorVehiculos();

            // Cargar FXML principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/MenuPrincipal.fxml"));
            Parent root = loader.load();

            // poner el gestor en el controlador principal si lo necesita
            MenuPrincipalController controller = loader.getController();
            controller.setGestorVehiculos(gestorVehiculos);

            primaryStage.setTitle("Menu Principal");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
