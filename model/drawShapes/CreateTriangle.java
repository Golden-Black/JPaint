package model.drawShapes;

import model.CommandHistory;
import model.ShapeList;
import model.ShapeShadingType;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class CreateTriangle implements IShape, IUndoable {
    private final int[] xCoordinates;
    private final int[] yCoordinates;
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    Shape paintArea;
    ShapeList shapeList;

    public CreateTriangle(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                          int[] xCoordinates, int[] yCoordinates, Shape paintArea, ShapeList shapeList) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
        this.paintArea = paintArea;
        this.shapeList = shapeList;
    }

    @Override
    public void drawShape() {
        Graphics2D g = paintCanvasBase.getGraphics2D();
        g.setColor(applicationState.getActivePrimaryColor().getColor());
        Polygon t = new Polygon(xCoordinates, yCoordinates, 3);
        paintArea = t;
        shapeList.addToExisting(paintArea);

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
    public void pasteShape() {
        for(int i = 0; i < xCoordinates.length; ++i){
            xCoordinates[i] += 1;
            yCoordinates[i] += 1;
        }
        drawShape();
        Polygon t = new Polygon(xCoordinates, yCoordinates, 3);
        paintArea = t;
        shapeList.addToExisting(paintArea);
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
