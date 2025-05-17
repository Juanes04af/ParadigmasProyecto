package co.edu.poli.paradigmas.tc.proyecto.GUI;

import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorRutas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    private GestorRutas gestorRutas; // unica instancia compartida para el gestor rutas

    @Override
    public void start(Stage primaryStage) {
        try {
            // Inicializar los gestores para que se compartan y guarden los datos en caso de que se quierzn usar en otro gestor
            gestorRutas = new GestorRutas();

            // Cargar FXML del menu principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuPrincipal.fxml"));
            Parent root = loader.load();

            // poner el gestor en el controlador principal si lo necesita
            MenuPrincipalController controller = loader.getController();
            controller.setGestorRutas(gestorRutas); // le pasa al controlador una instancia de gestor rutas

            primaryStage.setTitle("Menu Principal"); // titulo de la ventana
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
