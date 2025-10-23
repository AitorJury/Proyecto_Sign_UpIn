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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author aitor
 */
public class SignUpController {
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtMiddleInitial;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtRepeatPassword;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtZip;
    @FXML
    private Label errFirstName;
    @FXML
    private Label errLastName;
    @FXML
    private Label errMiddleInitial;
    @FXML
    private Label errEmail;
    @FXML
    private Label errPassword;
    @FXML
    private Label errRepeatPassword;
    @FXML
    private Label errPhone;
    @FXML
    private Label errCityState;
    @FXML
    private Label errStreetZip;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnCreateAccount;
    @FXML
    private Hyperlink linkSignIn;
    
    private static final Logger LOGGER = 
            Logger.getLogger("crudbankclientsideapplication.ui");

    public void init(Stage stage, Parent root) {
        LOGGER.info("Initializing window.");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        // Establecer el título de la ventana
        stage.setTitle("Sign Up");
        // la ventana no debe ser redimensionable
        stage.setResizable(false);
        
        
        btnExit.setDisable(false);
        btnCreateAccount.setDisable(false);
        /*
        Establecer el título de la ventana.
        
        Todos los labels de error (errFirstName, errLastName, errMiddleInitial, 
        errEmail, errPassword, errRepeatPassword, errPhone, errCityState, 
        errStreetZip) se vacían.
        
        El foco se coloca en txtFirstName.
        
        Los botones btnExit y btnCreateAccount estarán habilitados.
        
        El botón btnCreateAccount se deshabilita en caso de que un campo no 
        esté correctamente validado.
        
        Los campos de texto y password están habilitados para la entrada de 
        datos.
        */
        // Asociar eventos a manejadores
        btnExit.setOnAction(this::handleBtnExitOnAction);
        btnCreateAccount.setOnAction(this::handleBtnCreateAccountOnAction);
        
        txtFirstName.textProperty().addListener(this::handleTxtFirstNameTextChange);
        txtFirstName.focusedProperty().addListener(this::handleTxtFirstNameFocusChange);
        
        
        // Mostrar la ventana
        stage.show();
    }
    /**
     * 
     * @param event 
     */
    private void handleBtnExitOnAction(ActionEvent event) {
        
    }
    /**
     * 
     * @param event 
     */
    private void handleBtnCreateAccountOnAction(ActionEvent event) {
        
    }
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    private void handleTxtFirstNameTextChange(ObservableValue observable, String oldValue, String newValue) {
        
    }
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    private void handleTxtFirstNameFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if(!newValue) {
            
        } else {
            
        }
    }
}
