package model.drawShapes;

import model.CommandHistory;
import model.ShapeList;
import model.ShapeShadingType;
import model.interfaces.ISelectedSubjects;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class CreateTriangle implements IShape, IUndoable, ISelectedSubjects {
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
    Graphics2D g;

    @Override
    public void drawShape() {
        g = paintCanvasBase.getGraphics2D();
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
            xCoordinates[i] += 100;
        }
        drawShape();
        Polygon t = new Polygon(xCoordinates, yCoordinates, 3);
        paintArea = t;
        shapeList.addToExisting(paintArea);
    }

    @Override
    public void update() {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        Polygon t = new Polygon(xCoordinates, yCoordinates, 3);
        g.drawPolygon(t);
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
