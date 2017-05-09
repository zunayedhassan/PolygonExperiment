package zunayedhassan.VertexSorting.View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import zunayedhassan.VertexSorting.ViewModel.VertexSortingViewModel;

/**
 *
 * @author Zunayed Hassan
 */
public class VertexSortingView extends BorderPane {
    public  final Button  SortButton    = new Button("Sort Vertices");
    public  final Button  ClearButton   = new Button("Clear");
    
    private final ToolBar _toolbar      = new ToolBar(this.SortButton, this.ClearButton);
    private final Pane    _inputCanvas  = new Pane();
    private final Pane    _outputCanvas = new Pane();
    
    public VertexSortingView() {
        this._initializeLayout();
        this._initialize();
        this._bindEvents();
    }
    
    private void _initializeLayout() {
        this.setTop(this._toolbar);
        
        ScrollPane inputScrollPane  = new ScrollPane(this._inputCanvas);
        ScrollPane outputScrollPane = new ScrollPane(this._outputCanvas);
        
        BorderPane inputPane  = new BorderPane(inputScrollPane);
        BorderPane outputPane = new BorderPane(outputScrollPane);
        
        inputPane.setTop(new ToolBar(new Label("Input: Please click on the Canvas")));
        inputScrollPane.setFitToWidth(true);
        inputScrollPane.setFitToHeight(true);
        
        outputPane.setTop(new ToolBar(new Label("Output: Click on the [Sort Vertices] button")));
        outputScrollPane.setFitToWidth(true);
        outputScrollPane.setFitToHeight(true);
        
        SplitPane splitPane = new SplitPane(inputPane, outputPane);
        this.setCenter(splitPane);
    }
    
    private void _initialize() {

    }
    
    private void _bindEvents() {
        VertexSortingViewModel eventBinder = new VertexSortingViewModel(this);
    }
    
    public Pane GetInputCanvas() {
        return this._inputCanvas;
    }
    
    public Pane GetOutputCanvas() {
        return this._outputCanvas;
    }
}
