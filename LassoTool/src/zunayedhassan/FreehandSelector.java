/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zunayedhassan;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Zunayed Hassan
 */
public class FreehandSelector {
    public Polygon FreehandSelectionPreview = new Polygon();
    public Pane Canvas = null;
    public Alert OutputAsAlert = new Alert(Alert.AlertType.INFORMATION);
    
    public FreehandSelector(Pane parent) {
        this.Canvas = parent;
        
        this.FreehandSelectionPreview.setId("free-hand-selection");
        this.FreehandSelectionPreview.getStrokeDashArray().add(2d);
        
        this.Canvas.getChildren().add(this.FreehandSelectionPreview);
        
        this.Canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FreehandSelectionPreview.getPoints().clear();
                
                FreehandSelectionPreview.getPoints().add(event.getX());
                FreehandSelectionPreview.getPoints().add(event.getY());
                
                if (!Canvas.getChildren().contains(FreehandSelectionPreview)) {
                    Canvas.getChildren().add(FreehandSelectionPreview);
                }
            }
        });
        
        this.Canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FreehandSelectionPreview.getPoints().add(event.getX());
                FreehandSelectionPreview.getPoints().add(event.getY());
            }
        });
        
        this.Canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Canvas.getChildren().contains(FreehandSelectionPreview)) {
                    Canvas.getChildren().remove(FreehandSelectionPreview);
                }
                
                String results = "";

                for (Node node : Canvas.getChildren()) {
                    if (node instanceof ShapeObject) {
                        ShapeObject shape = (ShapeObject) node;
                        
                        int numberOfTotalVertices = FreehandSelectionPreview.getPoints().size() / 2;

                        double[] vertX = new double[numberOfTotalVertices];
                        
                        for (int i = 0,
                                 j = 0;
                                
                                 i < FreehandSelectionPreview.getPoints().size() - 1;
                                 
                                 i += 2,
                                 j++) {
                            
                            vertX[j] = FreehandSelectionPreview.getPoints().get(i);
                        
                        }
                        
                        double[] vertY = new double[numberOfTotalVertices];
                        
                        for (int i = 1,
                                 j = 0;
                                
                                 i < FreehandSelectionPreview.getPoints().size();
                                 
                                 i += 2,
                                 j++) {
                            
                            vertY[j] = FreehandSelectionPreview.getPoints().get(i);
                        
                        }
                        
                        double[][] testPoints = shape.GetPoints();
                        
                        boolean isNodeWithinFreehandSelection = true;
                        
                        for (int i = 0; i < testPoints.length; i++) {
                            double testX = testPoints[i][0];
                            double testY = testPoints[i][1];
                            
                            boolean isWithinFreehandSelection = IsWithinPolygon(numberOfTotalVertices, vertX, vertY, testX, testY);
                            
                            isNodeWithinFreehandSelection &= isWithinFreehandSelection;
                        }
                        
                        if (isNodeWithinFreehandSelection) {
                            results += shape.toString() + ", ";
                        }
                    }
                }
                
                // Show output
                if (results.trim().equals("")) {
                    OutputAsAlert.setContentText("Nothing selected");
                }
                else {
                    OutputAsAlert.setContentText(results + " objects selected");
                }
                
                OutputAsAlert.showAndWait();
            }
        });
    }
    
    /*
     * Source: PNPOLY - Point Inclusion in Polygon Test W. Randolph Franklin (WRF) 
     * URL: https://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
     */
    public boolean IsWithinPolygon(int numberOfTotalVertices, double[] vertX, double[] vertY, double testX, double testY) {
        boolean isWithinPolygon = false;
        
        for (int i = 0,
                j = numberOfTotalVertices - 1;
                
                i < numberOfTotalVertices;
                 
                j = i++) {
            
            if ( ((vertY[i] > testY) != (vertY[j] > testY)) && (testX < (vertX[j] - vertX[i]) * (testY - vertY[i]) / (vertY[j] - vertY[i]) + vertX[i]) ) {
                isWithinPolygon = !isWithinPolygon;
            }
        }
      
        return isWithinPolygon;
    }
}
