package model.actions;

import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawTriangle implements IShape {
    private final int[] xCoordinates;
    private final int[] yCoordinates;
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;

    public DrawTriangle(ApplicationState applicationState, PaintCanvasBase paintCanvasBase, int[] xCoordinates, int[] yCoordinates) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
    }

    @Override
    public void drawShape() {
        Graphics2D g = paintCanvasBase.getGraphics2D();
        g.setColor(applicationState.getActivePrimaryColor().getColor());
        Polygon t = new Polygon(xCoordinates, yCoordinates, 3);
        g.fillPolygon(t);
        System.out.println("Triangle!");
    }
}
