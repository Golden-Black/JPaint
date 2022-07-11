package model.actions;

import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawEclipse implements IShape {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int referenceX;
    int referenceY;
    int width;
    int height;

    public DrawEclipse(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                       int referenceX, int referenceY, int width, int height) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.referenceX = referenceX;
        this.referenceY = referenceY;
        this.width = width;
        this.height = height;
    }

    @Override
    public void drawShape() {
        Graphics2D g = paintCanvasBase.getGraphics2D();
        g.setColor(applicationState.getActivePrimaryColor().getColor());
        g.fillOval(referenceX, referenceY, width, height);
    }
}
