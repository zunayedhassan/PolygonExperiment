package zunayedhassan.VertexSorting;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import zunayedhassan.VertexSorting.View.VertexSortingView;

/**
 *
 * @author Zunayed Hassan
 */
public class VertexSortingApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VertexSortingView root = new VertexSortingView();
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("Vertex Sorting Experiment");
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
