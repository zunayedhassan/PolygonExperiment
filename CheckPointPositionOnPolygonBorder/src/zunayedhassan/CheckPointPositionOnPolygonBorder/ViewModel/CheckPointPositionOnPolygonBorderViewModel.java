package zunayedhassan.CheckPointPositionOnPolygonBorder.ViewModel;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import zunayedhassan.CheckPointPositionOnPolygonBorder.Model.PolygonBorderPointsFinder;
import zunayedhassan.CheckPointPositionOnPolygonBorder.View.CheckPointPositionOnPolygonBorderView;

/**
 *
 * @author Zunayed Hassan
 */
public class CheckPointPositionOnPolygonBorderViewModel {
    private CheckPointPositionOnPolygonBorderView _view    = null;
    private Polyline                              _preview = null;
    
    public CheckPointPositionOnPolygonBorderViewModel(CheckPointPositionOnPolygonBorderView view) {
        this._view = view;
        
        this._initialize();
        this._initialzieEvents();
    }
    
    private void _initialize() {
        this._draw();
    }
    
    private void _initialzieEvents() {
        this._view.GetInputCanvas().setOnMouseMoved(this._onMouseMoveOnCanvas);
    }
    
    private void _draw() {
        double[] points = new double[] { 100, 50, 200, 50, 300, 400, 250, 300, 20, 275 };
        
        Polygon polygon = new Polygon(points);
        this.SetColor(polygon, Color.AQUAMARINE, 1);
        this.AddToCanvas(polygon);
        
        this._preview = new Polyline(points);
        this._preview.getPoints().addAll(points[0], points[1]);
        this.SetColor(this._preview, Color.TRANSPARENT, 3);
        this.AddToCanvas(this._preview);
    }
    
    public void AddToCanvas(Node node) {
        this._view.GetInputCanvas().getChildren().add(node);
    }
    
    public Shape SetColor(Shape shape, Color color, double strokeWidth) {
        shape.setFill(color);
        shape.setStroke(Color.BLACK);
        shape.setStrokeWidth(strokeWidth);
        
        return shape;
    }
    
    public void SetResult(String text) {
        this._view.GetResultLabel().setText(text);
    }
    
    private String _getFormattedResult(double x, double y) {
        return ("Mouse Position: (" + x + ", " + y + ")");
    }
    
    private void _setAppearance(boolean isThePointWithinThePolygonLine) {
        if (isThePointWithinThePolygonLine) {
            this._preview.setStroke(Color.RED);
        }
        else {
            this._preview.setStroke(Color.BLACK);
        }
    }
    
    private Polygon _getPolygon() {
        return ((Polygon) this._view.GetInputCanvas().getChildren().get(0));
    }
    
    private EventHandler<MouseEvent> _onMouseMoveOnCanvas = new EventHandler<MouseEvent>() {
        double x = 0;
        double y = 0;
        String result = "";
        PolygonBorderPointsFinder polygonBorderPointsFinder = new PolygonBorderPointsFinder();
        
        @Override
        public void handle(MouseEvent event) {
            x = event.getX();
            y = event.getY();
            
            result = _getFormattedResult(x, y);
            SetResult(result);
            
            boolean result = polygonBorderPointsFinder.IsThePointWithinThePolygonLine(_getPolygon(), x, y);
            _setAppearance(result);
        }
    };
}
