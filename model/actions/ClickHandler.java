package model.actions;

import model.CommandHandler;
import model.MouseMode;
import model.Point;
import model.ShapeType;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {
    ApplicationState applicationState;
    PaintCanvasBase paintCanvasBase;
    model.Point start;
    model.Point end;
    CommandHandler commandHandler = new CommandHandler();

    public ClickHandler(ApplicationState applicationState, PaintCanvasBase paintCanvasBase) {
        this.applicationState = applicationState;
        this.paintCanvasBase = paintCanvasBase;
    }

    // press the mouse to get the starting (x,y) coordinates
    @Override
    public void mousePressed(MouseEvent event){
        int x = event.getX();
        int y = event.getY();
        start = new model.Point(x, y);
    }

    // release the mouse to get the ending (x,y) coordinates
    @Override
    public void mouseReleased(MouseEvent event){
        int x = event.getX();
        int y = event.getY();
        end = new Point(x, y);

        int height = end.getY() - start.getY();
        int width = end.getX() - start.getX();

        // The reference values are used for fillRect as starting coordinates
        int refX = start.getX();
        int refY = start.getY();

        // When drawing from bottom to top
        if(height < 0){
            refY = end.getY();
            height = -height;
        }

        // When drawing from right to left
        if(width < 0){
            refX = end.getX();
            width = -width;
        }

        int[] xCoordinates = new int[]{this.start.getX(), 2 * this.start.getX() - this.end.getX(), this.end.getX()};
        int[] yCoordinates = new int[]{this.start.getY(), this.end.getY(), this.end.getY()};

        ICommand command = null;

        if(applicationState.getActiveMouseMode().equals(MouseMode.DRAW)){
            command = new DrawCommand(applicationState, paintCanvasBase, refX, refY, width, height, xCoordinates, yCoordinates);
        }

        assert command != null;
        commandHandler.process(command);
    }

}
