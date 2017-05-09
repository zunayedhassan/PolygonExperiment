package zunayedhassan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import zunayedhassan.LineIntersection.LineIntersection;
import zunayedhassan.LineIntersection.Result;

/**
 *
 * @author Zunayed Hassan
 */
public class LineIntersectionPane extends SplitPane {
    private final Pane      _canvas      = new Pane();
    private final GridPane  _sidePanel   = new GridPane();
    private final TextField _x1Field     = new TextField();
    private final TextField _y1Field     = new TextField();
    private final TextField _x2Field     = new TextField();
    private final TextField _y2Field     = new TextField();
    private final TextField _x3Field     = new TextField();
    private final TextField _y3Field     = new TextField();
    private final TextField _x4Field     = new TextField();
    private final TextField _y4Field     = new TextField();
    private final Button    _resetButton = new Button("Reset");
    private final Button    _applyButton = new Button("Apply");
    private final Label     _result      = new Label();
    
    public LineIntersectionPane() {
        this._initializeLayout();
        this._initializeEvents();
    }
    
    private void _initializeLayout() {
        this.setDividerPositions(0.4, 1.0);        
        this._sidePanel.setPadding(new Insets(10));
      
        // Line 1
        Label line1 = new Label("Line 1");
        this.AddToSidePanel(line1, 0, 0);
        
        Label x1Label = new Label("X1");
        this.AddToSidePanel(x1Label, 1, 0);
        this.AddToSidePanel(this._x1Field, 1, 1);
        
        Label y1Label = new Label("Y1");
        this.AddToSidePanel(y1Label, 2, 0);
        this.AddToSidePanel(this._y1Field, 2, 1);
        
        Label x2Label = new Label("X2");
        this.AddToSidePanel(x2Label, 3, 0);
        this.AddToSidePanel(this._x2Field, 3, 1);
        
        Label y2Label = new Label("Y2");
        this.AddToSidePanel(y2Label, 4, 0);
        this.AddToSidePanel(this._y2Field, 4, 1);
        

        this._addSeperatorToSidePanelVertically(5);
        
        
        // Line 2
        Label line2 = new Label("Line 2");
        this.AddToSidePanel(line2, 6, 0);
        
        Label x3Label = new Label("X3");
        this.AddToSidePanel(x3Label, 7, 0);
        this.AddToSidePanel(this._x3Field, 7, 1);
        
        Label y3Label = new Label("Y3");
        this.AddToSidePanel(y3Label, 8, 0);
        this.AddToSidePanel(this._y3Field, 8, 1);
        
        Label x4Label = new Label("X4");
        this.AddToSidePanel(x4Label, 9, 0);
        this.AddToSidePanel(this._x4Field, 9, 1);
        
        Label y4Label = new Label("Y4");
        this.AddToSidePanel(y4Label, 10, 0);
        this.AddToSidePanel(this._y4Field, 10, 1);
        
        
        this._addSeperatorToSidePanelVertically(11);
        
        
        this.AddToSidePanel(this._resetButton, 12, 0);
        this.AddToSidePanel(this._applyButton, 12, 1);
        
        this._resetButton.setMinWidth(100);
        this._applyButton.setMinWidth(100);
        
        
        this._addSeperatorToSidePanelVertically(13);
        
        // Result
        this.AddToSidePanel(this._result, 14, 0);
        
        
        ScrollPane sidePanelScrollPane = new ScrollPane(this._sidePanel);
        sidePanelScrollPane.setFitToWidth(true);
        sidePanelScrollPane.setFitToHeight(true);
        this.AddToSplitPane(sidePanelScrollPane);
        
        ScrollPane canvasScrollPane = new ScrollPane(this._canvas);
        this.AddToSplitPane(canvasScrollPane);
    }
    
