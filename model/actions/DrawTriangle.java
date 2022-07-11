package model.actions;

import model.ShapeColor;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawTriangle implements IShape {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int v1x;
    int v1y;
    int v2x;
    int v2y;
    int v3x;
    int v3y;

    public DrawTriangle(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                         int startX, int startY, int v2x, int v2y, int v3x, int v3y) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.v1x = startX;
        this.v1y = startY;
        this.v2x = v2x;
        this.v2y = v2y;
        this.v3x = v3x;
        this.v3y = v3y;
    }

    int[] xCoordinates = new int[]{v1x, v2x, v3x};
    int[] yCoordinates = new int[]{v1y, v2y, v3y};
    int nPoint = 3;

    @Override
    public void drawShape() {
        Graphics2D g = paintCanvasBase.getGraphics2D();
        g.setColor(applicationState.getActivePrimaryColor().getColor());
        g.drawLine(v1x, v1y, v2x, v2y);
        g.drawLine(v1x, v1y, v3x, v3y);
        g.drawLine(v2x, v2y, v3x, v3y);
        g.fillPolygon(xCoordinates, yCoordinates, nPoint);
    }
}
