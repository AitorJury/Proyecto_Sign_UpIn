/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudbankclientsideapplication.ui;

import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        //Asociar manejadores de propierties
        txtEmail.focusedProperty().addListener(this::handeltxtEmailFocusChange);
        txtEmail.textProperty().addListener(this::handeltxtEmailTextChange);

        txtPassword.focusedProperty().addListener(this::handeltxtPasswordFocusChange);
        txtPassword.textProperty().addListener(this::handeltxtPasswordTextChange);

        //boton exit
        //Pedir confirmación al usuario para cerrar la aplicación.
        //Si confirma, se cerrará la aplicación.
        //Si no confirma, la ventana permanecerá abierta.
        //Si ocurre un error al cerrar la página se lanzará una excepción y se mostrará que no se puede cerrar la aplicación.
        //Mostrar la ventana
        stage.show();
         btnSignIn.setDisable(true);
        //Preparar el formulario de entrada con los campos de correo y contraseña vacíos.
        //Enfocar automáticamente el campo de correo al abrir la ventana.
        //Cargar los estilos visuales y el icono de la aplicación.
        //Si se produce un error durante la carga de la interfaz, se lanzará una excepción y se mostrará un mensaje con la excepción. (“No se ha podido cargar la página”).
        //Si se produce error en el servidor mostrar excepción y se mostrará un mensaje con la excepción.(“No se ha podido conectar con el servidor”).
        //Validar que el texto tenga formato de correo electrónico (con “@” y dominio).
        //Si no tiene formato válido se lanzará una excepción y se mostrará  un mensaje de alerta con un mensaje de la excepción. (“El formato no es válido debe de  tener una @ y un dominio”).
        //Si el campo queda vacío se lanzará una excepción y se mostrará un mensaje con la excepción.(“El correo es obligatorio”).
        //Si contiene espacios o caracteres especiales, mostrar “Correo con formato incorrecto”.
    }

    private void handleBtnExitOnAction(ActionEvent event) {

    }

    private void handleBtnSignInOnAction(ActionEvent event) {

    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void handeltxtEmailTextChange(ObservableValue observable, String oldValue, String newValue) {
        if (this.txtEmail.getText().isEmpty()) {
            txtEmail.setStyle(" -fx-border-color: red");
            
        } else {
            btnSignIn.setDisable(false);
        }
        
    }

    private void handeltxtPasswordTextChange(ObservableValue observable, String oldValue, String newValue) {
        if (this.txtPassword.getText().isEmpty()) {
            
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

    private void handeltxtPasswordFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {

    }

}
