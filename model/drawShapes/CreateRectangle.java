package model.drawShapes;

import model.CommandHistory;
import model.ShapeList;
import model.ShapeShadingType;
import model.interfaces.ISelectedObserver;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class CreateRectangle implements IShape, ISelectedObserver {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int referenceX;
    int referenceY;
    int width;
    int height;
    Shape paintArea;
    ShapeList shapeList;

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
    }

    Graphics2D g;

    @Override
    public void drawShape() {
        g = paintCanvasBase.getGraphics2D();

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

        paintArea = new Rectangle(referenceX, referenceY, width, height);
        shapeList.addToExisting(paintArea);

    }

    @Override
    public void pasteShape() {
        referenceX += 200;
        // referenceY += 200;
        drawShape();
        paintArea = new Rectangle(referenceX, referenceY, width, height);
        shapeList.addToExisting(paintArea);
    }

    @Override
    public void update() {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        g.drawRect(referenceX, referenceY, width, height);
    }
}
