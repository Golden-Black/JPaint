package model.commands;

import model.ShapeList;
import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class MoveShapeCommand implements ICommand {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int refX;
    int refY;
    int width;
    int height;
    ShapeList shapeList;

    public MoveShapeCommand(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
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
        // change of design pattern
        // major updates will be implemented in Sprint 4
    }
}
