package model.actions;

import model.CommandHistory;
import model.ShapeShadingType;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawTriangle implements IShape, IUndoable {
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

        if(applicationState.getActiveShapeShadingType().equals(ShapeShadingType.FILLED_IN)){
            g.setColor(applicationState.getActivePrimaryColor().getColor());
            g.fillPolygon(t);

        }else if(applicationState.getActiveShapeShadingType().equals(ShapeShadingType.OUTLINE)){
            g.fillPolygon(t);

            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(applicationState.getActiveSecondaryColor().getColor());
            g.drawPolygon(t);

        }else if(applicationState.getActiveShapeShadingType().equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(applicationState.getActivePrimaryColor().getColor());
            g.fillPolygon(t);

            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(applicationState.getActiveSecondaryColor().getColor());
            g.drawPolygon(t);
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
