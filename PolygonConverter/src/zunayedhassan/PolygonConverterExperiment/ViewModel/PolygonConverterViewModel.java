package zunayedhassan.PolygonConverterExperiment.ViewModel;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import zunayedhassan.PolygonConverterExperiment.Model.ShapeToPolygonConverter;
import zunayedhassan.PolygonConverterExperiment.View.PolygonConverterView;

/**
 *
 * @author Zunayed Hassan
 */
public class PolygonConverterViewModel {
    private PolygonConverterView    _view                    = null;
    private ShapeToPolygonConverter _shapeToPolygonConverter = new ShapeToPolygonConverter();
    
    public PolygonConverterViewModel(PolygonConverterView view) {
        this._view = view;
        
        // Events
        this._view.ConvertButton.setOnAction(this._onConvertShapes);
    }
    
    private EventHandler<ActionEvent> _onConvertShapes = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (_view != null) {
                for (Node node : _view.InputCanvas.getChildren()) {
                    if (node instanceof Shape) {
                        Shape shape = (Shape) node;
                        ArrayList<Double> points = _shapeToPolygonConverter.GetConvertedPolygonPoints(shape);
                        
                        if (shape instanceof Path) {
                            DrawPolylineFromPoints(points);
                        }
                        else {
                            DrawPolygonFromPoints(points);
                        }
                    }
                }
            }
        }
    };
    
    public void DrawPolylineFromPoints(ArrayList<Double> points) {
        Polyline polyline = new Polyline();
        
        for (double value : points) {
            polyline.getPoints().add(value);
        }
        
        this._view.SetStroke(polyline);
        this._view.AddToOutputCanvas(polyline);
    }
    
    public void DrawPolygonFromPoints(ArrayList<Double> points) {
        Polygon polygon = new Polygon();
        
        for (double value : points) {
            polygon.getPoints().add(value);
        }
        
        this._view.SetStroke(polygon);
        this._view.SetFill(polygon, Color.TRANSPARENT);
        this._view.AddToOutputCanvas(polygon);
    }
}
