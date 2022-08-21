package model.commands;

import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class MoveShapeCommand implements ICommand {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int refX;
    int refY;
    int width;
    int height;
    int[] xCoordinates;
    int[] yCoordinates;

    ShapeList shapeList;

    public MoveShapeCommand(ApplicationState applicationState, PaintCanvasBase paintCanvasBase, int refX, int refY, int width, int height, int[] xCoordinats, int[] yCoordinates, ShapeList shapeList) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.refX = refX;
        this.refY = refY;
        this.width = width;
        this.height = height;
        this.xCoordinates = xCoordinats;
        this.yCoordinates = yCoordinates;
        this.shapeList = shapeList;
    }

    @Override
    public void execute() {
        if(shapeList.getISelectedShapes().size() > 0){
            for(IShape iShape: shapeList.getISelectedShapes()){
                iShape.move(refX, refY, width, height, xCoordinates, yCoordinates);
            }
        }
        paintCanvasBase.repaint();
    }
}
