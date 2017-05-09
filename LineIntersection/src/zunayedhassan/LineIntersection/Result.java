package zunayedhassan.LineIntersection;

/**
 *
 * @author Zunayed Hassan
 */
public class Result {
    public static enum COMMENT {
        PARALLEL,
        WITHIN_THE_LINE,
        OUTSIDE_OF_THE_LINE
    }
    
    public Double  X        = new Double(0);
    public Double  Y        = new Double(0);
    public COMMENT Comment  = null;
    
    public Result(Double x, Double y, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        this.X = x;
        this.Y = y;
        
        if (this.X.equals(Double.NaN) || this.Y.equals(Double.NaN)) {
            this.Comment = COMMENT.PARALLEL;
        }
        else if (this._inRange(x3, y3, x4, y4, x, y))  {
            
            this.Comment = COMMENT.WITHIN_THE_LINE;
            
        }
        else {
            this.Comment = COMMENT.OUTSIDE_OF_THE_LINE;
        }
    }
    
    private static boolean _inRange(double start_x, double start_y, double end_x, double end_y, double point_x, double point_y) {
        double dx = end_x - start_x;
        double dy = end_y - start_y;
        double innerProduct = (point_x - start_x) * dx + (point_y - start_y) * dy;
        return (0 <= innerProduct && innerProduct <= dx * dx + dy * dy);
    }
}
