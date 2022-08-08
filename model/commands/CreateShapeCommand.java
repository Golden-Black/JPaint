package model.commands;

import model.CommandHistory;
import model.ShapeList;
import model.ShapeType;
import model.drawShapes.CreateEclipse;
import model.drawShapes.CreateRectangle;
import model.drawShapes.CreateTriangle;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class CreateShapeCommand implements ICommand, IUndoable {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int refX;
    int refY;
    int width;
    int height;
    int[] xCoordinates;
    int[] yCoordinates;
    ShapeList shapeList;

    IShape shape = null;

    public CreateShapeCommand(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                              int refX, int refY, int width, int height, int[] xCoordinates, int[] yCoordinates,
                              ShapeList shapeList) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.refX = refX;
        this.refY = refY;
        this.width = width;
        this.height = height;
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
        this.shapeList = shapeList;
    }

    public void execute() {

        if (this.applicationState.getActiveShapeType().equals(ShapeType.RECTANGLE)) {
            shape = new CreateRectangle(this.applicationState, this.paintCanvasBase, this.refX, this.refY, this.width, this.height, null, shapeList);
        } else if (this.applicationState.getActiveShapeType().equals(ShapeType.ELLIPSE)) {
            shape = new CreateEclipse(this.applicationState, this.paintCanvasBase, this.refX, this.refY, this.width, this.height, null);
        } else if (this.applicationState.getActiveShapeType().equals(ShapeType.TRIANGLE)) {
            shape = new CreateTriangle(this.applicationState, this.paintCanvasBase, this.xCoordinates, this.yCoordinates, null);
        } else throw new Error();

        shape.drawShape();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        shapeList.removeLastShape();
        System.out.println("Here!");
        for(Shape iShape: shapeList.getExistingShapes()){
            paintCanvasBase.repaint((Rectangle) iShape);
        }
    }

    @Override
    public void redo() {

    }
}

