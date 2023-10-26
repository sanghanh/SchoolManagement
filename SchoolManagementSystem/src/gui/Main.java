package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.PageTitle;

public class Main extends Application {
    
    public static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        LoginPageController loginPage = new LoginPageController();
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle(PageTitle.LOGIN_PAGE);
        stage.show();
    }
    
    public static void closeCurrentStage() {
        if(stage != null) {
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
