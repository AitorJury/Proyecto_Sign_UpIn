/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudbankclientsideapplication.ui;

import crudbankclientsideapplication.CRUDBankClientSideApplication;
import javafx.stage.Stage;
import javax.ws.rs.NotAuthorizedException;
import org.junit.Test;
import static org.junit.Assert.*;
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
 * @author cynthia
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SignInControllerTest extends ApplicationTest {
    
    @Override
    public void start(Stage stage) throws Exception{
        new CRUDBankClientSideApplication().start(stage);
        
    }
    
    @Test
    public void test1_InitialState() {
        verifyThat("#btnSignIn", isDisabled());
        verifyThat("#txtEmail",hasText(""));
        verifyThat("#txtPassword",hasText(""));
    } 
    
    @Test
    public void test2_txtEmailIsFocused() {
        verifyThat("#txtEmail", isFocused());
        
    } 
    
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
    @Test
    public void test4_NotAuthorizedExceptionTest(){
        clickOn("#txtEmail");
        write("awallace@gmail.com");
        clickOn("#txtPassword");
        write("qwerty");
        clickOn("#btnSignIn");
        
        verifyThat( "Okay", isVisible());
         clickOn("Okay");
    }
    
    @Test
    public void test5_LinkSignIn(){
        clickOn("#linkSignUp");
        verifyThat("#btnCreateAccount", isVisible());
    }
    @Test 
    public void test6_ExitButton(){
        clickOn("#btnExit");
        verifyThat("Yes", isVisible());
        clickOn("Yes");
    }
    
    @Test
    public void test7_SignIn(){
        clickOn("#txtEmail");
        write("awallace@gmail.com");
        clickOn("#txtPassword");
        write("qwerty*9876");
        clickOn("#btnSignIn");
        verifyThat("#btnLogout", isVisible());
    } 
    
}
