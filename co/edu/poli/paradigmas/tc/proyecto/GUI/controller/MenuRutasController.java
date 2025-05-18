package co.edu.poli.paradigmas.tc.proyecto.GUI.controller;

import co.edu.poli.paradigmas.tc.proyecto.GUI.MenuPrincipalController;
import co.edu.poli.paradigmas.tc.proyecto.entities.Rutas;
import co.edu.poli.paradigmas.tc.proyecto.negocio.GestorRutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceDialog;
import java.util.Optional;
import java.util.Random;

public class MenuRutasController {

    private GestorRutas gestorRutas = new GestorRutas();

    public void setGestorRutas(GestorRutas gestorRutas) {
        this.gestorRutas = gestorRutas;
    }

    @FXML
    private void agregarRuta(ActionEvent event) {
        // se crea un mini menu para escojer la letra del destino
        ChoiceDialog<String> destinoDialog = new ChoiceDialog<>("J", "J", "D", "B", "G");
        destinoDialog.setTitle("Agregar Ruta"); // nombre de la ventana para ese menu
        destinoDialog.setHeaderText("Ingrese una letra para elegir el destino:");
        destinoDialog.setContentText("J - Centro de Bogotá\nD - Portal 80\nB - Portal Norte\nG - Portal Sur\n");
        Optional<String> destinoLetra = destinoDialog.showAndWait(); // espera que el usuario ingrese algo
        if (destinoLetra.isEmpty()) return; // Usuario cancelo -> salir

        // Pedir datos de origen distancia y horarios y si el usuario cancela se cierra el metodo
        Optional<String> origen = pedirInput("Agregar Ruta", "Ingrese el origen:");
        if (origen.isEmpty()) return;

        Optional<String> distanciaStr = pedirInput("Agregar Ruta", "Ingrese la distancia (km):");
        if (distanciaStr.isEmpty()) return;

        Optional<String> horariosStr = pedirInput("Agregar Ruta", "Ingrese horarios separados por coma:");
        if (horariosStr.isEmpty()) return;

        // Continuar si todos los datos estan presentes
        String letraDestino = destinoLetra.get().trim().toUpperCase();
        String id = generarID(letraDestino);
        String origenRuta = origen.get().trim();
        String destino = convertirLetraADestino(letraDestino);
        String distanciaTexto = distanciaStr.get().trim();
        String horariosTexto = horariosStr.get().trim();

        if (!origenRuta.isEmpty() && !destino.isEmpty() && !distanciaTexto.isEmpty() && !horariosTexto.isEmpty()) { // si todos los campos estan llenos se ejecuta
            try {
                int distancia = Integer.parseInt(distanciaTexto); // Porque todos los valores que el usuario ingresa por cuadros son siempre texto, asi que toca envolver ese string con un int
                String[] horarios = horariosTexto.split(","); // separa los horarios ingresados con una coma
                Rutas nuevaRuta = new Rutas(id, origenRuta, destino, distancia, horarios);
                gestorRutas.agregarRuta(nuevaRuta); // se llama al metodo del gestor para que se cree la ruta
                mostrarMensaje("Exito", "Ruta agregada correctamente con ID: " + id); // se llama al metodo mostrar mensaje para que salga la ventanita
            } catch (NumberFormatException e) { // si se ingresa una letra en el cuadro de la distancia sale error
                mostrarMensaje("Error", "La distancia debe ser un número válido.");
            }
        } else {
            mostrarMensaje("Error", "Todos los campos deben estar llenos.");
        }
    }

    private String generarID(String letraDestino) { // numeros al azar para las rutas
        Random random = new Random();
        int numero = random.nextInt(90) + 10;
        String IDRuta = letraDestino + numero;
        return IDRuta;
    }

    // convertir la letra en nombre de destino
    private String convertirLetraADestino(String letra) {
        return switch (letra.toUpperCase()) {
            case "J" -> "Centro de Bogotá";
            case "D" -> "Portal 80";
            case "B" -> "Portal Norte";
            case "G" -> "Portal Sur";
            default -> "Desconocido";
        };
    }

    @FXML
    private void eliminarRuta(ActionEvent event) {
        Optional<String> id = pedirInput("Eliminar Ruta", "Ingrese el ID de la ruta a eliminar:");
        if (id.isPresent()) {
            String valor = id.get().trim();
            if (!valor.isEmpty()) {
                if (gestorRutas.eliminarRuta(valor)) {
                    mostrarMensaje("Exito", "Ruta eliminada exitosamente.");
                } else {
                    mostrarMensaje("Error", "No se encontró una ruta con ese ID.");
                }
            } else {
                mostrarMensaje("Error", "El ID no puede estar vacío.");
            }
        } else {
            mostrarMensaje("Cancelado", "Operacion cancelada por el usuario.");
        }
    }

