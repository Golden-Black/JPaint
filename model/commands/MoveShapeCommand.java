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
        // change of design pattern
        // major updates will be implemented in Sprint 4
//        int oldSize = shapeList.getiShapeSelected().size();
//        if(shapeList.getiShapeSelected().size() > 0){
//            for(int i = 0; i < shapeList.getiShapeSelected().size(); ++i){
//                IShape iShape = shapeList.getiShapeSelected().get(i);
//                shapeList.getiShapeSelected().get(i).move(refX, refY, width, height, xCoordinates, yCoordinates, shapeList.getShapeInfoList().get(i));
//                shapeList.getIShapeList().remove(iShape);
//            }
//        }
        if(shapeList.getISelectedShapes().size() > 0){
            for(IShape iShape: shapeList.getISelectedShapes()){
                iShape.move(refX, refY, width, height, xCoordinates, yCoordinates);
            }
        }
        paintCanvasBase.repaint();


    }
}
