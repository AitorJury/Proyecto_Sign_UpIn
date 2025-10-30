package crudbankclientsideapplication.ui;

// Imports.
import crudbankclientsideapplication.model.Customer;
import crudbankclientsideapplication.logic.CustomerRESTClient;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;

/**
 * Controlador de la ventana Sign Up.
 *
 * @author Aitor Jury Rodríguez.
 */
public class SignUpController {
    // TextFields.
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtMiddleInitial;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRepeatPassword;
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

    // Labels.
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
    private Label errCity;
    @FXML
    private Label errState;
    @FXML
    private Label errStreet;
    @FXML
    private Label errZip;
        
    // Botones y Links.
    @FXML
    private Button btnExit;
    @FXML
    private Button btnCreateAccount;
    @FXML
    private Hyperlink linkSignIn;

    // Logger para consola.
    private static final Logger LOGGER = 
            Logger.getLogger("crudbankclientsideapplication.ui");
    
    // Lista de todos los TextFields y PasswordFields.
    private List<javafx.scene.control.TextInputControl> txtFields;
    // Lista de todos los Labels.
    private List<Label> errLabels;

    // Agrupa campos y labels para manejo centralizado.
    @FXML
    private void initialize() {
        txtFields = Arrays.asList(
            txtFirstName, txtLastName, txtMiddleInitial, txtEmail,
            txtPassword, txtRepeatPassword, txtPhone, txtCity,
            txtState, txtStreet, txtZip);

        errLabels = Arrays.asList(
            errFirstName, errLastName, errMiddleInitial, errEmail,
            errPassword, errRepeatPassword, errPhone,
            errCity, errState, errStreet, errZip);
    }
    
