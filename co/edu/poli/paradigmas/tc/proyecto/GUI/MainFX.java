package co.edu.poli.paradigmas.tc.proyecto.GUI;

import co.edu.poli.paradigmas.tc.proyecto.negocio.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    private GestorRutas gestorRutas; // unica instancia compartida para el gestor rutas
    private  GestorConductores gestorConductores;
    private GestorVehiculos gestorVehiculos;
    private GestorPasajeros gestorPasajeros;

    @Override
    public void start(Stage primaryStage) {
        try {
            gestorRutas = new GestorRutas();
            gestorConductores = new GestorConductores();
            gestorVehiculos = new GestorVehiculos();
            gestorPasajeros= new GestorPasajeros();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuPrincipal.fxml"));
            Parent root = loader.load();

            MenuPrincipalController mainController = loader.getController();
            System.out.println("GestorVehiculos en MainFX antes de pasar: " + gestorVehiculos);
            mainController.setGestorRutas(gestorRutas);
            mainController.setGestorVehiculos(gestorVehiculos); // Pasar GestorVehiculos
            mainController.setGestorPasajeros(gestorPasajeros);

            primaryStage.setTitle("Menú Principal");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
