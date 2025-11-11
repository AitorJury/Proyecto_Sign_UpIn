/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudbankclientsideapplication.ui;

import crudbankclientsideapplication.CRUDBankClientSideApplication;
import javafx.stage.Stage;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author aitor & cynthia
 */
public class MainControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new CRUDBankClientSideApplication().start(stage);
    }
    // Los siguientes test funcionan si el correo está insertado en la base

    @Test
    public void test1_BtnExit() {
        clickOn("#txtEmail");
        write("aitor.jr04@gmail.com");
        clickOn("#txtPassword");
        write("abcd*1234");
        clickOn("#btnSignIn");

        verifyThat("#btnExit", isEnabled());
        clickOn("#btnExit");
        clickOn("No");
        verifyThat("#btnExit", isVisible());
        clickOn("#btnExit");
        clickOn("Yes");
    }

    @Test
    public void test2_BtnLogOut() {
        clickOn("#txtEmail");
        write("aitor.jr04@gmail.com");
        clickOn("#txtPassword");
        write("abcd*1234");
        clickOn("#btnSignIn");

        verifyThat("#btnLogout", isEnabled());
        clickOn("#btnLogout");
        clickOn("No");
        verifyThat("#btnLogout", isVisible());
        clickOn("#btnLogout");
        clickOn("Yes");

        verifyThat("#txtEmail", isVisible());
    }
}
