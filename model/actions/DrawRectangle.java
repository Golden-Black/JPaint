package model.actions;

import model.CommandHistory;
import model.ShapeShadingType;
import model.interfaces.IDrawShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawRectangle implements IDrawShape, IUndoable {
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

        if(applicationState.getActiveShapeShadingType().equals(ShapeShadingType.FILLED_IN)){
            g.setColor(applicationState.getActivePrimaryColor().getColor());
            g.fillRect(referenceX, referenceY, width, height);

        }else if(applicationState.getActiveShapeShadingType().equals(ShapeShadingType.OUTLINE)){
            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(applicationState.getActiveSecondaryColor().getColor());
            g.drawRect(referenceX, referenceY, width, height);

        }else if(applicationState.getActiveShapeShadingType().equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(applicationState.getActivePrimaryColor().getColor());
            g.fillRect(referenceX, referenceY, width, height);

            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(applicationState.getActiveSecondaryColor().getColor());
            g.drawRect(referenceX, referenceY, width, height);
        }else throw new Error();

        CommandHistory.add(this);
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
