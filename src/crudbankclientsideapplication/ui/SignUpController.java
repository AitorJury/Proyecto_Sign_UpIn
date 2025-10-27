package crudbankclientsideapplication.ui;

// Imports
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Aitor Jury Rodríguez.
 */
public class SignUpController {
    // TextFields
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

    // Labels
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
        
    // Botones y Links
    @FXML
    private Button btnExit;
    @FXML
    private Button btnCreateAccount;
    @FXML
    private Hyperlink linkSignIn;

    // Logger para consola
    private static final Logger LOGGER = 
            Logger.getLogger("crudbankclientsideapplication.ui");
    
    // Lista de todos los TextFields
    private List<TextField> txtFields;
    // Lista de todos los Labels
    private List<Label> errLabels;

    // Rellena las listas de TextFields y Labels para manejarlas en conjunto
    @FXML
    private void initialize() {
        txtFields = Arrays.asList(
            txtFirstName, txtLastName, txtMiddleInitial, txtEmail,
            txtPassword, txtRepeatPassword, txtPhone, txtCity,
            txtState, txtStreet, txtZip
        );

        errLabels = Arrays.asList(
            errFirstName, errLastName, errMiddleInitial, errEmail,
            errPassword, errRepeatPassword, errPhone,
            errCityState, errStreetZip
        );
    }


