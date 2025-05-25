package co.edu.poli.paradigmas.tc.proyecto.GUI;

import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuConductoresController;
import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuPasajerosController;
import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuRutasController;
import co.edu.poli.paradigmas.tc.proyecto.GUI.controller.MenuVehiculosController;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorConductores;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorRutas;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorVehiculos;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorPasajeros;
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
    private GestorPasajeros gestorPasajeros;
    private GestorConductores gestorConductores;

    // trae gestores desde MainFX
    public void setGestorRutas(GestorRutas gestorRutas) {
        this.gestorRutas = gestorRutas;
    }

    // Método para recibir el GestorVehiculos desde MainFX
    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) {
        this.gestorVehiculos = gestorVehiculos;
        System.out.println("setGestorVehiculos llamado en MenuPrincipalController con: " + gestorVehiculos);

    }

    public void setGestorPasajeros(GestorPasajeros gestorPasajeros) { this.gestorPasajeros = gestorPasajeros; }

    public void setGestorConductores(GestorConductores gestorConductores) {
        this.gestorConductores = gestorConductores;
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
            Rcontroller.setGestorPasajeros(gestorPasajeros);
            Rcontroller.setGestorConductores(gestorConductores); // Pasar GestorConductores

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
            controller.setGestorPasajeros(gestorPasajeros);
            controller.setGestorConductores(gestorConductores); // Pasar GestorConductores
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
            Pcontroller.setGestorPasajeros(gestorPasajeros);
            Pcontroller.setGestorRutas(gestorRutas);
            Pcontroller.setGestorVehiculos(gestorVehiculos);
            Pcontroller.setGestorConductores(gestorConductores); // Pasar GestorConductores

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
    private void irMenuConductores(ActionEvent event) { // <-- Nuevo método para abrir el menú de conductores
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuConductores.fxml")); // Asume que tienes este FXML
            Parent root = loader.load();
            MenuConductoresController controller = loader.getController();

            // Pasa todos los gestores que el MenuConductoresController podría necesitar
            controller.setGestorConductores(gestorConductores);
            controller.setGestorVehiculos(gestorVehiculos); // Si vas a asignar/desasignar vehículos
            controller.setGestorRutas(gestorRutas); // Si vas a asignar rutas
            controller.setGestorPasajeros(gestorPasajeros); // Si vas a asignar/desasignar pasajeros

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
}
