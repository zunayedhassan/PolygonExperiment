package zunayedhassan.CheckPointPositionOnPolygonBorder.View;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import zunayedhassan.CheckPointPositionOnPolygonBorder.ViewModel.CheckPointPositionOnPolygonBorderViewModel;

/**
 *
 * @author Zunayed Hassan
 */
public class CheckPointPositionOnPolygonBorderView extends BorderPane {
    private final Pane    _inputCanvas  = new Pane();
    private final Label   _resultLabel  = new Label();
    
    public CheckPointPositionOnPolygonBorderView() {
        this._initializeLayout();
        this._initialize();
    }
    
    private void _initializeLayout() {
        ScrollPane inputScrollPane  = new ScrollPane(this._inputCanvas);
        
        inputScrollPane.setFitToWidth(true);
        inputScrollPane.setFitToHeight(true);

        ToolBar toolbar = new ToolBar(this._resultLabel);
        this.setCenter(inputScrollPane);
        this.setBottom(toolbar);
    }
    
    private void _initialize() {
        CheckPointPositionOnPolygonBorderViewModel checkPointPositionOnPolygonBorderViewModel = new CheckPointPositionOnPolygonBorderViewModel(this);
    }
    
    public Pane GetInputCanvas() {
        return this._inputCanvas;
    }
    
    public Label GetResultLabel() {
        return this._resultLabel;
    }
}