    /**
     * Inicializa la ventana Sign Up y configura los controles y el 
     * comportamiento
     * 
     * @param stage La ventana principal (Stage) donde se muestra la escena.
     * @param root  La raíz (Parent) que contiene los elementos del FXML.
     */
    public void init(Stage stage, Parent root) {
        try {
        // Mensaje de ventana
        LOGGER.info("Initializing window.");

        // Establecer el título de la ventana
        stage.setTitle("Sign Up");
        // La ventana no debe ser redimensionable
        stage.setResizable(false);
        // Los labels de error se vacían
        handleErrLabelChange(null);
        // El botón tnExit estará habilitado
        btnExit.setDisable(false);
        // El botón btnCreateAccount estará deshabilitado
        btnCreateAccount.setDisable(true);
        // El foco se coloca en txtFirstName
        txtFirstName.requestFocus();
        // Los campos de texto y password están habilitados por defecto
        // La ventana es por defecto no modal

        // Asociar eventos de botones a manejadores
        btnExit.setOnAction(this::handleBtnExitOnAction);
        btnCreateAccount.setOnAction(this::handleBtnCreateAccountOnAction);
        // Asociar eventos de campos de texto a manejadores
        txtFirstName.textProperty().addListener(this::handleTxtFirstNameTextChange);
        txtFirstName.focusedProperty().addListener(this::handleTxtFirstNameFocusChange);
        txtLastName.textProperty().addListener(this::handleTxtLastNameTextChange);
        txtLastName.focusedProperty().addListener(this::handleTxtLastNameFocusChange);
        txtMiddleInitial.textProperty().addListener(this::handleTxtMiddleInitialTextChange);
        txtMiddleInitial.focusedProperty().addListener(this::handleTxtMiddleInitialFocusChange);
        txtEmail.textProperty().addListener(this::handleTxtEmailTextChange);
        txtEmail.focusedProperty().addListener(this::handleTxtEmailFocusChange);
        txtPassword.textProperty().addListener(this::handleTxtPasswordTextChange);
        txtPassword.focusedProperty().addListener(this::handleTxtPasswordFocusChange);
        txtRepeatPassword.textProperty().addListener(this::handleTxtRepeatPasswordTextChange);
        txtRepeatPassword.focusedProperty().addListener(this::handleTxtRepeatPasswordFocusChange);
        txtPhone.textProperty().addListener(this::handleTxtPhoneTextChange);
        txtPhone.focusedProperty().addListener(this::handleTxtPhoneFocusChange);
        txtCity.textProperty().addListener(this::handleTxtCityTextChange);
        txtCity.focusedProperty().addListener(this::handleTxtCityFocusChange);
        txtState.textProperty().addListener(this::handleTxtStateTextChange);
        txtState.focusedProperty().addListener(this::handleTxtStateFocusChange);
        txtStreet.textProperty().addListener(this::handleTxtStreetTextChange);
        txtStreet.focusedProperty().addListener(this::handleTxtStreetFocusChange);
        txtZip.textProperty().addListener(this::handleTxtZipTextChange);
        txtZip.focusedProperty().addListener(this::handleTxtZipFocusChange);
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
            // Mostrar alert modal de confirmación para salir de la aplicación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to exit?");
            alert.showAndWait().ifPresent(resp -> {
            // Si confirma, cerrar la aplicación
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
        
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo FirstName.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtFirstNameTextChange(ObservableValue observable, String oldValue, String newValue) {
        try {
            // Validar el formato del texto
            // Si es válido, rodear el campo en verde
            // Si el formato no es válido (contiene símbolos distintos a letras)
            // rodear el campo en rojo y lanzar una excepción
            if (!newValue.matches("[a-zA-Z]+")) {
                throw new Exception("FirstName must contain only letters");
            } else {
                txtFirstName.setStyle("-fx-border-color: green; -fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null);
            }
        } catch (Exception e) {
            txtFirstName.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-border-radius: 5;");
            // Lanzar excepción con mensaje de error
            handleErrLabelChange(e.getMessage());
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
            String text = txtFirstName.getText();
            try {
                // Validar el formato del texto y si está vacío
                // Si es válido, rodear el campo en verde
                // Si el formato no es válido (contiene símbolos distintos a 
                // letras) o está vacío al perder el foco; rodear el campo en 
                // rojo y lanzar una excepción
                
                if (text == null || text.trim().isEmpty()) {
                    txtFirstName.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("FirstName must not be empty");
                } else if (!text.matches("[a-zA-Z]+")) {
                    txtFirstName.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("FirstName must contain only letters");
                } else {
                    txtFirstName.setStyle("-fx-border-color: green; -fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null);
                }
            } catch (Exception e) {
                // Lanzar excepción con mensaje de error
                handleErrLabelChange(e.getMessage());
            } finally {
                // Comprobar habilitación de btnCreateAccount
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
    private void handleTxtLastNameTextChange(ObservableValue observable, String oldValue, String newValue) {
        try {
            // Validar el formato del texto
            // Si es válido, rodear el campo en verde
            // Si el formato no es válido (contiene símbolos distintos a letras)
            // rodear el campo en rojo y lanzar una excepción
            if (!newValue.matches("[a-zA-Z]+")) {
                throw new Exception("LastName must contain only letters");
            } else {
                txtLastName.setStyle("-fx-border-color: green; -fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null);
            }
        } catch (Exception e) {
            txtLastName.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-border-radius: 5;");
            // Lanzar excepción con mensaje de error
            handleErrLabelChange(e.getMessage());
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo LastName.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtLastNameFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtLastName.getText();
            try {
                // Validar el formato del texto y si está vacío
                // Si es válido, rodear el campo en verde
                // Si el formato no es válido (contiene símbolos distintos a 
                // letras) o está vacío al perder el foco; rodear el campo en 
                // rojo y lanzar una excepción
                
                if (text == null || text.trim().isEmpty()) {
                    txtLastName.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("LastName must not be empty");
                } else if (!text.matches("[a-zA-Z]+")) {
                    txtLastName.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("LastName must contain only letters");
                } else {
                    txtLastName.setStyle("-fx-border-color: green; -fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null);
                }
            } catch (Exception e) {
                // Lanzar excepción con mensaje de error
                handleErrLabelChange(e.getMessage());
            } finally {
                // Comprobar habilitación de btnCreateAccount
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
    private void handleTxtMiddleInitialTextChange(ObservableValue observable, String oldValue, String newValue) {
        try {
            // Validar el formato del texto
            // Si es válido, rodear el campo en verde
            // Si el formato no es válido (contiene símbolos distintos a letras
            // o contiene más de una letra) rodear el campo en rojo y lanzar 
            // una excepción
            if (!newValue.matches("[a-zA-Z]")) {
                throw new Exception("MiddleInitial must be a single letter");
            } else {
                txtMiddleInitial.setStyle("-fx-border-color: green; -fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null);
            }
        } catch (Exception e) {
            txtMiddleInitial.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-border-radius: 5;");
            // Lanzar excepción con mensaje de error
            handleErrLabelChange(e.getMessage());
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo MiddleInitial.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtMiddleInitialFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            String text = txtMiddleInitial.getText();
            try {
                // Validar el formato del texto y si está vacío
                // Si es válido, rodear el campo en verde
                // Si el formato no es válido (contiene símbolos distintos a 
                // letras o contiene más de una letra) o está vacío al perder 
                // el foco; rodear el campo en rojo y lanzar una excepción
                if (text == null || text.trim().isEmpty()) {
                    txtMiddleInitial.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("MiddleInitial must not be empty");
                } else if (!text.matches("[a-zA-Z]")) {
                    txtMiddleInitial.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-border-radius: 5;");
                    throw new Exception("MiddleInitial must be a single letter");
                } else {
                    txtMiddleInitial.setStyle("-fx-border-color: green; -fx-background-radius: 5; -fx-border-radius: 5;");
                    handleErrLabelChange(null);
                }
            } catch (Exception e) {
                // Lanzar excepción con mensaje de error
                handleErrLabelChange(e.getMessage());
            } finally {
                // Comprobar habilitación de btnCreateAccount
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
    private void handleTxtEmailTextChange(ObservableValue observable, String oldValue, String newValue) {
        try {
            // Validar formato de email
            // Comprobar en la base de datos si el email ya existe
            // Si es válido, rodear el campo en verde
            // Si el formato no es válido (no contiene ‘@’) o existe en la base 
            // de datos al perder el foco; rodear el campo en rojo y lanzar 
            // una excepción

            if (!newValue.matches("[a-zA-Z]")) {
                throw new Exception("MiddleInitial must be a single letter");
            } else {
                txtMiddleInitial.setStyle("-fx-border-color: green; -fx-background-radius: 5; -fx-border-radius: 5;");
                handleErrLabelChange(null);
            }
        } catch (Exception e) {
            txtMiddleInitial.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-border-radius: 5;");
            // Lanzar excepción con mensaje de error
            handleErrLabelChange(e.getMessage());
        }
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo Email.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtEmailFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if(!newValue) {
            
        } else {
            
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo Password.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtPasswordTextChange(ObservableValue observable, String oldValue, String newValue) {
        
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo Password.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtPasswordFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if(!newValue) {
            
        } else {
            
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo RepeatPassword.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtRepeatPasswordTextChange(ObservableValue observable, String oldValue, String newValue) {
        
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo RepeatPassword.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtRepeatPasswordFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if(!newValue) {
            
        } else {
            
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo Phone.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtPhoneTextChange(ObservableValue observable, String oldValue, String newValue) {
        
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo Phone.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtPhoneFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if(!newValue) {
            
        } else {
            
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo City.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtCityTextChange(ObservableValue observable, String oldValue, String newValue) {
        
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo City.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtCityFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if(!newValue) {
            
        } else {
            
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo State.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtStateTextChange(ObservableValue observable, String oldValue, String newValue) {
        
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo State.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtStateFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if(!newValue) {
            
        } else {
            
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo Street.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtStreetTextChange(ObservableValue observable, String oldValue, String newValue) {
        
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo Street.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtStreetFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if(!newValue) {
            
        } else {
            
        }
    }
    
    /**
    * Manejador del evento de cambio de texto en el campo Zip.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del texto.
    * @param newValue El nuevo valor del texto.
    */
    private void handleTxtZipTextChange(ObservableValue observable, String oldValue, String newValue) {
        
    }
    
    /**
    * Manejador del evento de cambio de foco en el campo Zip.
    * 
    * @param observable El valor observable que cambia.
    * @param oldValue El valor anterior del foco.
    * @param newValue El nuevo valor del foco.
    */
    private void handleTxtZipFocusChange(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if(!newValue) {
            
        } else {
            
        }
    }
    
    /**
    * Gestiona los labels de error de la ventana Sign Up:
    * Si el parámetro errText es null, se limpian todos los labels de error.  
    * Si errText contiene texto, se usará para mostrar un mensaje de error.
    *
    * @param errText El texto de error a mostrar
    */
    private void handleErrLabelChange(String errText) {
        if(errText == null) {
            for(Label errLabel : errLabels) {
                errLabel.setText("");
            }
        } else {
            for (int i = 0; i < txtFields.size(); i++) {
                TextField txtField = txtFields.get(i);
                Label errLabel = errLabels.get(i);
                if (errText.startsWith(txtField.getId().substring(3))) {
                    errLabel.setText(errText);
                }
            }
        }
    }
    
    /**
    * Habilita btnCreateAccount si todos los campos son válidos
    */
    private void checkBtnCreateAccount() {
    boolean allValid =
            (txtFirstName.getText() != null) &&
            (txtFirstName.getText().matches("[a-zA-Z]+")) &&
            (txtLastName.getText() != null) && 
            (txtLastName.getText().matches("[a-zA-Z]+")) &&
            (txtMiddleInitial.getText() != null) &&
            (txtMiddleInitial.getText().matches("[a-zA-Z]+"));
    btnCreateAccount.setDisable(!allValid);
    }
}
