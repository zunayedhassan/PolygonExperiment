package zunayedhassan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Zunayed Hassan
 */
public class LineIntersectionApp extends Application {
    public static final double WINDOWS_WIDTH  = 800;
    public static final double WINDOWS_HEIGHT = 600;
    public static final String WINDOWS_TITLE  = "Line Intersection Experiment";
    
    @Override
    public void start(Stage primaryStage) {
        LineIntersectionPane root = new LineIntersectionPane();
        Scene scene = new Scene(root, WINDOWS_WIDTH, WINDOWS_HEIGHT);
        
        primaryStage.setTitle(WINDOWS_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
