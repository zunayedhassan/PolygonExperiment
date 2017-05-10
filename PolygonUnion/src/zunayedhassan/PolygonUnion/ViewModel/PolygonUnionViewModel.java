package zunayedhassan.PolygonUnion.ViewModel;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import zunayedhassan.PolygonUnion.Model.PolygonUnionHelper;
import zunayedhassan.PolygonUnion.View.PolygonUnionView;

/**
 *
 * @author Zunayed Hassan
 */
public class PolygonUnionViewModel {
    private PolygonUnionView   _view               = null;
    private PolygonUnionHelper _polygonUnionHelper = new PolygonUnionHelper();
    
    public PolygonUnionViewModel(PolygonUnionView view) {
        this._view = view;
        
        this._initialize();
        this._initializeEvents();
    }
    
    private void _initialize() {
        this._drawInputData();
    }
    
    private void _initializeEvents() {
        this._view.ClearButton.setOnAction(this._onClearButtonClicked);
        this._view.ApplyButton.setOnAction(this._onApplyButtonClicked);
    }
    
    private void _drawInputData() {
        this._drawPolygon(new double[] {  50,  50, 150, 50, 100, 150 }, Color.BISQUE      );
        this._drawPolygon(new double[] {  1, 25, 100, 60, 4, 75      }, Color.DARKSEAGREEN);
    }
    
    private void _drawPolygon(double[] points, Color color) {
        Polygon polygon = new Polygon(points);        
        this._setColorToPolygon(polygon, color);
        this.AddToInputCanvas(polygon);
        
        polygon.setOpacity(0.5);
    }
    
    private void _setColorToPolygon(Polygon polygon, Color fillColor) {
        polygon.setFill(fillColor);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(1);
    }
    
    public void AddToInputCanvas(Node node) {
        this._view.GetInputCanvas().getChildren().add(node);
    }
    
    public void AddToOutputCanvas(Node node) {
        this._view.GetOutputCanvas().getChildren().add(node);
    }
    
    public void ClearCanvas() {
        this._view.GetOutputCanvas().getChildren().clear();
    }
    
    public void DrawOutputData(ArrayList<Double> points) {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(points);
        
        this._setColorToPolygon(polygon, Color.TRANSPARENT);
        this.AddToOutputCanvas(polygon);
    }
    
    private EventHandler<ActionEvent> _onClearButtonClicked = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            ClearCanvas();
        }
    };
    
    private EventHandler<ActionEvent> _onApplyButtonClicked = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            // Gather input data
            ArrayList<Polygon> polygons = new ArrayList<>();
            
            for (Node node : _view.GetInputCanvas().getChildren()) {
                if (node instanceof Polygon) {
                    Polygon polygon = (Polygon) node;
                    polygons.add(polygon);
                }
            }
            
            // Calculate and draw
            ArrayList<Double> points = _polygonUnionHelper.GetUnifiedPolygonFromPolygons(polygons);
            DrawOutputData(points);
        }
    };
}
