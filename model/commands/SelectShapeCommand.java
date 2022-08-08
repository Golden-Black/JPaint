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
        Graphics2D g = paintCanvasBase.getGraphics2D();
        Rectangle selectedArea = new Rectangle(refX, refY, width ,height);

        // check collision
        if(shapeList.getExistingShapes().size() != 0){

            // clear the seleted shapes from last Select command
            shapeList.getSelectedShapes().clear();

            for(int i = 0; i < shapeList.getExistingShapes().size(); ++i){

                Area shapeArea = new Area(shapeList.getExistingShapes().get(i));
                Area selectArea = new Area(selectedArea);

                // add to selected shapes if [collied AND not already included]
                if(selectArea.getBounds2D().intersects(shapeArea.getBounds2D()) &&
                !shapeList.getSelectedShapes().contains(shapeList.getExistingShapes().get(i))){
                    shapeList.addSelected(shapeList.getExistingShapes().get(i));
                    shapeList.addIShapeSelect(shapeList.getIShapeList().get(i));
                    shapeList.registerObserver(shapeList.getIShapeList().get(i));

                    System.out.println("Shape Selected!");
                }
            }

            shapeList.notifySelectedObservers();


        }
    }
}
