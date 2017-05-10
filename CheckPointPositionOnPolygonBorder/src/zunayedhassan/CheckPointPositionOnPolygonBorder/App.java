package zunayedhassan.CheckPointPositionOnPolygonBorder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import zunayedhassan.CheckPointPositionOnPolygonBorder.View.CheckPointPositionOnPolygonBorderView;

/**
 *
 * @author Zunayed Hassan
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        CheckPointPositionOnPolygonBorderView root = new CheckPointPositionOnPolygonBorderView();
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("Check Point Position On Polygon Border View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
