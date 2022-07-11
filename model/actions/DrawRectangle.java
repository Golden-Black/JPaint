package model.actions;

import model.ShapeColor;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawRectangle implements IShape {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int referenceX;
    int referenceY;
    int width;
    int height;

    public DrawRectangle(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                         int refX, int refY, int width, int height) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.referenceX = refX;
        this.referenceY = refY;
        this.width = width;
        this.height = height;
    }

    @Override
    public void drawShape() {
        Graphics2D g = paintCanvasBase.getGraphics2D();
        g.setColor(applicationState.getActivePrimaryColor().getColor());
        g.fillRect(referenceX, referenceY, width, height);
    }
}
