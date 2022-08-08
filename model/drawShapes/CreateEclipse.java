package model.drawShapes;

import model.CommandHistory;
import model.ShapeShadingType;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CreateEclipse implements IShape, IUndoable {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int referenceX;
    int referenceY;
    int width;
    int height;
    Shape paintArea;

    public CreateEclipse(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                         int referenceX, int referenceY, int width, int height, Shape paintArea) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.referenceX = referenceX;
        this.referenceY = referenceY;
        this.width = width;
        this.height = height;
        this.paintArea = paintArea;
    }

    @Override
    public void drawShape() {
        Graphics2D g = paintCanvasBase.getGraphics2D();

        if(applicationState.getActiveShapeShadingType().equals(ShapeShadingType.FILLED_IN)){
            g.setColor(applicationState.getActivePrimaryColor().getColor());
            g.fillOval(referenceX, referenceY, width, height);
            paintArea = new Ellipse2D.Double(referenceX, referenceY, width, height);

        }else if(applicationState.getActiveShapeShadingType().equals(ShapeShadingType.OUTLINE)){
            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(applicationState.getActiveSecondaryColor().getColor());
            g.drawOval(referenceX, referenceY, width, height);

        }else if(applicationState.getActiveShapeShadingType().equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(applicationState.getActivePrimaryColor().getColor());
            g.fillOval(referenceX, referenceY, width, height);

            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(applicationState.getActiveSecondaryColor().getColor());
            g.drawOval(referenceX, referenceY, width, height);
        }else throw new Error();

        CommandHistory.add(this);
    }

    public Shape getPaintArea() {
        return paintArea;
    }

    @Override
    public void undo() {
        paintCanvasBase.repaint();
    }

    @Override
    public void redo() {
        drawShape();
    }
}
