/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudbankclientsideapplication.ui;

import crudbankclientsideapplication.CRUDBankClientSideApplication;
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
    public void test3_ButtonEnable(){
        clickOn("#txtEmail");
        write("awallace@gmail.com");
        clickOn("#txtPassword");
        write("qwerty*9876");
        clickOn("#btnSignIn");
        verifyThat("#btnLogout", isVisible());
    } 
    
}
