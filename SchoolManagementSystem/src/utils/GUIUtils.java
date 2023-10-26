package utils;

import gui.Main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUIUtils {

    public static Stage openNewWindow(Parent parent, String title, Parent oldParent, String oldTitle) {
        Main.closeCurrentStage();
        Stage stage = new Stage();
        Main.stage = stage;
        if (oldParent != null && oldTitle != null) {
            stage.setOnCloseRequest(evt -> {
                try {
                    handler(evt, oldTitle, oldParent);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        stage.setScene(new Scene(parent));
        stage.setTitle(title);
        stage.show();
        return stage;
    }

    public static void openFXMLInCurrentWindow(AnchorPane pane, Parent parent) {
        pane.getChildren().clear();
        pane.getChildren().setAll(pane);
    }

    public static void handler(WindowEvent evt, String title, Parent parent) throws IOException {
        EventType<WindowEvent> window = evt.getEventType();
        if (window == WindowEvent.WINDOW_CLOSE_REQUEST) {
            Parent queueParent = Transferer.getInstance().getLastScene();
            String queueTitle = Transferer.getInstance().getLastTitle();
            GUIUtils.openNewWindow(parent, title, queueParent, queueTitle);
        }
    }

    public static void showFXPopup(String message, String title, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText("");
        alert.showAndWait();
    }
}
