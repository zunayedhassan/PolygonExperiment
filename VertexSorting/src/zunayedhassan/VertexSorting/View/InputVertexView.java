package zunayedhassan.VertexSorting.View;

import java.util.Random;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import zunayedhassan.VertexSorting.Model.Vertex;

/**
 *
 * @author Zunayed Hassan
 */
public class InputVertexView extends StackPane {
    public static final int RADIUS = 15;
    
    public Vertex Point = null;
    
    private Random _random = new Random();
    
    public InputVertexView(double x, double y) {
        this.Point = new Vertex(x, y);
        
        this.getChildren().addAll(
                new Circle(this.Point.X, this.Point.Y, RADIUS, this._getRandomColor()),
                new Text(Integer.toString(this.Point.Id))
        );
    }
    
    private int _getRandomInt() {
        return (this._random.nextInt((255 - 0) + 1) + 0);
    }
    
    private Color _getRandomColor() {
        return Color.rgb(this._getRandomInt(), this._getRandomInt(), this._getRandomInt());
    }
}
