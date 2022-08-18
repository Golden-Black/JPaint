package model.commands;

import model.CommandHistory;
import model.ShapeList;
import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.geom.Area;

public class SelectShapeCommand implements ICommand {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int refX;
    int refY;
    int width;
    int height;
    ShapeList shapeList;

    public SelectShapeCommand(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                              int refX, int refY, int width, int height, ShapeList shapeList) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.refX = refX;
        this.refY = refY;
        this.width = width;
        this.height = height;
        this.shapeList = shapeList;
    }


    @Override
    public void execute() {
        // Collision Detection
        // Selected Area
        Rectangle selectedArea = new Rectangle(refX, refY, width, height);

        // clear the selected shapes from last Select command
        if(shapeList.ISelectedShapesSize() > 0) {
            shapeList.getSelectedShapes().clear(); // shape
            shapeList.getISelectedShapes().clear(); // IShape
        }

        if (shapeList.getCanvasShapes().size() != 0) {
            for (int i = 0; i < shapeList.getCanvasShapes().size(); ++i) {
                Area shapeArea = new Area(shapeList.getCanvasShapes().get(i));
                Area selectArea = new Area(selectedArea);

                // compare the selected area and the shapes on canvas
                if (selectArea.getBounds2D().intersects(shapeArea.getBounds2D())) {
                    // if not included, add to both selectShapes and selectIShape list
                    shapeList.addSelectedShapes(shapeList.getCanvasShapes().get(i));
                    shapeList.addISelectedShapes(shapeList.getCanvasIShapes().get(i));
                    System.out.println("Shape Selected!");
                }
            }
        }
    }
}