    /**
     * Inicializa la ventana Sign Up y configura los controles.
     * 
     * @param stage La ventana principal (Stage) donde se muestra la escena.
     * @param root  La raíz (Parent) que contiene los elementos del FXML.
     */
    public void init(Stage stage, Parent root) {
        try {
        LOGGER.info("Initializing window.");
        stage.setTitle("Sign Up");
        stage.setResizable(false);
        for (Label err : errLabels) {
            err.setText("");
        }
        btnExit.setDisable(false);
        btnCreateAccount.setDisable(true);
        txtFirstName.requestFocus();

        // Asociar eventos de botones a manejadores.
        btnExit.setOnAction(this::handleBtnExitOnAction);
        btnCreateAccount.setOnAction(this::handleBtnCreateAccountOnAction);
        // Asociar eventos de campos de texto a manejadores.
        txtFirstName.focusedProperty().addListener(this::
                handleTxtFirstNameFocusChange);
        txtLastName.focusedProperty().addListener(this::
                handleTxtLastNameFocusChange);
        txtMiddleInitial.focusedProperty().addListener(this::
                handleTxtMiddleInitialFocusChange);
        txtEmail.focusedProperty().addListener(this::
                handleTxtEmailFocusChange);
        txtPassword.textProperty().addListener(this::
                handleTxtPasswordTextChange);
        txtRepeatPassword.textProperty().addListener(this::
                handleTxtRepeatPasswordTextChange);
        txtPhone.focusedProperty().addListener(this::
                handleTxtPhoneFocusChange);
        txtCity.focusedProperty().addListener(this::
                handleTxtCityFocusChange);
        txtState.focusedProperty().addListener(this::
                handleTxtStateFocusChange);
        txtStreet.focusedProperty().addListener(this::
                handleTxtStreetFocusChange);
        txtZip.focusedProperty().addListener(this::
                handleTxtZipFocusChange);
        linkSignIn.setOnAction(this::handleLinkSignInAction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * Cierra la ventana al pulsar el botón Exit y confirmar.
    *
    * @param event El evento de acción generado por el botón.
    */
    private void handleBtnExitOnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                    "Do you really want to exit?");
            alert.showAndWait().ifPresent(resp -> {
                if (resp == ButtonType.OK) {
                    Stage stage = (Stage) btnExit.getScene().getWindow();
                    stage.close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Crea un usuario al pulsar el botón CreateAccount y confirmar que todos 
    * los textField sean válidos.
    *
    * @param event El evento de acción generado por el botón.
    */
    private void handleBtnCreateAccountOnAction(ActionEvent event) {
        try {
            // Crear un objeto Customer
            Customer customer = new Customer();
            // Establecer propiedades del objeto a partir de los valores de los campos
            customer.setFirstName(txtFirstName.getText());
            customer.setLastName(txtLastName.getText());
            customer.setMiddleInitial(txtMiddleInitial.getText());
            customer.setEmail(txtEmail.getText());
            customer.setPassword(txtPassword.getText());
            customer.setPhone(Long.parseLong(txtPhone.getText()));
            customer.setCity(txtCity.getText());
            customer.setState(txtState.getText());
            customer.setStreet(txtStreet.getText());
            customer.setZip(Integer.parseInt(txtZip.getText()));
            CustomerRESTClient client = new CustomerRESTClient();
            client.create_XML(customer);
            client.close();
            new Alert(Alert.AlertType.INFORMATION, "Customer created and logged in successfully!")
                        .showAndWait();
        } catch (ForbiddenException e) {
            // Usuario no autorizado
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,
                    "You are not allowed to create a customer.").showAndWait();
        } catch (InternalServerErrorException e) {
            // Error del servidor
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,
                    "Server error. Please try again later.").showAndWait();
        } catch (Exception e) {
            // Cualquier otro error inesperado
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,
                    "Unexpected error: " + e.getMessage()).showAndWait();
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo FirstName.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtFirstNameFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {
                String text = txtFirstName.getText().trim();
                if (text.isEmpty()) {
                    throw new Exception("FirstName must not be empty");
                }
                if (!text.matches("[a-zA-Z]+")) {
                    throw new Exception("FirstName must contain only letters");
                }
                if (text.length() > 20) {
                    txtFirstName.setText(text.substring(0, 20));
                    throw new Exception("FirstName cannot exceed length of 20");
                }

                txtFirstName.setStyle("-fx-border-color: green;");
                handleErrLabelChange(null, txtFirstName);
            } catch (Exception e) {
                txtFirstName.setStyle("-fx-border-color: red;");
                handleErrLabelChange(e.getMessage(), txtFirstName);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }

    /**
     * Manejador del evento de cambio de foco en el campo LastName. Valida el
     * campo al perder el foco.
     *
     * @param observable El valor observable que cambia.
     * @param oldValue El valor anterior del foco.
     * @param newValue El nuevo valor del foco.
     */
    private void handleTxtLastNameFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {
                String text = txtLastName.getText().trim();
                if (text.isEmpty()) {
                    throw new Exception("LastName must not be empty");
                }
                if (!text.matches("[a-zA-Z]+")) {
                    throw new Exception("LastName must contain only letters");
                }
                if (text.length() > 20) {
                    txtLastName.setText(text.substring(0, 20));
                    throw new Exception("LastName cannot exceed length of 20");
                }

                txtLastName.setStyle("-fx-border-color: green;");
                handleErrLabelChange(null, txtLastName);
            } catch (Exception e) {
                txtLastName.setStyle("-fx-border-color: red;");
                handleErrLabelChange(e.getMessage(), txtLastName);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
     * Manejador del evento de cambio de foco en el campo MiddleInitial. Valida
     * que sea una sola letra al perder el foco y recorta si es necesario.
     */
    private void handleTxtMiddleInitialFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {
                String text = txtMiddleInitial.getText().trim();
                if (text.isEmpty()) {
                    throw new Exception("MiddleInitial must not be empty");
                }
                if (!text.matches("[a-zA-Z]+")) {
                    throw new Exception("MiddleInitial must contain only letters");
                }
                if (text.length() > 1) {
                    text = text.substring(0, 1);
                    txtMiddleInitial.setText(text);
                }

                txtMiddleInitial.setStyle("-fx-border-color: green;");
                handleErrLabelChange(null, txtMiddleInitial);
            } catch (Exception e) {
                txtMiddleInitial.setStyle("-fx-border-color: red;");
                handleErrLabelChange(e.getMessage(), txtMiddleInitial);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
     * Manejador del evento de cambio de foco en el campo Phone. Valida que no
     * esté vacío, que contenga solo números y tenga entre 7 y 15 dígitos.
     *
     * @param observable El valor observable que cambia.
     * @param oldValue El valor anterior del foco.
     * @param newValue El nuevo valor del foco.
     */
    private void handleTxtPhoneFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {
                String text = txtPhone.getText().trim();
                if (text.isEmpty()) {
                    throw new Exception("Phone must not be empty");
                }
                if (!text.matches("\\d+")) {
                    throw new Exception("Phone must contain only numbers");
                }
                if (text.length() < 7 || text.length() > 15) {
                    throw new Exception("Phone length must be between 7 and 15 digits");
                }

                txtPhone.setStyle("-fx-border-color: green;");
                handleErrLabelChange(null, txtPhone);
            } catch (Exception e) {
                txtPhone.setStyle("-fx-border-color: red;");
                handleErrLabelChange(e.getMessage(), txtPhone);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
     * Manejador del evento de cambio de foco en el campo City. Valida que no
     * esté vacío, que contenga solo letras y espacios, y que no supere 20
     * caracteres.
     *
     * @param observable El valor observable que cambia.
     * @param oldValue El valor anterior del foco.
     * @param newValue El nuevo valor del foco.
     */
    private void handleTxtCityFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {
                String text = txtCity.getText().trim();
                if (text.isEmpty()) {
                    throw new Exception("City must not be empty");
                }
                if (!text.matches("[a-zA-Z\\s]+")) {
                    throw new Exception("City must contain only letters and spaces");
                }
                if (text.length() > 20) {
                    txtCity.setText(text.substring(0, 20));
                    throw new Exception("City cannot exceed length of 20");
                }

                txtCity.setStyle("-fx-border-color: green;");
                handleErrLabelChange(null, txtCity);
            } catch (Exception e) {
                txtCity.setStyle("-fx-border-color: red;");
                handleErrLabelChange(e.getMessage(), txtCity);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
     * Manejador del evento de cambio de foco en el campo State. Valida que no
     * esté vacío, que contenga solo letras y espacios, y que no supere 20
     * caracteres.
     *
     * @param observable El valor observable que cambia.
     * @param oldValue El valor anterior del foco.
     * @param newValue El nuevo valor del foco.
     */
    private void handleTxtStateFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {
                String text = txtState.getText().trim();
                if (text.isEmpty()) {
                    throw new Exception("State must not be empty");
                }
                if (!text.matches("[a-zA-Z\\s]+")) {
                    throw new Exception("State must contain only letters and spaces");
                }
                if (text.length() > 20) {
                    txtState.setText(text.substring(0, 20));
                    throw new Exception("State cannot exceed length of 20");
                }

                txtState.setStyle("-fx-border-color: green;");
                handleErrLabelChange(null, txtState);
            } catch (Exception e) {
                txtState.setStyle("-fx-border-color: red;");
                handleErrLabelChange(e.getMessage(), txtState);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }

    
    /**
     * Manejador del evento de cambio de foco en el campo Street. Valida que no
     * esté vacío, que tenga letras, números o símbolos válidos, y que no supere
     * 50 caracteres.
     */
    private void handleTxtStreetFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {
                String text = txtStreet.getText().trim();
                if (text.isEmpty()) {
                    throw new Exception("Street must not be empty");
                }
                if (!text.matches("[\\p{L}\\p{N}\\s,\\.-/ºª#]+")) {
                    throw new Exception("Street contains invalid characters");
                }
                if (text.length() > 50) {
                    txtStreet.setText(text.substring(0, 50));
                    throw new Exception("Street cannot exceed length of 50");
                }

                txtStreet.setStyle("-fx-border-color: green;");
                handleErrLabelChange(null, txtStreet);
            } catch (Exception e) {
                txtStreet.setStyle("-fx-border-color: red;");
                handleErrLabelChange(e.getMessage(), txtStreet);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
     * Manejador del evento de cambio de foco en el campo Zip. Valida que no
     * esté vacío, que contenga solo números y que tenga exactamente 5 dígitos.
     *
     * @param observable El valor observable que cambia.
     * @param oldValue El valor anterior del foco.
     * @param newValue El nuevo valor del foco.
     */
    private void handleTxtZipFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {
                String text = txtZip.getText().trim();
                if (text.isEmpty()) {
                    throw new Exception("Zip must not be empty");
                }
                if (!text.matches("\\d+")) {
                    throw new Exception("Zip must contain only numbers");
                }
                if (text.length() != 5) {
                    if (text.length() > 5) {
                        text = text.substring(0, 5);
                        txtZip.setText(text);
                    }
                    throw new Exception("Zip must have length of 5");
                }

                txtZip.setStyle("-fx-border-color: green;");
                handleErrLabelChange(null, txtZip);
            } catch (Exception e) {
                txtZip.setStyle("-fx-border-color: red;");
                handleErrLabelChange(e.getMessage(), txtZip);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
     * Manejador del evento de cambio de foco en el campo Email. Valida formato
     * y longitud al perder el foco.
     *
     * @param observable El valor observable que cambia.
     * @param oldValue El valor anterior del foco.
     * @param newValue El nuevo valor del foco.
     */
    private void handleTxtEmailFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            try {
                String text = txtEmail.getText().trim();
                if (text.isEmpty()) {
                    throw new Exception("Email must not be empty");
                }
                if (!text.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                    throw new Exception("Email format invalid");
                }
                if (text.length() > 50) {
                    txtEmail.setText(text.substring(0, 50));
                    throw new Exception("Email cannot exceed length of 50");
                }

                txtEmail.setStyle("-fx-border-color: green;");
                handleErrLabelChange(null, txtEmail);
            } catch (Exception e) {
                txtEmail.setStyle("-fx-border-color: red;");
                handleErrLabelChange(e.getMessage(), txtEmail);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }

    /**
     * Manejador del evento de cambio de texto en el campo Password. 
     * Valida que no esté vacío y que tenga al menos 8 caracteres.
     *
     * @param observable El valor observable que cambia.
     * @param oldValue El valor anterior del texto.
     * @param newValue El nuevo valor del texto.
     */
    private void handleTxtPasswordTextChange(ObservableValue observable, String oldValue, String newValue) {
        try {
            if (newValue.isEmpty()) {
                throw new Exception("Password must not be empty");
            }
            if (!newValue.matches("[a-zA-Z0-9.*!@#$%&\\-_]{8,}")) {
                throw new Exception("Password contains invalid characters");
            }
            if (newValue.length() < 8) {
                throw new Exception("Password must be at least 8 characters");
            }

            txtPassword.setStyle("-fx-border-color: green;");
            handleErrLabelChange(null, txtPassword);
        } catch (Exception e) {
            txtPassword.setStyle("-fx-border-color: red;");
            handleErrLabelChange(e.getMessage(), txtPassword);
        } finally {
            checkBtnCreateAccount();
        }
    }

    /**
     * Manejador del evento de cambio de texto en el campo RepeatPassword.
     * Valida que no esté vacío y que coincida con el Password.
     *
     * @param observable El valor observable que cambia.
     * @param oldValue El valor anterior del foco.
     * @param newValue El nuevo valor del foco.
     */
    private void handleTxtRepeatPasswordTextChange(ObservableValue observable, String oldValue, String newValue) {
        try {
            if (newValue.isEmpty()) {
                throw new Exception("RepeatPassword must not be empty");
            }
            if (!newValue.equals(txtPassword.getText())) {
                throw new Exception("RepeatPassword must match Password");
            }

            txtRepeatPassword.setStyle("-fx-border-color: green;");
            handleErrLabelChange(null, txtRepeatPassword);
        } catch (Exception e) {
            txtRepeatPassword.setStyle("-fx-border-color: red;");
            handleErrLabelChange(e.getMessage(), txtRepeatPassword);
        } finally {
            checkBtnCreateAccount();
        }
    }
    
    /**
     * Conecta con un hyperlink el Sign In.
     */
    private void handleLinkSignInAction(ActionEvent Event) {
        try {
            // Cargar FXML de SignIn
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/crudbankclientsideapplication/ui/SignIn.fxml"));
            Parent root = loader.load();

            // Obtener la ventana actual
            Stage stage = (Stage) linkSignIn.getScene().getWindow();

            // Cambiar la escena
            stage.setScene(new Scene(root));
            stage.setTitle("Sign In");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Could not open Sign In window.").showAndWait();
        }
    }
    
    /**
    * Gestiona los labels de error de la ventana Sign Up.
    * Si el parámetro errText es null, se limpian todos los labels de error.  
    * Si errText contiene texto, se usará para mostrar un mensaje de error.
    * 
    * @param message Mensaje de error a mostrar (null para limpiar)
     * @param textField Campo de texto asociado
    */
    private void handleErrLabelChange(String message, javafx.scene.control.TextInputControl textField) {
        if (errLabels == null || txtFields == null) return;

        int index = txtFields.indexOf(textField);
        if (index != -1 && index < errLabels.size()) {
            Label errLabel = errLabels.get(index);
            if (message == null || message.isEmpty()) {
                errLabel.setText("");
            } else {
                errLabel.setText(message);
            }
        }
    }
    
    /**
    * Habilita btnCreateAccount si todos los campos son válidos.
    */
    private void checkBtnCreateAccount() {
        boolean allValid = true;

        if (!txtFirstName.getText().matches("[a-zA-Z]{1,20}")) {
            allValid = false;
        }
        if (!txtLastName.getText().matches("[a-zA-Z]{1,20}")) {
            allValid = false;
        }
        if (!txtMiddleInitial.getText().matches("[a-zA-Z]{1}")) {
            allValid = false;
        }
        if (!txtEmail.getText().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            allValid = false;
        }
        if (!txtPassword.getText().matches("[a-zA-Z0-9.*!@#$%&\\-_]{8,}")) {
            allValid = false;
        }
        if (!txtRepeatPassword.getText().equals(txtPassword.getText())) {
            allValid = false;
        }
        if (!txtPhone.getText().matches("\\d{7,15}")) {
            allValid = false;
        }
        if (!txtCity.getText().matches("[a-zA-Z\\s]{1,20}")) {
            allValid = false;
        }
        if (!txtState.getText().matches("[a-zA-Z\\s]{1,30}")) {
            allValid = false;
        }
        String street = txtStreet.getText();
        if (street == null || street.trim().isEmpty() || street.length() > 50) {
            allValid = false;
        }
        if (!txtZip.getText().matches("\\d{5}")) {
            allValid = false;
        }

        btnCreateAccount.setDisable(!allValid);
    }
}
