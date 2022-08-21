package model;

import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class ShapeInfo {
    public int originRefX;
    public int originRefY;
    public int originWidth;
    public int originHeight;
    public int[] originXCoordinates;
    public int[] originYCoordinates;
    ShapeList shapeList;
    Color primary;
    Color secondary;
    ShapeShadingType shapeShadingType;


    public ShapeInfo(int originRefX, int originRefY, int originWidth, int originHeight, int[] originXCoordinates, int[] originYCoordinates) {
        this.originRefX = originRefX;
        this.originRefY = originRefY;
        this.originWidth = originWidth;
        this.originHeight = originHeight;
        this.originXCoordinates = originXCoordinates;
        this.originYCoordinates = originYCoordinates;
    }
}