    @FXML
    private void buscarRuta(ActionEvent event) {
        Optional<String> id = pedirInput("Buscar Ruta", "Ingrese el ID de la ruta a buscar:");
        if (id.isPresent()) {
            String valor = id.get().trim();
            if (!valor.isEmpty()) {
                Rutas ruta = gestorRutas.buscarRutaPorID(valor);
                if (ruta != null) {
                    String info = "ID: " + ruta.getNumeroID() + "\n"
                            + "Origen: " + ruta.getOrigen() + "\n"
                            + "Destino: " + ruta.getDestino() + "\n"
                            + "Distancia: " + ruta.getDistancia() + " km\n"
                            + "Horarios: " + String.join(", ", ruta.getHorariosSalida());
                    mostrarMensaje("Ruta encontrada", info);
                } else {
                    mostrarMensaje("Error", "No se encontró la ruta.");
                }
            } else {
                mostrarMensaje("Error", "El ID no puede estar vacío.");
            }
        }
    }

    @FXML
    private void actualizarEstadoRuta(ActionEvent event) {
        Optional<String> idInput = pedirInput("Actualizar Ruta", "Ingrese el ID de la ruta a actualizar:");
        Optional<String> origenInput = pedirInput("Actualizar Ruta", "Nuevo origen:");
        Optional<String> distanciaInput = pedirInput("Actualizar Ruta", "Nueva distancia (número):");
        Optional<String> horariosInput = pedirInput("Actualizar Ruta", "Nuevos horarios separados por coma:");

        if (idInput.isPresent() && origenInput.isPresent() && distanciaInput.isPresent() && horariosInput.isPresent()) {

            String id = idInput.get().trim();
            String origen = origenInput.get().trim();
            String distanciaStr = distanciaInput.get().trim();
            String horariosStr = horariosInput.get().trim();

            if (!id.isEmpty() && !origen.isEmpty() && !distanciaStr.isEmpty() && !horariosStr.isEmpty()) {
                try {
                    int distancia = Integer.parseInt(distanciaStr);
                    String[] horarios = horariosStr.split(",");
                    boolean actualizado = gestorRutas.actualizarRuta(id, origen, distancia, horarios); // ← ahora correcto
                    if (actualizado) {
                        mostrarMensaje("Éxito", "Ruta actualizada correctamente.");
                    } else {
                        mostrarMensaje("Error", "No se encontró la ruta.");
                    }
                } catch (NumberFormatException e) {
                    mostrarMensaje("Error", "La distancia debe ser un número válido.");
                }
            } else {
                mostrarMensaje("Error", "Todos los campos deben estar llenos.");
            }
        }
    }

    @FXML
    private void mostrarRutas(ActionEvent event) {
        String listado = gestorRutas.obtenerListadoRutas(); // llama al metodo de obtener listado para imprimir todas las rutas
        if (listado.isEmpty()) {
            mostrarMensaje("Rutas", "No hay rutas registradas.");
        } else {
            mostrarMensaje("Rutas registradas", listado);
        }
    }

    @FXML
    private void volverMenuPrincipal(ActionEvent event) { // se vincula al boton de volver - el event es la accion de darle click al boton
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/paradigmas/tc/proyecto/GUI/fxml/MenuPrincipal.fxml"));
            Parent root = loader.load(); // se carga la instancia fxml del menu principal

            MenuPrincipalController controller = loader.getController(); // carga el controlador del menu principal fxml
            controller.setGestorRutas(gestorRutas); // pasa el gestorRutas actual al nuevo controlador del menu principal para que los datos queden ahi guardados

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow(); // Obtiene la ventana actual
            stage.setScene(new Scene(root)); // la cambia por la del menu principal
            stage.setTitle("Menú Principal"); // se le pone el titulo
            stage.show(); // se muestra el menu principal
        } catch (Exception e) {
            mostrarMensaje("Error", "No se pudo cargar el menu principal."); // en caso de que algo salga mal al cargar el menu principal, lanza error
            e.printStackTrace();
        }
    }

    // Metodo para pedir input y obligar al usuario a llenar el campo
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
                // Confirmacion para cancelar todo
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Cancelar");
                confirmacion.setHeaderText("¿Estás seguro que deseas cancelar?");
                confirmacion.setContentText("Si confirmas, se cancelará todo el proceso.");

                Optional<ButtonType> opcion = confirmacion.showAndWait();
                if (opcion.isPresent() && opcion.get() == ButtonType.OK) {
                    return Optional.empty(); // Se cancela todo
                }
                // Si elige Cancelar en la confirmación, se repite el input
            }
        }
    }

    // Metodo para mostrar mensajes
    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
