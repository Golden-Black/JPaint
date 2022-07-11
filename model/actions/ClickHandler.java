package model.actions;

import model.Point;
import model.ShapeType;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    model.Point start;
    model.Point end;

    public ClickHandler(ApplicationState applicationState, PaintCanvasBase paintCanvasBase) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
    }

    // press the mouse to get the starting (x,y) coordinates
    @Override
    public void mousePressed(MouseEvent event){
        int x = event.getX();
        int y = event.getY();
        start = new model.Point(x, y);
    }

    // release the mouse to get the ending (x,y) coordinates
    @Override
    public void mouseReleased(MouseEvent event){
        int x = event.getX();
        int y = event.getY();
        // System.out.println(x + ", " + y);
        end = new Point(x, y);

        int height = end.getY() - start.getY();
        int width = end.getX() - start.getX();

        // The reference values are used for fillRect as starting coordinates
        int refX = start.getX();
        int refY = start.getY();

        // When drawing from bottom to top
        if(height < 0){
            refY = end.getY();
            height = -height;
        }

        // When drawing from right to left
        if(width < 0){
            refX = end.getX();
            width = -width;
        }

        IShape shape = null;

        if(applicationState.getActiveShapeType().equals(ShapeType.RECTANGLE)) {
            shape = new DrawRectangle(applicationState, paintCanvasBase, refX, refY, width, height);
        }else if(applicationState.getActiveShapeType().equals(ShapeType.ELLIPSE)) {
            shape = new DrawEclipse(applicationState, paintCanvasBase, refX, refY, width, height);
        }else if (applicationState.getActiveShapeType().equals(ShapeType.TRIANGLE)){
            shape = new DrawTriangle(applicationState, paintCanvasBase,
                    start.getX(), start.getY(),
                    2 * start.getX() - end.getX(), end.getY(),
                    end.getX(), end.getY()
                    );
        }else throw new Error();

        draw(shape);

    }

    private void draw(IShape shape) {
        shape.drawShape();
    }

}
