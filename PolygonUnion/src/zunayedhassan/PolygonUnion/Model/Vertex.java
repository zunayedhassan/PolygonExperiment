package zunayedhassan.PolygonUnion.Model;

/**
 *
 * @author Zunayed Hassan
 */
public class Vertex {
    public static int ID = 0;
    
    public Double  X  = null;
    public Double  Y  = null;
    public Integer Id = null;
    
    public Vertex(double x, double y) {
        this.Id = ++ID;
        
        this.X = x;
        this.Y = y;
    }
    
    public boolean IsEqual(Vertex vertex) {
        if ((vertex.X.doubleValue() == this.X.doubleValue()) && (vertex.Y.doubleValue() == this.Y.doubleValue())) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return ("Vertex[id=" + this.Id + " x=" + this.X + " " + this.Y + "]");
    }
}