    private void _initializeEvents() {
        this._resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OnReset();
            }
        });
        
        this._applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OnApply();
            }
        });
    }
    
    private void _addSeperatorToSidePanelVertically(int row) {
        this.AddToSidePanel(new Label(), row, 0);
    }

    public void AddToSplitPane(Node node) {
        this.getItems().add(node);
    }
    
    public void AddToSidePanel(Node node, int row, int column) {
        this._sidePanel.getChildren().add(node);
        GridPane.setRowIndex(node, row);
        GridPane.setColumnIndex(node, column);
    }
    
    private void _setResult(double x, double y, Result.COMMENT comment) {
        String xText = this._getStringAfterConvertingDouble(x);
        String yText = this._getStringAfterConvertingDouble(y);
        
        this._result.setText("Result: " + xText + ", " + yText + " " + comment);
    }
    
    private String _getStringAfterConvertingDouble(double value) {
        return String.format("%1$,.2f", value);
    }
    
    private void _clearResult() {
        this._result.setText("");
    }
    
    private void _clearCanvas() {
        this._clearMark();
        this._canvas.getChildren().clear();
    }
    
    private void _addToCanvas(Shape shape) {
        this._canvas.getChildren().add(shape);
    }
    
    private void _clearTextFields()  {
        for (Node node : this._sidePanel.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                textField.clear();
            }
        }
    }
    
    private double _getDoubleFromTextField(TextField textField) {
        double value = 0;
        
        try {
            value = Double.parseDouble(textField.getText().trim());
        }
        catch(NumberFormatException exception) {
            System.err.println("Error: Number format exception");
        }
        finally {
            return value;
        }
    }
    
    private void _drawLineOnCanvas(double x1, double y1, double x2, double y2) {
        Line line = new Line(x1, y1, x2, y2);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(1);
        
        this._addToCanvas(line);
    }
    
    private void _clearMark() {
        Circle markToBeRemoved = null;
        
        for (Node node : this._canvas.getChildren()) {
            if (node instanceof Circle) {
                markToBeRemoved = (Circle) node;
                break;
            }
        }
        
        if (markToBeRemoved != null) {
            this._canvas.getChildren().remove(markToBeRemoved);
        }
    }
    
    private void _setMark(double x, double y) {
        this._clearMark();
        
        Circle mark = new Circle(x, y, 5, Color.RED);
        this._addToCanvas(mark);
    }
    
    private void _displayLog(String text) {
        System.out.println(text);
    }
    
    public void OnReset() {
        this._clearTextFields();
        this._clearCanvas();
        this._clearResult();
    }
    
    public void OnApply() {
        double x1 = this._getDoubleFromTextField(this._x1Field);
        double y1 = this._getDoubleFromTextField(this._y1Field);
        
        double x2 = this._getDoubleFromTextField(this._x2Field);
        double y2 = this._getDoubleFromTextField(this._y2Field);
        
        double x3 = this._getDoubleFromTextField(this._x3Field);
        double y3 = this._getDoubleFromTextField(this._y3Field);
        
        double x4 = this._getDoubleFromTextField(this._x4Field);
        double y4 = this._getDoubleFromTextField(this._y4Field);
        
        this._drawLineOnCanvas(x1, y1, x2, y2);
        this._drawLineOnCanvas(x3, y3, x4, y4);
        
        LineIntersection lineIntersectionCalculator = new LineIntersection(x1, y1, x2, y2, x3, y3, x4, y4);
        Result result = lineIntersectionCalculator.GetLineIntersectedPoint();
        
        if (result != null) {
            double x = result.X;
            double y = result.Y;
            
            this._setMark(x, y);
            this._setResult(x, y, result.Comment);
            
            this._displayLog("LINE 1: " + x1 + " " + y1 + " " + x2 + " " + y2 + "\nLINE 2: " + x3 + " " + y3 + " " + x4 + " " + y4);
            this._displayLog("RESULT:\t" + x + " " + y + " " + result.Comment);
        }
    }
}
