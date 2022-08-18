package model.drawShapes;

import model.CommandHistory;
import model.ShapeInfo;
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
    Color primary;
    Color secondary;
    ShapeShadingType shapeShadingType;

    public CreateTriangle(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                          int[] xCoordinates, int[] yCoordinates, Shape paintArea, ShapeList shapeList) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
        this.paintArea = paintArea;
        this.shapeList = shapeList;
        this.primary = applicationState.getActivePrimaryColor().getColor();
        this.secondary = applicationState.getActiveSecondaryColor().getColor();
        this.shapeShadingType = applicationState.getActiveShapeShadingType();
    }
    Graphics2D g;

    @Override
    public void drawShape() {
        g = paintCanvasBase.getGraphics2D();
        g.setColor(primary);
        Polygon t = new Polygon(xCoordinates, yCoordinates, 3);

        if(shapeShadingType.equals(ShapeShadingType.FILLED_IN)){
            g.setColor(primary);
            g.fillPolygon(t);

        }else if(shapeShadingType.equals(ShapeShadingType.OUTLINE)){
            g.fillPolygon(t);

            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(secondary);
            g.drawPolygon(t);

        }else if(shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(primary);
            g.fillPolygon(t);

            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(secondary);
            g.drawPolygon(t);
        }else throw new Error();

        // Add IShape to canvas
        shapeList.addToCanvasIShapes(this);

        // Add Metrics to canvas
        paintArea = t;
        shapeList.addToCanvasShapes(paintArea);

        // Add data to canvas
        ShapeInfo shapeData = new ShapeInfo(0, 0, 0, 0, xCoordinates, yCoordinates);
        shapeList.addToShapeInfo(shapeData);

        CommandHistory.add(this);
    }

    @Override
    public void copy() {
        shapeList.addToCopiedShapes(this);
    }

    @Override
    public void paste() {
        for(int i = 0; i < xCoordinates.length; ++i){
            xCoordinates[i] += 100;
        }
        drawShape();
    }

    @Override
    public void move(int refX, int refY, int widthDist, int heightDist, int[] xCoord, int[] yCoord, ShapeInfo shapeInfo) {
        g.setColor(Color.WHITE);
        Polygon t = new Polygon(shapeInfo.originXCoordinates, shapeInfo.originYCoordinates, 3);
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.drawPolygon(t);

        for(int i = 0; i < 3; ++i) {
            xCoordinates[i] = xCoord[i] + shapeInfo.originXCoordinates[i];
            yCoordinates[i] = xCoord[i] + shapeInfo.originXCoordinates[i];
        }
        drawShape();
    }

    @Override
    public void updateOutline() {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        Polygon t = new Polygon(xCoordinates, yCoordinates, 3);
        g.drawPolygon(t);
    }

    @Override
    public void removeOutline() {
        drawShape();
    }

    @Override
    public void delete() {
        shapeList.getCanvasIShapes().remove(this);
        shapeList.getCanvasShapes().remove(paintArea);
        paintCanvasBase.repaint();
    }

    @Override
    public void undo() {
        shapeList.getCanvasIShapes().remove(2);
        paintCanvasBase.repaint();
    }

    @Override
    public void redo() {
        drawShape();
    }
}
