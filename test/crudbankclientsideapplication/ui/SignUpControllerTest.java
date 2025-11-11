package crudbankclientsideapplication.ui;

import crudbankclientsideapplication.CRUDBankClientSideApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author aitor
 */
public class SignUpControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new CRUDBankClientSideApplication().start(stage);
    }

    @Test
    public void test1_TextFieldsEmpty() {
        clickOn("#linkSignUp");
        verifyThat("#txtFirstName", hasText(""));
        verifyThat("#txtLastName", hasText(""));
        verifyThat("#txtMiddleInitial", hasText(""));
        verifyThat("#txtPhone", hasText(""));
        verifyThat("#txtCity", hasText(""));
        verifyThat("#txtState", hasText(""));
        verifyThat("#txtStreet", hasText(""));
        verifyThat("#txtZip", hasText(""));
        verifyThat("#txtEmail", hasText(""));
        verifyThat("#txtPassword", hasText(""));
        verifyThat("#txtRepeatPassword", hasText(""));
    }

    @Test
    public void test2_FirstNameIsFocused() {
        clickOn("#linkSignUp");
        verifyThat("#txtFirstName", isFocused());
    }

    @Test
    public void test3_ButtonExitEanbled() {
        clickOn("#linkSignUp");
        verifyThat("#btnExit", isEnabled());
    }

    @Test
    public void test4_CorrectSignUp() {
        clickOn("#linkSignUp");
        write("Aitor");
        clickOn("#txtLastName");
        write("Jury");
        clickOn("#txtMiddleInitial");
        write("R");
        clickOn("#txtPhone");
        write("626262626");
        clickOn("#txtCity");
        write("Madrid");
        clickOn("#txtState");
        write("Comunidad de Madrid");
        clickOn("#txtStreet");
        write("Calle ejemplo, 12, 2ºD");
        clickOn("#txtZip");
        write("28260");
        clickOn("#txtEmail");
        write("ejemplo" + System.currentTimeMillis() + "@mail.com");
        clickOn("#txtPassword");
        write("abcd*1234");
        clickOn("#txtRepeatPassword");
        write("abcd*1234");

        verifyThat("#btnCreateAccount", isEnabled());
        clickOn("#btnCreateAccount");
        verifyThat("Customer created and logged in successfully!", isVisible());
        clickOn("OK");
        verifyThat("#btnSignIn", isVisible());
    }

    // Este Test solo funciona si el correo ya existe en la base
    @Test
    public void test5_ExistingEmail_ForbiddenException() {
        clickOn("#linkSignUp");
        write("Existente");
        clickOn("#txtLastName");
        write("Test");
        clickOn("#txtMiddleInitial");
        write("A");
        clickOn("#txtEmail");
        write("aitor.jr04@mail.com");
        clickOn("#txtPassword");
        write("abcd*1234");
        clickOn("#txtRepeatPassword");
        write("abcd*1234");
        clickOn("#txtPhone");
        write("123456789");
        clickOn("#txtCity");
        write("Sevilla");
        clickOn("#txtState");
        write("Andalucia");
        clickOn("#txtStreet");
        write("Calle Ejemplo");
        clickOn("#txtZip");
        write("12345");
        clickOn("#txtEmail");

        verifyThat("#btnCreateAccount", isEnabled());
        clickOn("#btnCreateAccount");
        verifyThat("The email exists. Please try another.", isVisible());
        clickOn("OK");

    }

    @Test
    public void test6_BtnExit_CloseWindow() {
        clickOn("#linkSignUp");
        clickOn("#btnExit");
        verifyThat("Do you really want to exit?", isVisible());
        clickOn("No");
        verifyThat("#btnExit", isVisible());
        clickOn("#btnExit");
        clickOn("Yes");
    }

    @Test
    public void test7_LinkSignIn_OpenWindowSignIn() {
        clickOn("#linkSignUp");
        clickOn("#linkSignIn");
        verifyThat("#btnSignIn", isVisible());
    }
    
    @Test
    public void test8_BtnCreateAccount_DisabledIfIsEmpty() {
        clickOn("#linkSignUp");
        write("Existente");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtLastName");
        write("Test");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtMiddleInitial");
        write("A");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtEmail");
        write("aitor.jr04@mail.com");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtPassword");
        write("abcd*1234");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtRepeatPassword");
        write("abcd*1234");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtPhone");
        write("123456789");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtCity");
        write("Sevilla");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtState");
        write("Andalucia");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtStreet");
        write("Calle Ejemplo");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtZip");
        write("");
        clickOn("#txtStreet");
        verifyThat("#btnCreateAccount", isDisabled());
        clickOn("#txtZip");
        write("12345");
        clickOn("#txtEmail");
        verifyThat("#btnCreateAccount", isEnabled());
    }
    
    // El siguiente test solo funciona si el servidor está desconectado
    /*
    @Test
    public void test9_ServerOff() {
        clickOn("#linkSignUp");
        write("Aitor");
        clickOn("#txtLastName");
        write("Jury");
        clickOn("#txtMiddleInitial");
        write("R");
        clickOn("#txtPhone");
        write("626262626");
        clickOn("#txtCity");
        write("Madrid");
        clickOn("#txtState");
        write("Comunidad de Madrid");
        clickOn("#txtStreet");
        write("Calle ejemplo, 12, 2ºD");
        clickOn("#txtZip");
        write("28260");
        clickOn("#txtEmail");
        write("ejemplo" + System.currentTimeMillis() + "@mail.com");
        clickOn("#txtPassword");
        write("abcd*1234");
        clickOn("#txtRepeatPassword");
        write("abcd*1234");

        verifyThat("#btnCreateAccount", isEnabled());
        clickOn("#btnCreateAccount");
    
        verifyThat("Server error. Please try again later.", isVisible());
        clickOn("OK");
    }
    */
}
