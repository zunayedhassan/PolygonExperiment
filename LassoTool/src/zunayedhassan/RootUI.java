/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zunayedhassan;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Zunayed Hassan
 */
public class RootUI extends BaseUI {
    public Pane Canvas = new Pane();
    
    public ShapeObject Object1 = new ShapeObject("Blue",  this.Canvas, 100,  75, 100,  75, "object1");
    public ShapeObject Object2 = new ShapeObject("Pink",  this.Canvas, 300, 250,  75,  80, "object2");
    public ShapeObject Object3 = new ShapeObject("Orage", this.Canvas, 500,  50,  30,  70, "object3");
    
    public FreehandSelector freehandSelector = new FreehandSelector(this.Canvas);
    
    public RootUI() {
        this.setCenter(this.Canvas);
        
        this.setBottom(new Label("Draw an area with mouse around rectabgles to get the demo"));
    }
}
