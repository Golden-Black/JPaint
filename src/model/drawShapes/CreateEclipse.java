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
import java.awt.geom.Ellipse2D;

public class CreateEclipse implements IShape, ISelectedSubjects, IUndoable {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int referenceX;
    int referenceY;
    int width;
    int height;
    Shape paintArea;
    ShapeList shapeList;
    Color primary;
    Color secondary;
    ShapeShadingType shapeShadingType;
    ShapeInfo shapeInfo;

    public CreateEclipse(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                         int referenceX, int referenceY, int width, int height, Shape paintArea, ShapeList shapeList) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.referenceX = referenceX;
        this.referenceY = referenceY;
        this.width = width;
        this.height = height;
        this.paintArea = paintArea;
        this.shapeList = shapeList;
        this.primary = applicationState.getActivePrimaryColor().getColor();
        this.secondary = applicationState.getActiveSecondaryColor().getColor();
        this.shapeShadingType = applicationState.getActiveShapeShadingType();
        this.shapeInfo = null;
    }

    Graphics2D g;

    @Override
    public void drawShape() {
        g = paintCanvasBase.getGraphics2D();

        if(shapeShadingType.equals(ShapeShadingType.FILLED_IN)){
            g.setColor(primary);
            g.fillOval(referenceX, referenceY, width, height);

        }else if(shapeShadingType.equals(ShapeShadingType.OUTLINE)){
            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(secondary);
            g.drawOval(referenceX, referenceY, width, height);

        }else if(shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(primary);
            g.fillOval(referenceX, referenceY, width, height);

            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(secondary);
            g.drawOval(referenceX, referenceY, width, height);
        }else throw new Error();

        // Add IShape to canvas
        shapeList.addToCanvasIShapes(this);
        System.out.println(shapeList.getCanvasIShapes().size());

        paintArea = new Ellipse2D.Double(referenceX, referenceY, width, height);
        // Add to the ShapeMetrics
        shapeList.addToCanvasShapes(paintArea);

        // Add to shape info
        shapeInfo = new ShapeInfo(primary, secondary, shapeShadingType);
        shapeList.addToShapeInfo(shapeInfo);

        CommandHistory.add(this);
    }

    @Override
    public void copy() {
        shapeList.addToCopiedShapes(this);
    }

    @Override
    public void paste() {
        referenceX += 200;
        drawShape();
    }

    @Override
    public void delete() {
        shapeList.getCanvasIShapes().remove(this);
        shapeList.getCanvasShapes().remove(paintArea);
        paintCanvasBase.repaint();
    }

    @Override
    public void move(int refX, int refY, int widthDist, int heightDist, int[] xCoordinates, int[] yCoordinates) {
        referenceX = widthDist + referenceX;
        referenceY = heightDist + referenceY;
        shapeList.removeIShapeFromCanvas(this);
        shapeList.removeShapeFromCanvas(paintArea);
        drawShape();
    }

    @Override
    public void updateOutline() {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        g.drawOval(referenceX, referenceY, width, height);
    }

    @Override
    public void group() {
        shapeList.addToIShapeGroup(this);
        shapeList.addToShapeGroup(paintArea);
    }

    @Override
    public void unGroup() {
        shapeList.removeFromIShapeGroup(this);
        shapeList.removeFromShapeGroup(paintArea);
        paintCanvasBase.repaint();
    }


    @Override
    public void undo() {
        shapeList.removeIShapeFromCanvas(this);
        shapeList.removeShapeFromCanvas(paintArea);
        shapeList.removeShapeInfo(shapeInfo);
        paintCanvasBase.repaint();
    }

    @Override
    public void redo() {
        shapeList.addToCanvasIShapes(this);
        shapeList.addToCanvasShapes(paintArea);
        shapeList.addToShapeInfo(shapeInfo);
        paintCanvasBase.repaint();
    }
}
