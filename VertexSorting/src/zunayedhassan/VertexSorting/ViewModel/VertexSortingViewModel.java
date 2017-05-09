package zunayedhassan.VertexSorting.ViewModel;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import zunayedhassan.VertexSorting.Model.Vertex;
import zunayedhassan.VertexSorting.Model.VertexSortingHelper;
import zunayedhassan.VertexSorting.View.InputVertexView;
import zunayedhassan.VertexSorting.View.VertexSortingView;

/**
 *
 * @author Zunayed Hassan
 */
public class VertexSortingViewModel {
    private VertexSortingView   _view                   = null;
    private VertexSortingHelper _verVertexSortingHelper = new VertexSortingHelper();
    
    public VertexSortingViewModel(VertexSortingView view) {
        this._view =  view;
        
        this._view.GetInputCanvas().setOnMouseClicked(this._onClickedOnInputCanvas);
        this._view.ClearButton.setOnAction(this._onClearButtonClicked);
        this._view.SortButton.setOnAction(this._onSortVerticesButtonClicked);
    }
    
    private EventHandler<MouseEvent> _onClickedOnInputCanvas = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            double x = event.getX();
            double y = event.getY();
            
            InputVertexView vertex = new InputVertexView(x, y);
            AddToInputCanvas(vertex);
        }
    };
    
    private EventHandler<ActionEvent> _onClearButtonClicked = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            ClearCanvas();
        }
    };
    
    private EventHandler<ActionEvent> _onSortVerticesButtonClicked = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            ArrayList<Vertex> inputVertices = new ArrayList<>();
            
            for (Node node : _view.GetInputCanvas().getChildren()) {
                if (node instanceof InputVertexView) {
                    InputVertexView vertexView = (InputVertexView) node;
                    inputVertices.add(vertexView.Point);
                }
            }
            
            ArrayList<Double> points = _verVertexSortingHelper.GetSortedVertices(inputVertices);
            DrawResult(points);
        }
    };
    
    public void AddToInputCanvas(InputVertexView vertex) {
        this._view.GetInputCanvas().getChildren().add(vertex);
        
        vertex.setTranslateX(vertex.Point.X - InputVertexView.RADIUS);
        vertex.setTranslateY(vertex.Point.Y - InputVertexView.RADIUS);
    }
    
    public void AddToOutputCanvas(Polygon shape) {
        this._view.GetOutputCanvas().getChildren().add(shape);
    }
    
    public void ClearCanvas() {
        this._view.GetInputCanvas().getChildren().clear();
        this._view.GetOutputCanvas().getChildren().clear();
    }
    
    public void DrawResult(ArrayList<Double> points) {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(points);
        
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(1);
        polygon.setFill(Color.TRANSPARENT);
        
        this.AddToOutputCanvas(polygon);
    }
}
