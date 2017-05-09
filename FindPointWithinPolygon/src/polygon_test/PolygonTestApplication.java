package polygon_test;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PolygonTestApplication extends Pane {
    public static final String WINDOW_TITLE = "Polygon Point Experiment";
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    
    public Point2D TestPoint = new Point2D(150, 600);
    public double[] PointsOfThePolygon = new double[] { 57,680.36216, 131,690.36216, 68,599.36216, 159,635.36216, 200,698.36216, 263,613.36216, 131,508.36216, 108,568.36216, 72,567.36216, 71,511.36216 };
    
    private double _testPointRadius = 3;
    
    public PolygonTestApplication() {
        super.setPrefSize(1600, 900);
        
        Polygon polygon = new Polygon(this.PointsOfThePolygon);
        polygon.setStroke(Color.GREEN);
        polygon.setStrokeWidth(1.0);
        polygon.setFill(null);
        
        Circle previewOfTestPoint = new Circle(this.TestPoint.getX(),
                                               this.TestPoint.getY(),
                                               this._testPointRadius,
                                               Color.ORANGE);
        
        super.getChildren().add(polygon);
        super.getChildren().add(previewOfTestPoint);
        
        
        final int totalNumberOfVertices = polygon.getPoints().size() / 2;

        double[] vertX = new double[totalNumberOfVertices];
        double[] vertY = new double[totalNumberOfVertices];
        
        for (int i = 0, j = 0; i < polygon.getPoints().size(); i += 2, j++) {
            vertX[j] = polygon.getPoints().get(i);
        }
        
        for (int i = 1, j = 0; i < polygon.getPoints().size(); i += 2, j++) {
            vertY[j] = polygon.getPoints().get(i);
        }
        
        boolean isWithinPolygon = this.IsWithinPolygon(vertX.length, vertX, vertY, this.TestPoint.getX(), this.TestPoint.getY());
        
        System.out.println(isWithinPolygon);
        
        Alert alert = null;
        
        if (isWithinPolygon) {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("The given point (" + this.TestPoint.getX() + ", " + this.TestPoint.getY() + ") is within the polygon");
        }
        else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText("Failure");
            alert.setContentText("The given point (" + this.TestPoint.getX() + ", " + this.TestPoint.getY() + ") is not within the polygon");
        }
        
        alert.show();
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
