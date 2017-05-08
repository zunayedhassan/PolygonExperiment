package zunayedhassan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zunayedhassan.PolygonConverterExperiment.View.PolygonConverterView;

/**
 *
 * @author Zunayed Hassan
 */
public class PolygonConverterApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        PolygonConverterView root = new PolygonConverterView();
        Scene scene = new Scene(root, 1024, 768);
        
        primaryStage.setTitle("Polygon Converter Experiment");
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
