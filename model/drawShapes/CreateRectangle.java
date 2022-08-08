package model.drawShapes;

import model.CommandHistory;
import model.ShapeList;
import model.ShapeShadingType;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class CreateRectangle implements IShape {
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

        paintArea = new Rectangle(referenceX, referenceY, width, height);
        shapeList.addToExisting(paintArea);

    }

    public Shape getPaintArea() {
        return paintArea;
    }


}
