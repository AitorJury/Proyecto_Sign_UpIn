/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudbankclientsideapplication.ui;

import crudbankclientsideapplication.logic.CustomerRESTClient;
import crudbankclientsideapplication.model.Customer;
import crudbankclientsideapplication.ui.SignUpController;

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
    private Stage stage;

    public void initStage(Stage stage, Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
         
        this.stage = stage;
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
        txtPassword.focusedProperty().addListener(this::handletxtPasswordFocusChange);

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
        ButtonType okay = new ButtonType("Okay");
        Alert alert = new Alert(Alert.AlertType.ERROR, "", okay);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();

    }

    private void handleLinkOnAction(ActionEvent event) {
        try {
            //Cerrar la ventana actual.
            //Abrir la ventana de registro de nuevo usuario.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            Parent root = loader.load();
            
            SignUpController controller = loader.getController();
            controller.init(this.stage, root);
            // Obtener el controlador correcto

        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
    }

    private void handleBtnExitOnAction(ActionEvent event) {
        //boton exit
        try {
            ButtonType yes = new ButtonType("Yes");
            ButtonType no = new ButtonType("No");
            // Mostrar alert modal de confirmación para salir de la aplicación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit?", yes, no);
            alert.setTitle("Exit the application");
            alert.setHeaderText("Departure confirmation");
            alert.showAndWait().ifPresent(resp -> {
                // Si confirma, cerrar la aplicación
                if (resp == yes) {
                    Stage stage = (Stage) btnExit.getScene().getWindow();
                    stage.close();
                }//Si no confirma, la ventana permanecerá abierta.
            });
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            e.printStackTrace();
        }

    }

    private void handleBtnSignInOnAction(ActionEvent event) {
        try {
            //Validar si el correo tiene formato del correo (@ y un dominio)
            String text = txtEmail.getText().trim();
            if (!text.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                //Si no coincide, se lanzará una excepción con el label de error y se mostrará un mensaje (“El correo o la contraseña no son correctos”)
                throw new Exception("The email must have @ an email and a domain");

            } else {
                handleLabelError("Checking in the database");
            }
            //Conectará con la base de datos para validar la contraseña y el correo.
            String email = txtEmail.getText().trim();
            String password = txtPassword.getText().trim();
            CustomerRESTClient client = new CustomerRESTClient();
            //Verificar que la contraseña coincida con la del usuario registrado. 
            //Verificar que el correo y la contraseña existe en la base de datos.
            Customer customer = client.findCustomerByEmailPassword_XML(Customer.class, email, password);

            //Si todo es correcto se abrirá la página “Main” y se cerrará la actual.
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = loader.load();
            //Cargamos controlador
            MainController controller = loader.getController();
            controller.initStage(this.stage, root);

        } catch (NotAuthorizedException e) {
            LOGGER.warning(e.getMessage());
            //Si no coincide, se lanzará una excepción con el label de error y 
            //se mostrará un mensaje (“El correo o la contraseña no son correctos”)
            handleAlertError("The username or password does not match.");
        } catch (InternalServerErrorException e) {
            LOGGER.warning(e.getMessage());
            //Si el servidor no responde se lanzará una excepción con el label de error 
            //y se mostrará un mensaje.(“No se puede acceder al servidor”)
            handleAlertError("It cannot connect to the server.");
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
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
                lblError.setText("");

            }

        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            //lanzar excepcion de error
            handleLabelError(e.getMessage());
        } finally {
            //Si el campo del texto está rellenado se habilita el botón de entrar
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
                lblError.setText("");
            }
            //Si el campo del texto está rellenado se habilita el botón de entrar
            enableButtonSignIn();
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            //lanzar excepcion de error
            handleLabelError(e.getMessage());
        } finally {
            //Si el campo del texto está rellenado se habilita el botón de entrar
            enableButtonSignIn();
        }
    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void handeltxtEmailFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (oldValue && !newValue && !txtEmail.getText().isEmpty()) {
            lblError.setText("");
        }
    }

    private void handletxtPasswordFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (oldValue && !newValue) {

        }
    }

    //Metodo para hablitar el boton
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
