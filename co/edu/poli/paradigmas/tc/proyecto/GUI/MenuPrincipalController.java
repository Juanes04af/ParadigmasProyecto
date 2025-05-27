package co.edu.poli.paradigmas.tc.proyecto.GUI;

import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuConductoresController;
import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuPasajerosController;
import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuRutasController;
import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuVehiculosController;
import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuTallerController;
import co.edu.poli.paradigmas.tc.proyecto.negocio.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class MenuPrincipalController {

    private GestorRutas gestorRutas;
    private GestorVehiculos gestorVehiculos;
    private GestorPasajeros gestorPasajeros;
    private GestorConductores gestorConductores;
    private GestorTaller gestorTaller;


    public void setGestorRutas(GestorRutas gestorRutas) {
        this.gestorRutas = gestorRutas;
    }


    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) {
        this.gestorVehiculos = gestorVehiculos;
        System.out.println("setGestorVehiculos llamado en MenuPrincipalController con: " + gestorVehiculos);

    }

    public void setGestorPasajeros(GestorPasajeros gestorPasajeros) { this.gestorPasajeros = gestorPasajeros; }

    public void setGestorConductores(GestorConductores gestorConductores) {
        this.gestorConductores = gestorConductores;
    }

    public void setGestorTaller(GestorTaller gestorTaller) {
        this.gestorTaller = gestorTaller;
    }

    @FXML
    private void irAMenuRutas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuRutas.fxml"));
            Parent root = loader.load();


            MenuRutasController Rcontroller = loader.getController();
            System.out.println("MenuRutasController instance created: " + Rcontroller);


            System.out.println("GestorRutas in MenuPrincipal: " + gestorRutas);
            Rcontroller.setGestorRutas(gestorRutas);
            Rcontroller.setGestorVehiculos(gestorVehiculos);
            Rcontroller.setGestorPasajeros(gestorPasajeros);
            Rcontroller.setGestorConductores(gestorConductores);
            Rcontroller.setGestorTaller(gestorTaller);

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
            controller.setGestorTaller(gestorTaller);
            controller.setGestorConductores(gestorConductores);
            controller.setGestorVehiculos(gestorVehiculos);
            controller.setGestorRutas(gestorRutas);
            controller.setGestorPasajeros(gestorPasajeros);
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

    @FXML
    private void irAMenuPasajero(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuPasajero.fxml"));
            Parent root = loader.load();

            MenuPasajerosController Pcontroller = loader.getController();
            Pcontroller.setGestorTaller(gestorTaller);
            Pcontroller.setGestorConductores(gestorConductores);
            Pcontroller.setGestorVehiculos(gestorVehiculos);
            Pcontroller.setGestorRutas(gestorRutas);
            Pcontroller.setGestorPasajeros(gestorPasajeros);

            // Mostrar nueva ventana para el menu de rutas
            Stage stage = new Stage();
            stage.setTitle("Gestión de Pasajeros");
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
    private void irMenuConductores(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuConductores.fxml"));
            Parent root = loader.load();
            MenuConductoresController controller = loader.getController();


            controller.setGestorTaller(gestorTaller);
            controller.setGestorConductores(gestorConductores);
            controller.setGestorVehiculos(gestorVehiculos);
            controller.setGestorRutas(gestorRutas);
            controller.setGestorPasajeros(gestorPasajeros);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Conductores");
            stage.show();

            // Cerrar ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            System.err.println("Error al cargar el menú de conductores: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void irMenuTaller(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuTaller.fxml"));
            Parent root = loader.load();
            MenuTallerController controller = loader.getController();


            controller.setGestorTaller(gestorTaller);
            controller.setGestorConductores(gestorConductores);
            controller.setGestorVehiculos(gestorVehiculos);
            controller.setGestorRutas(gestorRutas);
            controller.setGestorPasajeros(gestorPasajeros);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Taller de Mantenimiento");
            stage.show();

            // Cerrar ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            System.err.println("Error al cargar el menú de taller: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
