package co.edu.poli.paradigmas.tc.proyecto.GUI.controller;

import co.edu.poli.paradigmas.tc.proyecto.GUI.MenuPrincipalController;
import co.edu.poli.paradigmas.tc.proyecto.entities.Boleto;
import co.edu.poli.paradigmas.tc.proyecto.entities.Pasajeros;
import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas; // Necesitarás importar Rutas
import co.edu.poli.paradigmas.tc.proyecto.negocio.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MenuBoletoController {

    private GestorConductores gestorConductores;
    private GestorVehiculos gestorVehiculos;
    private GestorRutas gestorRutas;
    private GestorPasajeros gestorPasajeros;
    private GestorTaller gestorTaller;
    private GestorBoletos gestorBoletos;

    // Métodos para inyectar los gestores
    public void setGestorBoletos(GestorBoletos gestorBoletos) {
        this.gestorBoletos = gestorBoletos;
        if (this.gestorBoletos == null) {
            System.err.println("Alerta: GestorBoletos no se inicializo correctamente.");
        }
    }
    public void setGestorPasajeros(GestorPasajeros gestorPasajeros) {
        this.gestorPasajeros = gestorPasajeros;
    }
    public void setGestorRutas(GestorRutas gestorRutas) {
        this.gestorRutas = gestorRutas;
    }
    public void setGestorConductores(GestorConductores gestorConductores) {
        this.gestorConductores = gestorConductores;
        if (this.gestorConductores == null) {
            System.err.println("Alerta: GestorConductores no se inicializó correctamente.");
        }
    }
    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) {
        this.gestorVehiculos = gestorVehiculos;
    }
    public void setGestorTaller(GestorTaller gestorTaller) {
        this.gestorTaller = gestorTaller;
    }
    // --- Métodos FXML (acciones de botones) ---

    @FXML
    private void crearBoleto(ActionEvent event) {
        if (gestorBoletos == null || gestorPasajeros == null || gestorRutas == null) {
            mostrarMensaje("Error", "Los gestores no estan inicializados correctamente.");
            return;
        }

        // 1. Pedir ID del pasajero
        Optional<String> pasajeroIdInput = pedirInput("Crear Boleto", "Ingrese el ID del pasajero:");
        if (!pasajeroIdInput.isPresent()) return;
        int pasajeroId;
        try {
            pasajeroId = Integer.parseInt(pasajeroIdInput.get());
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID de pasajero inválido. Debe ser un numero.");
            return;
        }

        Pasajeros pasajero = gestorPasajeros.buscarPasajeroPorId(pasajeroId);
        if (pasajero == null) {
            mostrarMensaje("Error", "No se encontró ningún pasajero con el ID: " + pasajeroId);
            return;
        }

        // 2. Pedir ID de la ruta
        Optional<String> rutaIdInput = pedirInput("Crear Boleto", "Ingrese el ID de la ruta (verifique rutas disponibles):");
        if (!rutaIdInput.isPresent()) return;
        String rutaId;
        try {
            rutaId = rutaIdInput.get().toUpperCase();
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "ID de ruta inválido.");
            return;
        }

        Rutas rutaSeleccionada = gestorRutas.buscarRutaPorID(rutaId);
        if (rutaSeleccionada == null) {
            mostrarMensaje("Error", "No se encontró ninguna ruta con el ID: " + rutaId + ". Revise las rutas disponibles.");
            return;
        }

        // Generar un ID de boleto (puedes tener un método similar a generarIdPasajero en GestorBoletos)
        int idBoleto = gestorBoletos.generarIdBoleto(); // Asume que tienes este método en GestorBoletos

        Boleto nuevoBoleto = new Boleto(true, pasajero.getNombre(), idBoleto, rutaSeleccionada, GestorBoletos.getPrecioFijo());
        gestorBoletos.crearBoleto(nuevoBoleto);
        pasajero.agregarBoleto(nuevoBoleto); // Asumiendo que el pasajero guarda una lista de sus boletos

        mostrarMensaje("Éxito", "Boleto creado correctamente con ID: " + idBoleto + " para " + pasajero.getNombre() + " en la ruta " + rutaSeleccionada.getRutaString());
    }

    @FXML
    private void buscarBoleto(ActionEvent event) {
        if (gestorBoletos == null) {
            mostrarMensaje("Error", "El gestor de boletos no esta inicializado.");
            return;
        }
        Optional<String> idInput = pedirInput("Buscar Boleto", "Ingrese el ID del boleto:");
        if (idInput.isPresent()) {
            try {
                int id = Integer.parseInt(idInput.get());
                Boleto boleto = gestorBoletos.buscarBoletoPorId(id);
                if (boleto != null) {
                    mostrarMensaje("Boleto Encontrado", boleto.toString());
                } else {
                    mostrarMensaje("No encontrado", "No se encontro ningun boleto con ese ID.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error", "ID inválido. Debe ser un numero.");
            }
        }
    }

    @FXML
    private void actualizarBoleto(ActionEvent event) {
        if (gestorBoletos == null || gestorRutas == null) {
            mostrarMensaje("Error", "Los gestores no estan inicializados correctamente.");
            return;
        }
        Optional<String> idInput = pedirInput("Actualizar Boleto", "Ingrese el ID del boleto a actualizar:");
        if (idInput.isPresent()) {
            try {
                int id = Integer.parseInt(idInput.get());
                Boleto boletoExistente = gestorBoletos.buscarBoletoPorId(id);
                if (boletoExistente != null) {
                    Optional<String> nuevoNombre = pedirInput("Actualizar Nombre", "Ingrese el nuevo nombre del pasajero (o el mismo):");
                    if (!nuevoNombre.isPresent()) return;

                    Optional<String> rutaIdInput = pedirInput("Actualizar Ruta", "Ingrese el nuevo ID de la ruta (o el mismo):");
                    if (!rutaIdInput.isPresent()) return;
                    String nuevaRutaId;
                    try {
                        nuevaRutaId = rutaIdInput.get();
                    } catch (NumberFormatException e) {
                        mostrarMensaje("Error", "ID de ruta inválido.");
                        return;
                    }
                    Rutas nuevaRuta = gestorRutas.buscarRutaPorID(nuevaRutaId);
                    if (nuevaRuta == null) {
                        mostrarMensaje("Error", "Nueva ruta no encontrada.");
                        return;
                    }

                    // Por simplicidad, el precio y compraBoletos se podrían pedir también o mantener fijos
                    gestorBoletos.actualizarBoleto(id, nuevoNombre.get(), true, nuevaRuta, GestorBoletos.getPrecioFijo());
                    mostrarMensaje("Éxito", "Boleto actualizado correctamente.");
                } else {
                    mostrarMensaje("No encontrado", "No se encontró ningún boleto con ese ID.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error", "ID inválido. Debe ser un numero.");
            }
        }
    }

    @FXML
    private void eliminarBoleto(ActionEvent event) {
        if (gestorBoletos == null) {
            mostrarMensaje("Error", "El gestor de boletos no esta inicializado.");
            return;
        }
        Optional<String> idInput = pedirInput("Eliminar Boleto", "Ingrese el ID del boleto a eliminar:");
        if (idInput.isPresent()) {
            try {
                int id = Integer.parseInt(idInput.get());
                Boleto boleto = gestorBoletos.buscarBoletoPorId(id);
                if (boleto != null) {
                    gestorBoletos.eliminarBoleto(id);
                    mostrarMensaje("Éxito", "Boleto eliminado correctamente.");
                } else {
                    mostrarMensaje("No encontrado", "No se encontro ningun boleto con ese ID.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error", "ID inválido. Debe ser un numero.");
            }
        }
    }

    @FXML
    private void mostrarTodosBoletos(ActionEvent event) {
        if (gestorBoletos == null) {
            mostrarMensaje("Error", "El gestor de boletos no esta inicializado.");
            return;
        }
        List<Boleto> listaBoletos = gestorBoletos.getBoletos(); // Asume que tienes un getter para la lista de boletos en GestorBoletos
        if (listaBoletos == null || listaBoletos.isEmpty()) {
            mostrarMensaje("Sin boletos", "No hay boletos registrados.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Lista de Boletos:\n\n");
        for (Boleto b : listaBoletos) {
            mensaje.append(b.toString()).append("\n\n");
        }
        mostrarMensaje("Lista de Boletos", mensaje.toString());
    }

    @FXML
    private void volverMenuPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuPrincipal.fxml"));
            Parent root = loader.load();

            MenuPrincipalController controller = loader.getController();
            // Pasa todos los gestores de vuelta al MenuPrincipalController
            controller.setGestorRutas(gestorRutas);
            controller.setGestorVehiculos(gestorVehiculos);
            controller.setGestorPasajeros(gestorPasajeros);
            controller.setGestorConductores(gestorConductores);
            controller.setGestorTaller(gestorTaller);
            controller.setGestorBoletos(gestorBoletos);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal");
            stage.setResizable(false);
            stage.show();

            // Cierra la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            mostrarMensaje("Error", "No se pudo cargar el menu principal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Métodos auxiliares (copiados de MenuPasajerosController)
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
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Cancelar");
                confirmacion.setHeaderText("¿Estás seguro que deseas cancelar?");
                confirmacion.setContentText("Si confirmas, se cancelará todo el proceso.");

                Optional<ButtonType> opcion = confirmacion.showAndWait();
                if (opcion.isPresent() && opcion.get() == ButtonType.OK) {
                    return Optional.empty();
                }
            }
        }
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}