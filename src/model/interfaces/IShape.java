package model.interfaces;

import model.ShapeInfo;
import model.ShapeList;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public interface IShape {
    void drawShape();
    void copy();
    void paste();
    void move(int referenceX, int referenceY, int width, int height, int[] xCoordinates, int[] yCoordinates);

    void updateOutline();
    void removeOutline();

    void delete();
}
