package zunayedhassan.PolygonUnion.Model;

import java.util.ArrayList;

/**
 *
 * @author Zunayed Hassan
 */
public class VertexSortingHelper {
    public ArrayList<Double> GetSortedVertices(ArrayList<Vertex> vertices) {
        ArrayList<Double> points = new ArrayList<>();
        
        // Find the center point
        double centerX = 0;
        double centerY = 0;
        
        for (Vertex vertex : vertices) {
            centerX += vertex.X;
            centerY += vertex.Y;
        }
        
        centerX /= vertices.size();
        centerY /= vertices.size();
        
        // Find angle between center point and current point
        double[] angles        = new double[vertices.size()];
        int[]    verticesIndex = new int[vertices.size()];
        
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            double x      = vertex.X;
            double y      = vertex.Y;
            
            double anlgeFromCenter = Math.atan2((y - centerY), (x - centerX));
            angles[i]        = anlgeFromCenter;
            verticesIndex[i] = i;
        }
        
        // Sort
        for (int i = 0; i < angles.length; i++) {
            for (int j = 0; j < angles.length; j++) {
                if (angles[i] > angles[j]) {
                    double angleTemp  = angles[i];
                    angles[i]         = angles[j];
                    angles[j]         = angleTemp;

                    int indexTemp    = verticesIndex[i];
                    verticesIndex[i] = verticesIndex[j];
                    verticesIndex[j] = indexTemp;
                }
            }
        }
        
        for (int i = 0; i < verticesIndex.length; i++) {
            double x = vertices.get(verticesIndex[i]).X;
            double y = vertices.get(verticesIndex[i]).Y;

            points.add(x);
            points.add(y);
        }
        
        return points;
    }
}
