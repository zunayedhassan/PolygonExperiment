package zunayedhassan.PolygonUnion.Model;

/**
 *
 * @author Zunayed Hassan
 */
public class Edge {
    public Vertex[] Vertices = new Vertex[2];
    
    public Edge(Vertex vertex1, Vertex vertex2) {
        this.Vertices[0] = vertex1;
        this.Vertices[1] = vertex2;
    }
    
    public Edge(double x1, double y1, double x2, double y2) {
        this(new Vertex(x1, y1), new Vertex(x2, y2));
    }
    
    public boolean IsVertexExists(Vertex testVertex) {
        boolean isVertexExists = false;
        
        for (Vertex currentVertex : this.Vertices) {
            if ((currentVertex.X.doubleValue() == testVertex.X.doubleValue()) && (currentVertex.Y.doubleValue() == testVertex.Y.doubleValue())) {
                isVertexExists = true;
                break;
            }
        }
        
        return isVertexExists;
    }
    
    public Vertex GetTheOtherVertex(Vertex vertex) {
        if (this.IsVertexExists(vertex)) {
            for (int i = 0; i < 2; i++) {
                Vertex currentVertex = this.Vertices[i];
                
                if ((currentVertex.X.doubleValue() == vertex.X.doubleValue()) && (currentVertex.Y.doubleValue() == vertex.Y.doubleValue())) {
                    int j = 0;
                    
                    if (i == 0) {
                        j = 1;
                    }
                    else {
                        j = 0;
                    }
                    
                    return this.Vertices[j];
                }
            }
        }
        
        return null;
    } 
    
    @Override
    public String toString() {
        if (this.Vertices.length == 2) {
            return (this.Vertices[0] + ", " + this.Vertices[1]);
        }
        
        return "null";
    }
}
