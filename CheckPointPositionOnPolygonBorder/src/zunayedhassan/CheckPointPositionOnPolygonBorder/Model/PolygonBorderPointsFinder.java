package zunayedhassan.CheckPointPositionOnPolygonBorder.Model;

import java.util.ArrayList;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;

/**
 *
 * @author Zunayed Hassan
 */
public class PolygonBorderPointsFinder {
    private EdgeFinderHelper _edgeFinderHelper = new EdgeFinderHelper();
    
    public boolean IsThePointWithinThePolygonLine(Polygon polygon, double x, double y) {
        boolean isThePointWithinThePolygonLine = false;
        
        // TODO:...
        ArrayList<Vertex> points = new ArrayList<>();
        
        for (int i = 0; i < polygon.getPoints().size(); i += 2) {
            Vertex point = new Vertex(polygon.getPoints().get(i), polygon.getPoints().get(i + 1));
            points.add(point);
        }
        
        ArrayList<Edge> edges = this._edgeFinderHelper.GetEdgesFromPolygon(points);
        
        for (Edge edge : edges) {
            if (this._isThePointOnTheLine(edge.Vertices[0].X, edge.Vertices[0].Y, edge.Vertices[1].X, edge.Vertices[1].Y, x, y)) {
                isThePointWithinThePolygonLine = true;
                break;
            }
        }
        
        return isThePointWithinThePolygonLine;
    }
    
    private boolean _isThePointOnTheLine(double x1, double y1, double x2, double y2, double testPointX, double testPointY) {
        boolean isThePointOnTheLine = false;
        
        double m = (y2 - y1) / (x2 - x1);
        double c = y1 - (m * x1);
        
        double resultY = (m * testPointX) + c;
        
        if (((testPointX >= Math.min(x1, x2)) && (testPointX <= Math.max(x1, x2))) &&
            ((testPointY >= Math.min(y1, y2)) && (testPointY <= Math.max(y1, y2)))) {
            
            if ((testPointY >= (resultY - 3)) && (testPointY <= (resultY + 3))) {
                isThePointOnTheLine = true;
            }
        }
        
        return isThePointOnTheLine;
    }
}
