package model;

import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLOutput;

public class ClickHandler extends MouseAdapter {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    Point start;
    Point end;

    public ClickHandler(ApplicationState applicationState, PaintCanvasBase paintCanvasBase) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
    }

    // press the mouse to get the starting (x,y) coordinates
    @Override
    public void mousePressed(MouseEvent event){
        int x = event.getX();
        int y = event.getY();
        // System.out.println(x + ", " + y);
        start = new Point(x, y);
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
        int referenceX = start.getX();
        int referenceY = start.getY();

        // When drawing from bottom to top
        if(height < 0){
            referenceY = end.getY();
            height = -height;
        }

        // When drawing from right to left
        if(width < 0){
            referenceX = end.getX();
            width = -width;
        }

        paintCanvasBase.getGraphics2D().fillRect(referenceX, referenceY, width, height);
    }

}
