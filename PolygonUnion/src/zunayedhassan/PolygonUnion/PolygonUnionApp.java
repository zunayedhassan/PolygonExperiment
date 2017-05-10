package zunayedhassan.PolygonUnion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zunayedhassan.PolygonUnion.View.PolygonUnionView;

/**
 *
 * @author Zunayed Hassan
 */
public class PolygonUnionApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        PolygonUnionView root = new PolygonUnionView();
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("Polygon Union");
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
