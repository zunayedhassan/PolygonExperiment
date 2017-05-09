package polygon_test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Polygon_test extends Application {
    
    @Override
    public void start(Stage primaryStage) {        
        PolygonTestApplication canvas = new PolygonTestApplication();
        ScrollPane root = new ScrollPane(canvas);
        
        Scene scene = new Scene(root,
                                PolygonTestApplication.WINDOW_WIDTH,
                                PolygonTestApplication.WINDOW_HEIGHT);
        
        primaryStage.setTitle(PolygonTestApplication.WINDOW_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
