package model.drawShapes;

import model.ShapeList;
import model.ShapeShadingType;
import model.interfaces.ISelectedSubjects;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CreateEclipse implements IShape, ISelectedSubjects {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int referenceX;
    int referenceY;
    int width;
    int height;
    Shape paintArea;
    ShapeList shapeList;

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
    }

    Graphics2D g;

    @Override
    public void drawShape() {
        g = paintCanvasBase.getGraphics2D();

        if(applicationState.getActiveShapeShadingType().equals(ShapeShadingType.FILLED_IN)){
            g.setColor(applicationState.getActivePrimaryColor().getColor());
            g.fillOval(referenceX, referenceY, width, height);

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

        paintArea = new Ellipse2D.Double(referenceX, referenceY, width, height);
        shapeList.addToExisting(paintArea);
    }

    @Override
    public void pasteShape() {
        referenceX += 200;
        // referenceY += 200;
        drawShape();
        paintArea = new Ellipse2D.Double(referenceX, referenceY, width, height);
        shapeList.addToExisting(paintArea);
    }

    @Override
    public void update() {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        g.drawOval(referenceX, referenceY, width, height);
    }
}
