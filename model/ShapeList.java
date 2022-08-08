package model;

import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeList {
    List<Shape> existingShapes = new ArrayList<>();
    List<Shape> selectedShapes = new ArrayList<>();
    List<Shape> undoneShapes = new ArrayList<>();

    public List<Shape> getExistingShapes() {
        return existingShapes;
    }

    public List<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public List<Shape> getUndoneShapes() {
        return undoneShapes;
    }

    public void addToExisting(Shape shape){
        existingShapes.add(shape);
        undoneShapes.clear();
    }

    public void addSelected(Shape shape){
        selectedShapes.add(shape);
    }

    public void removeLastShape(){
        Shape lastShape = existingShapes.get(existingShapes.size() - 1);
        existingShapes.remove(existingShapes.size() - 1);
        undoneShapes.add(lastShape);
    }
}
