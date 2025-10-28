package crudbankclientsideapplication.ui;

// Imports.
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private Label errCityState;
    @FXML
    private Label errStreetZip;
        
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
    
    // Lista de todos los TextFields.
    private List<TextField> txtFields;
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
            errCityState, errStreetZip);
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
        handleErrLabelChange(null, null);
        btnExit.setDisable(false);
        btnCreateAccount.setDisable(true);
        txtFirstName.requestFocus();

        // Asociar eventos de botones a manejadores.
        btnExit.setOnAction(this::handleBtnExitOnAction);
        btnCreateAccount.setOnAction(this::handleBtnCreateAccountOnAction);
        // Asociar eventos de campos de texto a manejadores.
        txtFirstName.textProperty().addListener(this::
                handleTxtFirstNameTextChange);
        txtFirstName.focusedProperty().addListener(this::
                handleTxtFirstNameFocusChange);
        
        txtLastName.textProperty().addListener(this::
                handleTxtLastNameTextChange);
        txtLastName.focusedProperty().addListener(this::
                handleTxtLastNameFocusChange);
        
        txtMiddleInitial.textProperty().addListener(this::
                handleTxtMiddleInitialTextChange);
        txtMiddleInitial.focusedProperty().addListener(this::
                handleTxtMiddleInitialFocusChange);
        
        txtEmail.textProperty().addListener(this::
                handleTxtEmailTextChange);
        txtEmail.focusedProperty().addListener(this::
                handleTxtEmailFocusChange);
        
        txtPassword.textProperty().addListener(this::
                handleTxtPasswordTextChange);
        txtPassword.focusedProperty().addListener(this::
                handleTxtPasswordFocusChange);
        
        txtRepeatPassword.textProperty().addListener(this::
                handleTxtRepeatPasswordTextChange);
        txtRepeatPassword.focusedProperty().addListener(this::
                handleTxtRepeatPasswordFocusChange);
        
        txtPhone.textProperty().addListener(this::
                handleTxtPhoneTextChange);
        txtPhone.focusedProperty().addListener(this::
                handleTxtPhoneFocusChange);
        
        txtCity.textProperty().addListener(this::
                handleTxtCityTextChange);
        txtCity.focusedProperty().addListener(this::
                handleTxtCityFocusChange);
        
        txtState.textProperty().addListener(this::
                handleTxtStateTextChange);
        txtState.focusedProperty().addListener(this::
                handleTxtStateFocusChange);
        
        txtStreet.textProperty().addListener(this::
                handleTxtStreetTextChange);
        txtStreet.focusedProperty().addListener(this::
                handleTxtStreetFocusChange);
        
        txtZip.textProperty().addListener(this::
                handleTxtZipTextChange);
        txtZip.focusedProperty().addListener(this::
                handleTxtZipFocusChange);
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
        // Aquí se implementará la creación de usuario en la base de datos,
        // pero por ahora solo validamos que todos los campos sean correctos.
        Alert alert = new Alert(Alert.AlertType.INFORMATION, 
                "All fields valid! Ready to create account.");
        alert.showAndWait();
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo FirstName.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtFirstNameTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            if (!newValue.matches("[a-zA-Z]+")) {
                throw new Exception("FirstName must contain only letters");
            } else if (newValue.length() > 20) {
                txtFirstName.setText(newValue.substring(0, 20));
                throw new Exception("FirstName cannot exceeded length of 20");
            } else {
                txtFirstName.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtFirstName);
            }
        } catch (Exception e) {
            txtFirstName.setStyle("-fx-border-color: red; "
                    + "-fx-background-radius: 5; -fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtFirstName);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo FirstName.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtFirstNameFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtFirstName.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtFirstName.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("FirstName must not be empty");
                } else if (!text.matches("[a-zA-Z]+")) {
                    txtFirstName.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("FirstName must contain only letters");
                } else if (text.length() > 20) {
                    txtFirstName.setText(text.substring(0, 20));
                    throw new Exception("FirstName cannot exceeded length of 20");
                } else {
                    txtFirstName.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtFirstName);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtFirstName);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }

    /**
    * Manejador del evento de cambio de texto en el campo LastName.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtLastNameTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            if (!newValue.matches("[a-zA-Z]+")) {
                throw new Exception("LastName must contain only letters");
            } else if (newValue.length() > 20) {
                txtLastName.setText(newValue.substring(0, 20));
                throw new Exception("LastName cannot exceeded length of 20");
            } else {
                txtLastName.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtLastName);
            }
        } catch (Exception e) {
            txtLastName.setStyle("-fx-border-color: red; "
                    + "-fx-background-radius: 5; -fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtLastName);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo LastName.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtLastNameFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtLastName.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtLastName.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("LastName must not be empty");
                } else if (!text.matches("[a-zA-Z]+")) {
                    txtLastName.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("LastName must contain only letters");
                } else if (text.length() > 20) {
                    txtLastName.setText(text.substring(0, 20));
                    throw new Exception("LastName cannot exceeded length of 20");
                } else {
                    txtLastName.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtLastName);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtLastName);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo MiddleInitial.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtMiddleInitialTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            if (!newValue.matches("[a-zA-Z]")) {
                throw new Exception("MiddleInitial must be a single letter");
            } else if (newValue.length() > 1) {
                txtMiddleInitial.setText(newValue.substring(0, 1));
                throw new Exception("MiddleInitial cannot exceeded length of 1");
            } else {
                txtMiddleInitial.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtMiddleInitial);
            }
        } catch (Exception e) {
            txtMiddleInitial.setStyle("-fx-border-color: red; "
                    + "-fx-background-radius: 5; -fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtMiddleInitial);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo MiddleInitial.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtMiddleInitialFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtMiddleInitial.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtMiddleInitial.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("MiddleInitial must not be empty");
                } else if (!text.matches("[a-zA-Z]")) {
                    txtMiddleInitial.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("MiddleInitial must be a single letter");
                } else if (text.length() > 1) {
                    txtMiddleInitial.setText(text.substring(0, 1));
                    throw new Exception("MiddleInitial cannot exceeded length of 1");
                } else {
                    txtMiddleInitial.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtMiddleInitial);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtMiddleInitial);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo Email.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtEmailTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            if (!newValue.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                throw new Exception("Email format invalid");
            } else if (newValue.length() > 50) {
                txtEmail.setText(newValue.substring(0, 50));
                throw new Exception("Email cannot exceeded length of 50");
            } else {
                txtEmail.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtEmail);
            }
        } catch (Exception e) {
            txtEmail.setStyle("-fx-border-color: red; -fx-background-radius: 5;"
                    + "-fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtEmail);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo Email.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtEmailFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtEmail.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtEmail.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("Email must not be empty");
                } else if (!text.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                    txtEmail.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("Email format invalid");
                } else if (text.length() > 50) {
                    txtEmail.setText(text.substring(0, 50));
                    throw new Exception("Email cannot exceeded length of 50");
                } else {
                    txtEmail.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtEmail);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtEmail);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo Password.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtPasswordTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            if (newValue == null || newValue.trim().isEmpty()) {
                throw new Exception("Password must not be empty");
            } else if (!newValue.matches("[a-zA-Z0-9]+")) {
                throw new Exception("Password must contain no special "
                        + "characters");
            } else if (newValue.length() < 8) {
                throw new Exception("Password must be at least 8 characters");
            } else {
                txtPassword.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtPassword);
            }
        } catch (Exception e) {
            txtPassword.setStyle("-fx-border-color: red; "
                    + "-fx-background-radius: 5; -fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtPassword);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo Password.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtPasswordFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtPassword.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtPassword.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("Password must not be empty");
                } else if (!text.matches("[a-zA-Z0-9]+")) {
                    txtPassword.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("Password must contain no special "
                            + "characters");
                } else if (text.length() < 8) {
                    throw new Exception("Password must be at least 8 characters");
                } else {
                    txtPassword.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtPassword);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtPassword);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo RepeatPassword.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtRepeatPasswordTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            String psw = txtPassword.getText();
            if (newValue == null || newValue.trim().isEmpty() || 
                    !newValue.equals(psw)) {
                throw new Exception("RepeatPassword must be like Password");
            } else {
                txtRepeatPassword.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtRepeatPassword);
            }
        } catch (Exception e) {
            txtRepeatPassword.setStyle("-fx-border-color: red; "
                    + "-fx-background-radius: 5; -fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtRepeatPassword);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo RepeatPassword.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtRepeatPasswordFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtRepeatPassword.getText();
            String psw = txtPassword.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtRepeatPassword.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("RepeatPassword must not be empty");
                } else if (!text.equals(psw)) {
                    txtRepeatPassword.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("RepeatPassword must be like Password");
                } else {
                    txtRepeatPassword.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtRepeatPassword);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtRepeatPassword);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo Phone.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtPhoneTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            if (!newValue.matches("\\d*")) {
                throw new Exception("Phone must contain only numbers");
            } else if (newValue.length() > 15 || newValue.length() < 7) {
                txtPhone.setText(newValue.substring(0, 15));
                throw new Exception("Phone length must be on 7-15");
            } else {
                txtPhone.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtPhone);
            }
        } catch (Exception e) {
            txtPhone.setStyle("-fx-border-color: red; "
                    + "-fx-background-radius: 5; -fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtPhone);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo Phone.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtPhoneFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtPhone.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtPhone.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("Phone must not be empty");
                } else if (!text.matches("\\d*")) {
                    txtPhone.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("Phone must contain only numbers");
                } else if (text.length() > 15 || text.length() < 7) {
                    txtPhone.setText(text.substring(0, 15));
                    throw new Exception("Phone length must be on 7-15");
                } else {
                    txtPhone.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtPhone);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtPhone);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo City.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtCityTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            if (!newValue.matches("[a-zA-Z]+")) {
                throw new Exception("City must contain only letters");
            } else if (newValue.length() > 20) {
                txtCity.setText(newValue.substring(0, 20));
                throw new Exception("City cannot exceeded length of 20");
            } else {
                txtCity.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtCity);
            }
        } catch (Exception e) {
            txtCity.setStyle("-fx-border-color: red; "
                    + "-fx-background-radius: 5; -fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtCity);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo City.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtCityFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtCity.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtCity.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("City must not be empty");
                } else if (!text.matches("[a-zA-Z]+")) {
                    txtCity.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("City must contain only letters");
                } else if (text.length() > 20) {
                    txtCity.setText(text.substring(0, 20));
                    throw new Exception("City cannot exceeded length of 20");
                } else {
                    txtCity.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtCity);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtCity);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo State.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtStateTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            if (!newValue.matches("[a-zA-Z]+")) {
                throw new Exception("State must contain only letters");
            } else if (newValue.length() > 30) {
                txtState.setText(newValue.substring(0, 30));
                throw new Exception("State cannot exceeded length of 30");
            } else {
                txtState.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtState);
            }
        } catch (Exception e) {
            txtState.setStyle("-fx-border-color: red; "
                    + "-fx-background-radius: 5; -fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtState);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo State.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtStateFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtState.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtState.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("State must not be empty");
                } else if (!text.matches("[a-zA-Z]+")) {
                    txtState.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("State must contain only letters");
                } else if (text.length() > 30) {
                    txtState.setText(text.substring(0, 30));
                    throw new Exception("State cannot exceeded length of 30");
                } else {
                    txtState.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtState);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtState);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo Street.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtStreetTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            if (newValue == null || newValue.trim().isEmpty()) {
                throw new Exception("Street must not be empty");
            } else if (newValue.length() > 50) {
                txtStreet.setText(newValue.substring(0, 50));
                throw new Exception("Street cannot exceeded length of 50");
            } else {
                txtStreet.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtStreet);
            }
        } catch (Exception e) {
            txtStreet.setStyle("-fx-border-color: red; "
                    + "-fx-background-radius: 5; -fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtStreet);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo Street.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtStreetFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtStreet.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtStreet.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("Street must not be empty");
                } else if (text.length() > 50) {
                    txtStreet.setText(text.substring(0, 50));
                    throw new Exception("Street cannot exceeded length of 50");
                } else {
                    txtStreet.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtStreet);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtStreet);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo Zip.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtZipTextChange(ObservableValue observable, 
            String oldValue, String newValue) {
        try {
            if (!newValue.matches("\\d+")) {
                throw new Exception("Zip must contain only numbers");
            } else if (newValue.length() != 5) {
                if (newValue.length() > 5) {
                    txtZip.setText(newValue.substring(0, 5));
                }
                throw new Exception("Zip must have length of 5");
            } else {
                txtZip.setStyle("-fx-border-color: green; "
                        + "-fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null, txtZip);
            }
        } catch (Exception e) {
            txtZip.setStyle("-fx-border-color: red; -fx-background-radius: 5; "
                    + "-fx-border-radius: 5;");
            handleErrLabelChange(e.getMessage(), txtZip);
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo Zip.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtZipFocusChange(ObservableValue observable, 
            Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtZip.getText();
            try {
                if (text == null || text.trim().isEmpty()) {
                    txtZip.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("Zip must not be empty");
                } else if (!text.matches("\\d+")) {
                    txtZip.setStyle("-fx-border-color: red; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("Zip must contain only numbers");
                } else if (text.length() != 5) {
                    if (text.length() > 5) {
                        txtZip.setText(text.substring(0, 5));
                    }
                    throw new Exception("Zip must have length of 5");
                } else {
                    txtZip.setStyle("-fx-border-color: green; "
                            + "-fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null, txtZip);
                }
            } catch (Exception e) {
                handleErrLabelChange(e.getMessage(), txtZip);
            } finally {
                checkBtnCreateAccount();
            }
        }
    }
    
    /**
    * Gestiona los labels de error de la ventana Sign Up.
    * Si el parámetro errText es null, se limpian todos los labels de error.  
    * Si errText contiene texto, se usará para mostrar un mensaje de error.
    *
    * @param errText El texto de error a mostrar.
    */
    private void handleErrLabelChange(String errText, TextField changedField) {
        if(errText == null) {
            if (changedField != null) {
                int i = txtFields.indexOf(changedField);
                if (i >= 0) {
                    errLabels.get(i).setText("");
                } else {
                    for (Label errLabel : errLabels) {
                        errLabel.setText("");
                    }
            }
        } else {
            for (int i = 0; i < txtFields.size(); i++) {
                TextField txtField = txtFields.get(i);
                Label errLabel = errLabels.get(i);
                if (changedField == txtField) {
                    errLabel.setText(errText);
                }
            }
        }
    }
    
    /**
    * Habilita btnCreateAccount si todos los campos son válidos.
    */
    private void checkBtnCreateAccount() {
        boolean allValid = true;

        try {
            String firstName = txtFirstName.getText();
            if (firstName == null || !firstName.matches("[a-zA-Z]{1,20}")) {
                allValid = false;
            }

            String lastName = txtLastName.getText();
            if (lastName == null || !lastName.matches("[a-zA-Z]{1,20}")) {
                allValid = false;
            }

            String middleInitial = txtMiddleInitial.getText();
            if (middleInitial == null || !middleInitial.matches("[a-zA-Z]{1}")) {
                allValid = false;
            }

            String email = txtEmail.getText();
            if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+${1-50}")) {
                allValid = false;
            }

            String password = txtPassword.getText();
            if (password == null || !password.matches("[a-zA-Z0-9]") || 
                    password.length() < 8) {
                allValid = false;
            }

            String repeatPassword = txtRepeatPassword.getText();
            if (repeatPassword == null || !repeatPassword.equals(password)) {
                allValid = false;
            }

            String phone = txtPhone.getText();
            if (phone == null || !phone.matches("\\d{7,15}")) {
                allValid = false;
            }

            String city = txtCity.getText();
            if (city == null || !city.matches("[a-zA-Z\\s]{1,20}")) {
                allValid = false;
            }

            String state = txtState.getText();
            if (state == null || !state.matches("[a-zA-Z\\s]{1,30}")) {
                allValid = false;
            }

            String street = txtStreet.getText();
            if (street == null || street.trim().isEmpty() || street.length() > 50) {
                allValid = false;
            }

            String zip = txtZip.getText();
            if (zip == null || !zip.matches("\\d{5}")) {
                allValid = false;
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error during form validation: " + e.getMessage(), e);
            allValid = false;
        }

        // Activar o desactivar el botón según la validez de todos los campos
        btnCreateAccount.setDisable(!allValid);
    }
}
