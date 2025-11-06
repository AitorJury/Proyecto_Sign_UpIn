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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/crudbankclientsideapplication/ui/SignUp.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void test1_TextFieldsEmpty() {
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
        verifyThat("#txtFirstName", isFocused());
    }

    @Test
    public void test3_ButtonExitEanbled() {
        verifyThat("#btnExit", isEnabled());
    }

    @Test
    public void test4_CorrectSignUp() {
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

    @Test
    public void test5_ExistingEmail_ForbiddenException() {
        write("Existente");
        clickOn("#txtLastName");
        write("Test");
        clickOn("#txtMiddleInitial");
        write("A");
        clickOn("#txtEmail");
        write("existinguser@mail.com");
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

        verifyThat("#btnCreateAccount", isEnabled());
        clickOn("#btnCreateAccount");
        verifyThat("The email exists. Please try another.", isVisible());
        clickOn("OK");

    }

    @Test
    public void test6_BtnExit_CloseWindow() {
        clickOn("#btnExit");
        verifyThat("Do you really want to exit?", isVisible());
        clickOn("NO");
        verifyThat("#btnExit", isVisible());
        clickOn("#btnExit");
        clickOn("YES");
    }

    @Test
    public void test7_LinkSignIn_OpenWindowSignIn() {
        clickOn("#linkSignIn");
        verifyThat("#btnSignIn", isVisible());
    }
    
    @Test
    public void test8_BtnCreateAccount_DisabledIfIsEmpty() {
        write("Aitor");
        clickOn("#txtLastName");
        write("");
        clickOn("#txtMiddleInitial");
        verifyThat("#btnCreateAccount", isDisabled());
    }
}
