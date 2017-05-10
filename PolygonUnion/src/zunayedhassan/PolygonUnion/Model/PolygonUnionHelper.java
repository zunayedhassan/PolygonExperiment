package zunayedhassan.PolygonUnion.Model;

import java.util.ArrayList;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Zunayed Hassan
 */
public class PolygonUnionHelper {
    private VertexSortingHelper _vertexSortingHelper  = new VertexSortingHelper();
    private EdgeFinderHelper    _edgeEdgeFinderHelper = new EdgeFinderHelper();
    
    public ArrayList<Double> GetUnifiedPolygonFromPolygons(ArrayList<Polygon> polygons) {
        ArrayList<Double> output = null;
        
        if (polygons.size() == 1) {
            output = this._getPointsArrayListFromPolygon(polygons.get(0));
        }
        else if (polygons.size() > 1) {
            Polygon previouslyMergedPolygon = null;
            
            for (int i = 0; i < polygons.size(); i++) {
                ArrayList<Double> previouslyMergedPolygonPoints = null;
                
                if (previouslyMergedPolygon == null) {
                    previouslyMergedPolygonPoints = this._merge(polygons.get(i), polygons.get(++i));
                }
                else {
                    previouslyMergedPolygonPoints = this._merge(previouslyMergedPolygon, polygons.get(i));
                }
                
                if (previouslyMergedPolygonPoints != null) {
                    previouslyMergedPolygon = this._getPolygonFromPoints(previouslyMergedPolygonPoints);
                }
            } 
            
            if (previouslyMergedPolygon != null) {
                output = this._getPointsArrayListFromPolygon(previouslyMergedPolygon);
            }
        }
        
        return output;
    }
    
    private ArrayList<Double> _merge(Polygon polygon1, Polygon polygon2) {
        ArrayList<Vertex> vertices1 = this._getUnifiedPolygonFromPolygons(polygon1, polygon2);
        ArrayList<Vertex> vertices2 = this._getUnifiedPolygonFromPolygons(polygon2, polygon1);
        ArrayList<Vertex> vertices  = new ArrayList<>();
        ArrayList<Double> output    = null;
        
        for (Vertex currentVertex : vertices1) {
            vertices.add(new Vertex(currentVertex.X, currentVertex.Y));
        }
        
        for (Vertex currentVertex : vertices2) {
            for (Vertex anotherVertex : vertices1) {
                if (!currentVertex.IsEqual(anotherVertex)) {
                    vertices.add(new Vertex(currentVertex.X, currentVertex.Y));
                }
            }
        }
        
        output = this._vertexSortingHelper.GetSortedVertices(vertices);
        
        return output;
    }
    
