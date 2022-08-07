package model.actions;

import model.ShapeType;
import model.interfaces.ICommand;
import model.interfaces.IDrawShape;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class DrawCommand implements ICommand {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    int refX;
    int refY;
    int width;
    int height;
    int[] xCoordinates;
    int[] yCoordinates;

    public DrawCommand(ApplicationState applicationState, PaintCanvasBase paintCanvasBase,
                       int refX, int refY, int width, int height, int[] xCoordinates, int[] yCoordinates) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
        this.refX = refX;
        this.refY = refY;
        this.width = width;
        this.height = height;
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
    }

    public void execute() {
        IDrawShape shape = null;
        if (this.applicationState.getActiveShapeType().equals(ShapeType.RECTANGLE)) {
            shape = new DrawRectangle(this.applicationState, this.paintCanvasBase, this.refX, this.refY, this.width, this.height);
        } else if (this.applicationState.getActiveShapeType().equals(ShapeType.ELLIPSE)) {
            shape = new DrawEclipse(this.applicationState, this.paintCanvasBase, this.refX, this.refY, this.width, this.height);
        } else if (this.applicationState.getActiveShapeType().equals(ShapeType.TRIANGLE)) {
            shape = new DrawTriangle(this.applicationState, this.paintCanvasBase, this.xCoordinates, this.yCoordinates);
        } else throw new Error();

        shape.drawShape();
    }
}

