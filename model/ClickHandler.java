package model;

import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

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
        int width = end.getX() - end.getY();
        paintCanvasBase.getGraphics2D().fillRect(start.getX(), start.getY(), width, height);
    }


}