    private ArrayList<Vertex> _getUnifiedPolygonFromPolygons(Polygon polygon1, Polygon polygon2) {
        ArrayList<Double> points = new ArrayList<>();
        
        double[]           polygon1VerticesX        = this._getVerticesXListFromPolygon(polygon1);
        double[]           polygon1VerticesY        = this._getVerticesYListFromPolygon(polygon1);
        
        double[]           polygon2VerticesX        = this._getVerticesXListFromPolygon(polygon2);
        double[]           polygon2VerticesY        = this._getVerticesYListFromPolygon(polygon2);
        
        ArrayList<Double>  polygon2OutsideVerticesX = new ArrayList<>();
        ArrayList<Double>  polygon2OutsideVerticesY = new ArrayList<>();
        
        ArrayList<Double>  polygon1InsideVerticesX  = new ArrayList<>();
        ArrayList<Double>  polygon1InsideVerticesY  = new ArrayList<>();
        
        ArrayList<Double>  polygon2InsideVerticesX  = new ArrayList<>();
        ArrayList<Double>  polygon2InsideVerticesY  = new ArrayList<>();
        
        // Check if polygon 2 overlaps upon polygon 1        
        for (int i = 0; i < polygon2.getPoints().size() / 2; i++) {
            double testX = polygon2VerticesX[i];
            double testY = polygon2VerticesY[i];
            
            if (this.IsWithinPolygon(polygon1VerticesX.length, polygon1VerticesX, polygon1VerticesY, testX, testY)) {
                polygon2InsideVerticesX.add(testX);
                polygon2InsideVerticesY.add(testY);
            }
            else {
                polygon2OutsideVerticesX.add(testX);
                polygon2OutsideVerticesY.add(testY);
            }
        }
        
        // Check if polygon 1 overlaps upon polygon 2
        for (int i = 0; i < polygon1.getPoints().size() / 2; i++) {
            double testX = polygon1VerticesX[i];
            double testY = polygon1VerticesY[i];
            
            if (this.IsWithinPolygon(polygon2VerticesX.length, polygon2VerticesX, polygon2VerticesY, testX, testY)) {
                polygon1InsideVerticesX.add(testX);
                polygon1InsideVerticesY.add(testY);
            }
        }
        
        ArrayList<Vertex> unsortedPoints = new ArrayList<>();
        
        for (int i = 0; i < polygon1VerticesX.length; i++) {
            unsortedPoints.add(new Vertex(polygon1VerticesX[i], polygon1VerticesY[i]));
        }
        
        for (int i = 0; i < polygon2OutsideVerticesX.size(); i++) {
            unsortedPoints.add(new Vertex(polygon2OutsideVerticesX.get(i), polygon2OutsideVerticesY.get(i)));
        } 
        
        ArrayList<Vertex> polygon1Vertices = this._getVerticesFromPolygon(polygon1);
        ArrayList<Vertex> polygon2Vertices = this._getVerticesFromPolygon(polygon2);
        
        ArrayList<Edge> polygon1Edges = this._edgeEdgeFinderHelper.GetEdgesFromPolygon(polygon1Vertices);
        ArrayList<Edge> polygon2Edges = this._edgeEdgeFinderHelper.GetEdgesFromPolygon(polygon2Vertices);
        
        ArrayList<ArrayList<Edge>> polygon2EdgesWithAdjacentInsideVertices = new ArrayList<>();
        
        for (int i = 0; i < polygon2InsideVerticesX.size(); i++) {
            double x = polygon2InsideVerticesX.get(i);
            double y = polygon2InsideVerticesY.get(i);
            
            Vertex currentinsiderVertex = new Vertex(x, y);
            
            ArrayList<Edge> adjacentEdgeForCurrentPoint = new ArrayList<>();
            
            for (Edge edge : polygon2Edges) {
                if (edge.IsVertexExists(currentinsiderVertex)) {
                    boolean isOtherVertexAlsoInsider = false;
                    Vertex otherVertex = edge.GetTheOtherVertex(currentinsiderVertex);

                    for (int j = 0; j < polygon2InsideVerticesX.size(); j++) {
                        if ((polygon2InsideVerticesX.get(i).doubleValue() == otherVertex.X.doubleValue()) &&
                            (polygon2InsideVerticesY.get(i).doubleValue() == otherVertex.Y.doubleValue())) {
                            
                            isOtherVertexAlsoInsider = true;
                            break;
                        }
                    }
                    
                    if (!isOtherVertexAlsoInsider) {
                        adjacentEdgeForCurrentPoint.add(edge);
                    }
                }
            }
            
            polygon2EdgesWithAdjacentInsideVertices.add(adjacentEdgeForCurrentPoint);
        }
        
        for (ArrayList<Edge> currentInsiderEdgeList : polygon2EdgesWithAdjacentInsideVertices) {
            for (Edge currentEdge : currentInsiderEdgeList) {
                double x1 = currentEdge.Vertices[0].X;
                double y1 = currentEdge.Vertices[0].Y;
                double x2 = currentEdge.Vertices[1].X;
                double y2 = currentEdge.Vertices[1].Y;
                
                for (Edge otherPolygonEdge : polygon1Edges) {
                    double x3 = otherPolygonEdge.Vertices[0].X;
                    double y3 = otherPolygonEdge.Vertices[0].Y;
                    double x4 = otherPolygonEdge.Vertices[1].X;
                    double y4 = otherPolygonEdge.Vertices[1].Y;
                    
                    LineIntersection lineIntersection = new LineIntersection(x1, y1, x2, y2, x3, y3, x4, y4);
                    LineIntersectionResult lineIntersectionResult = lineIntersection.GetLineIntersectedPoint();
                    
                    if (lineIntersectionResult.Comment == LineIntersectionResult.COMMENT.WITHIN_THE_LINE) {
                        unsortedPoints.add(new Vertex(lineIntersectionResult.X, lineIntersectionResult.Y));
                    }
                }
            }
        }
        
        ArrayList<Vertex> verticesToBeRemoved = new ArrayList<Vertex>();
        
        for (int i = 0; i < unsortedPoints.size(); i++) {
            Vertex vertex = (Vertex) unsortedPoints.get(i);
            
            for (int j = 0; j < polygon1InsideVerticesX.size(); j++) {
                double x = polygon1InsideVerticesX.get(j);
                double y = polygon1InsideVerticesY.get(j);
                
                if ((vertex.X.doubleValue() == x) && (vertex.Y.doubleValue() == y)) {
                    verticesToBeRemoved.add(vertex);
                }
            }
        }
        
        for (Vertex vertex : verticesToBeRemoved) {
            unsortedPoints.remove(vertex);
        }
        
        return unsortedPoints;
    }
    
    private ArrayList<Vertex> _getVerticesFromPolygon(Polygon polygon) {
        ArrayList<Vertex> vertices = new ArrayList<>();
        
        for (int i = 0; i < polygon.getPoints().size(); i += 2) {
            vertices.add(new Vertex(
                    polygon.getPoints().get(i),
                    polygon.getPoints().get(i + 1)
            ));
        }
        
        return vertices;
    }
    
    private double[] _getVerticesXListFromPolygon(Polygon polygon) {
        double[] polygonVertices = new double[polygon.getPoints().size() / 2];
        
        for (int i = 0, j = 0; i < polygon.getPoints().size(); i += 2, j++) {
            polygonVertices[j] = polygon.getPoints().get(i);
        }
        
        return polygonVertices;
    }
    
    private double[] _getVerticesYListFromPolygon(Polygon polygon) {
        double[] polygonVertices = new double[polygon.getPoints().size() / 2];
        
        for (int i = 1, j = 0; i < polygon.getPoints().size(); i += 2, j++) {
            polygonVertices[j] = polygon.getPoints().get(i);
        }
        
        return polygonVertices;
    }
    
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
    
    private ArrayList<Double> _getPointsArrayListFromPolygon(Polygon polygon) {
        ArrayList<Double> points = new ArrayList<>();
        
        for (Double value : polygon.getPoints()) {
            points.add(value);
        }
        
        return points;
    }
    
    private Polygon _getPolygonFromPoints(ArrayList<Double> points) {
        Polygon output = new Polygon();
        
        for (Double value : points) {
            output.getPoints().add(value);
        }
        
        return output;
    }
}
