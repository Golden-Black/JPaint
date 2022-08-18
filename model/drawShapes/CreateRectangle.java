package model.drawShapes;

import model.ShapeInfo;
import model.ShapeList;
import model.ShapeShadingType;
import model.interfaces.ISelectedSubjects;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class CreateRectangle implements IShape, ISelectedSubjects {
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
    Graphics2D g;

    public CreateRectangle(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                           int refX, int refY, int width, int height, Shape paintArea, ShapeList shapeList) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.referenceX = refX;
        this.referenceY = refY;
        this.width = width;
        this.height = height;
        this.paintArea = paintArea;
        this.shapeList = shapeList;
        this.primary = applicationState.getActivePrimaryColor().getColor();
        this.secondary = applicationState.getActiveSecondaryColor().getColor();
        this.shapeShadingType = applicationState.getActiveShapeShadingType();
        this.g = paintCanvasBase.getGraphics2D();
    }

    @Override
    public void drawShape() {

        if(shapeShadingType.equals(ShapeShadingType.FILLED_IN)){
            g.setColor(primary);
            g.fillRect(referenceX, referenceY, width, height);

        }else if(shapeShadingType.equals(ShapeShadingType.OUTLINE)){
            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(secondary);
            g.drawRect(referenceX, referenceY, width, height);

        }else if(shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(primary);
            g.fillRect(referenceX, referenceY, width, height);

            Stroke stroke = new BasicStroke(4);
            g.setStroke(stroke);
            g.setColor(secondary);
            g.drawRect(referenceX, referenceY, width, height);
        }else throw new Error();

        // Add IShape to Canvas
        shapeList.addToCanvasIShapes(this);

        // Add Metrics to Canvas
        paintArea = new Rectangle(referenceX, referenceY, width, height);
        shapeList.addToCanvasShapes(paintArea);

        // Add Data to Canvas
        ShapeInfo shapeData = new ShapeInfo(referenceX, referenceY, width, height, null, null);
        shapeList.addToShapeInfo(shapeData);

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
    public void move(int refX, int refY, int widthDist, int heightDist, int[] xCoordinates, int[] yCoordinates, ShapeInfo shapeInfo) {
//        g.setColor(Color.WHITE);
//        g.fillRect(shapeInfo.originRefX, shapeInfo.originRefY, shapeInfo.originWidth, shapeInfo.originHeight);
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        g.setStroke(stroke);
//        g.drawRect(referenceX, referenceY, width, height);

        referenceX = widthDist + shapeInfo.originRefX;
        referenceY = heightDist + shapeInfo.originRefY;
        drawShape();
    }

    @Override
    public void updateOutline() {


        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        g.drawRect(referenceX, referenceY, width, height);
    }

    public void removeOutline(){
        drawShape();
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        g.setStroke(stroke);
//        g.setColor(Color.GREEN);
//        g.drawRect(referenceX, referenceY, width, height);
    }
}
