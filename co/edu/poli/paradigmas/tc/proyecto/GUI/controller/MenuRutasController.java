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

    @FXML // Cada @FXML es un metodo asociado al boton para asi ejecutarlo
    private void agregarRuta(ActionEvent event) {
        // Primero pedir destino
        ChoiceDialog<String> destinoDialog = new ChoiceDialog<>("J", "J", "D", "B", "G"); // coso para menu de eleccion
        destinoDialog.setTitle("Agregar Ruta");
        destinoDialog.setHeaderText("Ingrese una letra para elegir el destino:");
        destinoDialog.setContentText("J - Centro de Bogotá\nD - Portal 80\nB - Portal Norte\nG - Portal Sur\n\nSeleccione la letra correspondiente:");
        Optional<String> destinoLetra = destinoDialog.showAndWait(); // espera que el usuario elija algo

        // Luego pedir origen, distancia y horario como String
        Optional<String> origen = pedirInput("Agregar Ruta", "Ingrese el origen:");
        Optional<String> distanciaStr = pedirInput("Agregar Ruta", "Ingrese la distancia (km):");
        Optional<String> horariosStr = pedirInput("Agregar Ruta", "Ingrese horarios separados por coma:");

        if (origen.isPresent() && destinoLetra.isPresent() && distanciaStr.isPresent() && horariosStr.isPresent()) { // si estan todos los parametros...
            String letraDestino = destinoLetra.get().trim().toUpperCase(); // Se toma la letra que el usuario escojio para el destino y se pone en mayuscula
            String id = generarID(letraDestino); // se llama al metodo de los numero al azar para que le asigne numeros a esa letra
            String origenRuta = origen.get().trim();
            String destino = convertirLetraADestino(letraDestino); // se le asigna a la detra el destino
            String distanciaTexto = distanciaStr.get().trim();
            String horariosTexto = horariosStr.get().trim();

            if (!origenRuta.isEmpty() && !destino.isEmpty() && !distanciaTexto.isEmpty() && !horariosTexto.isEmpty()) {
                try {
                    int distancia = Integer.parseInt(distanciaTexto);
                    String[] horarios = horariosTexto.split(",");
                    Rutas nuevaRuta = new Rutas(id, origenRuta, destino, distancia, horarios);
                    gestorRutas.agregarRuta(nuevaRuta);
                    mostrarMensaje("Éxito", "Ruta agregada correctamente con ID: " + id);
                } catch (NumberFormatException e) {
                    mostrarMensaje("Error", "La distancia debe ser un número válido.");
                }
            } else {
                mostrarMensaje("Error", "Todos los campos deben estar llenos.");
            }
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
        id.ifPresent(valor -> {
            if (!valor.trim().isEmpty()) {
                Rutas ruta = gestorRutas.buscarRutaPorID(valor.trim());
                if (ruta != null) {
                    String info = "ID: " + ruta.getNumeroID() + "\n"
                            + "Origen: " + ruta.getOrigen() + "\n"
                            + "Destino: " + ruta.getDestino() + "\n"
                            + "Distancia: " + ruta.getDistancia() + "\n"
                            + "Horarios: " + String.join(", ", ruta.getHorariosSalida());
                    mostrarMensaje("Ruta encontrada", info);
                } else {
                    mostrarMensaje("Error", "No se encontró la ruta.");
                }
            } else {
                mostrarMensaje("Error", "El ID no puede estar vacío.");
            }
        });
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
            mostrarMensaje("Error", "No se pudo cargar el menú principal."); // en caso de que algo salga mal al cargar el menu principal, lanza error
            e.printStackTrace();
        }
    }

    // Metodo para pedir input
    private Optional<String> pedirInput(String titulo, String mensaje) {
        TextInputDialog dialog = new TextInputDialog(); // TextInputDialog crea una ventana emergente con un campo de texto
        dialog.setTitle(titulo); // se le asigna el titulo del metodo correspondiente a la ventana
        dialog.setHeaderText(mensaje); // mensaje de instruccion
        return dialog.showAndWait(); // Muestra el cuadro y espera a que el usuario responda algo
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
