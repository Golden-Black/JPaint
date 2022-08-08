package model;

import model.interfaces.ISelectedObservers;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeList implements ISelectedObservers {
    private static final List<Shape> existingShapes = new ArrayList<>(); // for selecting
    private static final List<IShape> iShapeList = new ArrayList<>(); // for drawing

    List<Shape> selectedShapes = new ArrayList<>(); // for selecting
    private static final List<IShape> iShapeSelected = new ArrayList<>(); // for drawing

    private final List<IShape> selectedObservers = new ArrayList<>();// observer

    List<Shape> undoneShapes = new ArrayList<>();

    private static final List<IShape> clipboard = new ArrayList<>();

    // getters
    public List<Shape> getExistingShapes() {
        return existingShapes;
    }
    public List<Shape> getSelectedShapes() {
        return selectedShapes;
    }
    public List<IShape> getIShapeList() {
        return iShapeList;
    }
    public List<Shape> getUndoneShapes() {
        return undoneShapes;
    }


    // Adding functions
    public void addToExisting(Shape shape){
        existingShapes.add(shape);
        undoneShapes.clear();
    }

    public void addSelected(Shape shape){
        selectedShapes.add(shape);
    }

    public void addIShapeList(IShape iShape){
        iShapeList.add(iShape);
    }

    public void addIShapeSelect(IShape iShape){
        iShapeSelected.add(iShape);
    }

    public void removeLastShape(){
        Shape lastShape = existingShapes.get(existingShapes.size() - 1);
        existingShapes.remove(existingShapes.size() - 1);
        undoneShapes.add(lastShape);
    }

    public static void copy(){
        clipboard.clear();
        clipboard.addAll(iShapeSelected);
    }

    public static void paste(){
        for (IShape iShape : clipboard) {
            iShape.pasteShape();
            iShapeList.add(iShape);
        }
    }

    public static void delete(){

    }

    @Override
    public void registerObserver(IShape observer) {
        selectedObservers.add(observer);
    }

    @Override
    public void removeObserver(IShape observer) {
        selectedObservers.remove(observer);
    }

    public void notifySelectedObservers() {
        for (IShape sObserver : selectedObservers) {
            sObserver.update();
        }
    }
}
