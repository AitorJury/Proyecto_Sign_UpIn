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
        initialiceButtonSignIn();
        // Establecer el título de la ventana a “Sign In”.
        //La ventana no debe ser redimensionable.
        //Preparar el formulario de entrada con los campos de correo y contraseña vacíos.
        //La ventana es no modal.
        //Enfocar automáticamente el campo de correo al abrir la ventana.

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
    
    //Configuración del botón para que esté deshabilitado al entrar en la aplicación
    //hasta que estén rellenados los 2 
    
    //Este es el método que establece que al iniciar la aplicación el botón
    //aparezca deshabilitado
    private void initialiceButtonSignIn(){
          btnSignIn.setDisable(true);
          txtEmail.textProperty().addListener(this::textChange);
          txtPassword.textProperty().addListener(this::textChange);
          txtPassword.textProperty().addListener(this::textChange);
          
    }
    
    //Este botón hace que automáticamente cuando en el texto se escriba algo
    //le envie a la funcion initialButtonSignIn() el cambio que ha habido.
     private void textChange(ObservableValue observable, String oldValue, String newValue) {
        boolean enable = txtPassword.getText().trim().isEmpty() || !txtEmail.getText().trim().isEmpty();
        btnSignIn.setDisable(enable);
        
        
    }
     
      
    
    private void handeltxtEmailTextChange(ObservableValue observable, String oldValue, String newValue) {
        if (this.txtEmail.getText().isEmpty()) {
            txtEmail.setStyle(" -fx-border-color: red");

        } else {

        }

    }

    private void handeltxtPasswordTextChange(ObservableValue observable, String oldValue, String newValue) {
        if (this.txtPassword.getText().isEmpty()) {

        } else {

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
