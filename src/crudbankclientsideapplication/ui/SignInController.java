/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudbankclientsideapplication.ui;

import crudbankclientapplication.logic.CustomerRESTClient;
import crudbankclientsideapplication.model.Customer;

import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.ProcessingException;

/**
 *
 * @author cynthia
 */
public class SignInController {

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label lblError;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnSignIn;
    @FXML
    private Hyperlink linkSignUp;
    private static final Logger LOGGER = Logger.getLogger("crudbankclientside.ui");

    public void initStage(Stage stage, Parent root) {
        LOGGER.info("Initializing window");
        //Establecer el título de la ventana
        stage.setTitle("Sign in");
        //la ventana no debe ser redimensaionable
        stage.setResizable(false);
        //Asociar eventos a manejadores
        btnExit.setOnAction(this::handleBtnExitOnAction);
        btnSignIn.setOnAction(this::handleBtnSignInOnAction);
        linkSignUp.setOnAction(this::handleLinkOnAction);

        //Asociar manejadores de propierties
        txtEmail.focusedProperty().addListener(this::handeltxtEmailFocusChange);
        txtEmail.textProperty().addListener(this::handeltxtEmailTextChange);
        txtPassword.textProperty().addListener(this::handeltxtPasswordTextChange);

        stage.show();
        btnSignIn.setDisable(true);

        //Preparar el formulario de entrada con los campos de correo y contraseña vacíos.
        //La ventana es no modal.
        //Enfocar automáticamente el campo de correo al abrir la ventana.
    }

    private void handleLabelError(String message) {
        lblError.setText(message);
    }

    private void handleAlertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    //
    private void handleLinkOnAction(ActionEvent event) {
        try {
            //Cerrar la ventana actual.
            //Abrir la ventana de registro de nuevo usuario.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            Parent root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        } catch (Exception e) {

        }
    }

    private void handleBtnExitOnAction(ActionEvent event) {
        //boton exit
        try {
            // Mostrar alert modal de confirmación para salir de la aplicación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit?");
            alert.setTitle("Exit the application");
            alert.setHeaderText("Departure confirmation");
            alert.showAndWait().ifPresent(resp -> {
                // Si confirma, cerrar la aplicación
                if (resp == ButtonType.OK) {
                    Stage stage = (Stage) btnExit.getScene().getWindow();
                    stage.close();
                }//Si no confirma, la ventana permanecerá abierta.
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handleBtnSignInOnAction(ActionEvent event) {
        try {
            String text = txtEmail.getText().trim();
            if (!text.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                throw new Exception("The email must have @ an email and a domain");

                //200 bien
            } else {
                handleLabelError("Checking in the database");
            }
            String email = txtEmail.getText().trim();
            String password = txtPassword.getText().trim();
            CustomerRESTClient client = new CustomerRESTClient();
            Customer customer = client.findCustomerByEmailPassword_XML(Customer.class, email, password);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        } catch (NotAuthorizedException e) {
            handleAlertError("The username or password does not match.");
        } catch (InternalServerErrorException e) {
            //LOGGER.log
            handleAlertError("No conecta al servidor");
        } catch (Exception e) {
            handleLabelError(e.getMessage());
        } finally {
            enableButtonSignIn();
        }
    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void handeltxtEmailTextChange(ObservableValue observable, String oldValue, String newValue) {
        try {
            if (this.txtEmail.getText().isEmpty()) {
                txtEmail.setStyle(" -fx-border-color: red");
                throw new Exception("The email field must not be left empty.");
            } else {
                txtEmail.setStyle("-fx-border-color: white");

            }

        } catch (Exception e) {
            //lanzar excepcion de error
            handleLabelError(e.getMessage());
        } finally {
            enableButtonSignIn();
        }

    }

    private void handeltxtPasswordTextChange(ObservableValue observable, String oldValue, String newValue) {
        try {
            if (this.txtPassword.getText().isEmpty()) {
                txtPassword.setStyle(" -fx-border-color: red");
                throw new Exception("The password field must not be left empty.");
            } else {
                txtPassword.setStyle("-fx-border-color: white");
            }
            enableButtonSignIn();
        } catch (Exception e) {
            //lanzar excepcion de error
            handleLabelError(e.getMessage());
        }
    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void handeltxtEmailFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!oldValue) {
            //oldValue false newValue true
        } else {

        }
    }

    private void enableButtonSignIn() {
        boolean validEmail = validEmail();
        boolean validPassword = validPassword();
        btnSignIn.setDisable(!(validEmail && validPassword));

    }

    private boolean validEmail() {
        boolean isValidEmail = txtEmail.getText().isEmpty();
        return !isValidEmail;
    }

    private boolean validPassword() {
        boolean isValidPsw = txtPassword.getText().isEmpty();

        return !isValidPsw;
    }

}
