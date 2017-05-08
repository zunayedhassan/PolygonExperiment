package zunayedhassan.PolygonConverterExperiment.View;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.VLineTo;
import zunayedhassan.PolygonConverterExperiment.ViewModel.PolygonConverterViewModel;

/**
 *
 * @author Zunayed Hassan
 */
public class PolygonConverterView extends BorderPane {
    public final Pane InputCanvas  = new Pane();
    public final Pane OutputCanvas = new Pane();
    
    private PolygonConverterViewModel _polygonConverterViewModel = null;
    
    public  final Button ConvertButton = new Button("Convert");
    
    public PolygonConverterView() {
        this._initializeVariables();
        this._initializeLayout();
        this._initialize();
        this._bindEvents();
    }
    
    private void _initializeVariables() {
        
    }
    
    private void _initializeLayout() {
        ToolBar toolbar = new ToolBar(this.ConvertButton);
        this.setTop(toolbar);
        
        SplitPane splitPane = new SplitPane();
        this.setCenter(splitPane);
                
        ScrollPane inputScrollPane  = new ScrollPane(this.InputCanvas);
        ScrollPane outputScrollPane = new ScrollPane(this.OutputCanvas);
        
        splitPane.getItems().addAll(inputScrollPane, outputScrollPane);
    }
    
    private void _bindEvents() {
        this._polygonConverterViewModel = new PolygonConverterViewModel(this);
    }
    
    private void _initialize() {
        this._draw();
    }
    
    
    private void _draw() {
        // Various shapes
        Rectangle rectangle = new Rectangle(30, 40, 100, 70);
        this.AddToInputCanvas(rectangle, Color.AQUA);
        
        Circle circle = new Circle(100, 200, 75);
        this.AddToInputCanvas(circle, Color.BISQUE);
        
        Ellipse ellipse = new Ellipse(120, 400, 100, 60);
        this.AddToInputCanvas(ellipse, Color.CADETBLUE);
        
        Rectangle roundedRectangle = new Rectangle(200, 500, 130, 70);
        roundedRectangle.setArcWidth(30);
        roundedRectangle.setArcHeight(80);
        this.AddToInputCanvas(roundedRectangle, Color.CORNFLOWERBLUE);
        
        // Path
        Path path = new Path();
        
        path.getElements().addAll(
                new MoveTo(250, 340),
                new LineTo(300, 400),
                new HLineTo(400),
                new VLineTo(200),
                new QuadCurveTo(350, 240, 650, 340),
                new CubicCurveTo(400, 200, 300, 500, 250, 100),
                new ClosePath()
        );
        
        /*
        ArcTo arcTo = new ArcTo();
        arcTo.setX(600);
        arcTo.setY(500);
        arcTo.setRadiusX(50);
        arcTo.setRadiusY(80);
        path.getElements().add(arcTo);
        */
        
        this.AddToInputCanvas(path, Color.TRANSPARENT);
    }
    
    public void SetStroke(Shape shape) {
        shape.setStroke(Color.BLACK);
        shape.setStrokeWidth(1);
    }
    
    public void SetFill(Shape shape, Color color) {
        shape.setFill(color);
    }
    
    public void AddToInputCanvas(Shape shape, Color color) {
        this.InputCanvas.getChildren().add(shape);
        shape.setFill(color);
        this.SetStroke(shape);
    }
    
    public void AddToOutputCanvas(Shape shape) {
        this.OutputCanvas.getChildren().add(shape);
        this.SetStroke(shape);
    }
}
