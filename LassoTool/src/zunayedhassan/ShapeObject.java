/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zunayedhassan;

import javafx.scene.effect.Light.Point;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Zunayed Hassan
 */
public class ShapeObject extends Rectangle {
    private String _name = "";
    
    public ShapeObject(String name, Pane parent, double x, double y, double width, double height, String id) {
        super(x, y, width, height);
        
        this._name = name;
        
        this.setId(id);
        parent.getChildren().add(this);
    }
    
    public double[][] GetPoints() {
        return new double[][] {
            { this.getX(),                   this.getY()                    },
            { this.getX() + this.getWidth(), this.getY()                    },
            { this.getX(),                   this.getY() + this.getHeight() },
            { this.getX() + this.getWidth(), this.getY() + this.getHeight() }
        };
    }
    
    @Override
    public String toString() {
        return this._name;
    }
}
