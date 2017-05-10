package zunayedhassan.PolygonUnion.View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import zunayedhassan.PolygonUnion.ViewModel.PolygonUnionViewModel;

/**
 *
 * @author Zunayed Hassan
 */
public class PolygonUnionView extends BorderPane {
    public  final Button  ApplyButton   = new Button("Apply Union");
    public  final Button  ClearButton   = new Button("Clear");
    
    private final ToolBar _toolbar      = new ToolBar(this.ApplyButton, this.ClearButton);
    private final Pane    _inputCanvas  = new Pane();
    private final Pane    _outputCanvas = new Pane();
    
    public PolygonUnionView() {
        this._initializeLayout();
        this._initialize();
    }
    
    private void _initializeLayout() {
        this.setTop(this._toolbar);
        
        ScrollPane inputScrollPane  = new ScrollPane(this._inputCanvas);
        ScrollPane outputScrollPane = new ScrollPane(this._outputCanvas);
        
        BorderPane inputPane  = new BorderPane(inputScrollPane);
        BorderPane outputPane = new BorderPane(outputScrollPane);
        
        inputPane.setTop(new ToolBar(new Label("Input: Polygons")));
        inputScrollPane.setFitToWidth(true);
        inputScrollPane.setFitToHeight(true);
        
        outputPane.setTop(new ToolBar(new Label("Output: Click on the [Apply Union] button")));
        outputScrollPane.setFitToWidth(true);
        outputScrollPane.setFitToHeight(true);
        
        SplitPane splitPane = new SplitPane(inputPane, outputPane);
        this.setCenter(splitPane);
    }
    
    private void _initialize() {
        PolygonUnionViewModel polygonUnionViewModel = new PolygonUnionViewModel(this);
    }
    
    public Pane GetInputCanvas() {
        return this._inputCanvas;
    }
    
    public Pane GetOutputCanvas() {
        return this._outputCanvas;
    }
}
