package crudbankclientsideapplication.ui;

import crudbankclientsideapplication.CRUDBankClientSideApplication;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
/**
 *
 * @author Cynthia.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SignInControllerTest extends ApplicationTest {
            
    @Override
    public void start(Stage stage) throws Exception{
        new CRUDBankClientSideApplication().start(stage);
    }

    /**
     * Test que verifica el estado inicial.
     */
    @Test
    public void test1_InitialState() {
        verifyThat("#btnSignIn", isDisabled());
        verifyThat("#txtEmail",hasText(""));
        verifyThat("#txtPassword",hasText(""));
    } 
    
    /**
     * Test que verifica el foco inicial en Email.
     */
    @Test
    public void test2_txtEmailIsFocused() {
        verifyThat("#txtEmail", isFocused());
    } 
    
    /**
     * Test que verifica que el botón SignIn se habilita.
     */
    @Test
    public void tes3_ButtonEnable(){
        clickOn("#txtEmail");
        write("hello");
        verifyThat("#btnSignIn", isDisabled());
        clickOn("#txtEmail");
        eraseText(5);
        clickOn("#txtPassword");
        write("hello");
        verifyThat("#btnSignIn", isDisabled());
        clickOn("#txtEmail");
        write("hello");
        verifyThat("#btnSignIn", isEnabled());
    }
    
    /**
     * Test que verifica que el email no exista en la base.
     */
    @Test
    public void test4_NotAuthorizedExceptionTest(){
        clickOn("#txtEmail");
        write("awallace@gmail.com");
        clickOn("#txtPassword");
        write("qwerty");
        clickOn("#btnSignIn");
        
        verifyThat( "OK", isVisible());
         clickOn("OK");
    }
    
    /**
     * Test que verifica que el link envíe a la ventana Sign Up.
     */
    @Test
    public void test5_LinkSignUp(){
        clickOn("#linkSignUp");
        verifyThat("#btnCreateAccount", isVisible());
    }
    
    /**
     * Test que verifique que el botón Exit funciona.
     */
    @Test 
    public void test6_ExitButton(){
        clickOn("#btnExit");
        verifyThat("Yes", isVisible());
        clickOn("Yes");
    }
    
    /**
     * Test que verifica que el botón SignIn accede a la ventana Main.
     */
    @Test
    public void test7_SignIn(){
        clickOn("#txtEmail");
        write("awallace@gmail.com");
        clickOn("#txtPassword");
        write("qwerty*9876");
        clickOn("#btnSignIn");
        verifyThat("#btnLogout", isVisible());
    } 
 
    /**
     * Test que verifica que no hay conexión con el servidor.
     * El siguiente test solo funciona si el servidor está desconectado.
     */
    /*
    @Test
    public void test8_NoServer() {
        clickOn("#txtEmail");
        write("awallace@gmail.com");
        clickOn("#txtPassword");
        write("qwerty*9876");
        clickOn("#btnSignIn");
    
        verifyThat("It cannot connect to the server.", isVisible());
        clickOn("OK");
    }
    */
}
