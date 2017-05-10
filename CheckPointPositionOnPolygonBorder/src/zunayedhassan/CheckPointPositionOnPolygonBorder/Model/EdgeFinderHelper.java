package zunayedhassan.CheckPointPositionOnPolygonBorder.Model;

import java.util.ArrayList;

/**
 *
 * @author Zunayed Hassan
 */
public class EdgeFinderHelper {
    public ArrayList<Edge> GetEdgesFromPolygon(ArrayList<Vertex> points) {
        ArrayList<Edge> edges = new ArrayList<>();
        
        // TODO: Find edges
        for (int i = 0; i < points.size(); i++) {
            if (i == points.size() - 1) {
                edges.add(new Edge(points.get(i), points.get(0)));
            }
            else {
                edges.add(new Edge(points.get(i), points.get(i + 1)));
            }
        }
        
        return edges;
    }
}
