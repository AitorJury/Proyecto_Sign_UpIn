package crudbankclientsideapplication;

import crudbankclientsideapplication.ui.SignInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @authors Aitor & Cynthia.
 * 
 */
public class CRUDBankClientSideApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/SignIn.fxml"));
        Parent root = (Parent)loader.load();
        
         SignInController controller = loader.getController();
         controller.initStage(stage, root);
        
    }

    /**
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
