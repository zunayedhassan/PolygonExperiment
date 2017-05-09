package zunayedhassan.LineIntersection;

/**
 *
 * @author Zunayed Hassan
 */
public class LineIntersection {
    protected double x1 = 0;
    protected double y1 = 0;
    protected double x2 = 0;
    protected double y2 = 0;
    protected double x3 = 0;
    protected double y3 = 0;
    protected double x4 = 0;
    protected double y4 = 0;
    
    public LineIntersection(double x1, double y1,
                              double x2, double y2,
                              double x3, double y3,
                              double x4, double y4) {
        
        this.x1 = x1;
        this.y1 = y1;
        
        this.x2 = x2;
        this.y2 = y2;
        
        this.x3 = x3;
        this.y3 = y3;
        
        this.x4 = x4;
        this.y4 = y4;
    }
    
    public Result GetLineIntersectedPoint() {
        Result result = null;
        
        double m1 = (this.y2 - this.y1) / (this.x2 - this.x1);
        double b1 = this.y1 - (m1 * this.x1);
        
        double m2 = (this.y4 - this.y3) / (this.x4 - this.x3);
        double b2 = this.y3 - (m2 * this.x3);
        
        double x  = (b2 - b1) / (m1 - m2);
        double y  = (m1 * x) + b1;
        
        result = new Result(x, y, x1, y1, x2, y2, x3, y3, x4, y4);
        System.out.println(result.Comment);
        
        return result;
    }
}
