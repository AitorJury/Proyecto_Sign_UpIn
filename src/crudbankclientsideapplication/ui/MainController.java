/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudbankclientsideapplication.ui;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author aitor & cynthia
 */
public class MainController {

    private static final Logger LOGGER = Logger.getLogger("crudbankclientside.ui");
    @FXML
    private Button btnLogout;

    public void initStage(Stage stage, Parent root) {
        LOGGER.info("Initializing window");
        stage.setTitle("Main window");
        stage.setResizable(false);

        btnLogout.setOnAction(this::handleBtnLogoutOnAction);
    }

    private void handleBtnLogoutOnAction(ActionEvent event) {
        //Muestra un alert modal de confirmación para cerrar sesión.
        //Si el usuario confirma, la sesión se cierra y se redirige a la ventana de Sign In.
        //Si el usuario cancela, la ventana principal permanece abierta y sin cambios.
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to log out?");
            alert.setTitle("Log Out from the application");
            alert.setHeaderText("Departure confirmation");

            alert.showAndWait().ifPresent(resp -> {
                if (resp == ButtonType.OK) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
                        Parent root = loader.load();
                        Scene scene = ((Node) event.getSource()).getScene();
                        scene.setRoot(root);

                    } catch (IOException e) {
                        e.printStackTrace(); // o muestra un alert de error
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
