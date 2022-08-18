package model;

import model.editShapes.CopyShapeList;
import model.editShapes.pasteShapeList;
import model.interfaces.ISelectedObservers;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeList implements ISelectedObservers {

    // for moving
    private static final List<ShapeInfo> shapeInfoList = new ArrayList<>(); // for moving


    public void addToShapeInfo (ShapeInfo shapeData){
        shapeInfoList.add(shapeData);
    }
    public List<ShapeInfo> getShapeInfoList(){
        return shapeInfoList;
    }

    private static final List<Shape> existingShapes = new ArrayList<>();
    public List<Shape> getExistingShapes() {
        return existingShapes;
    }


    private static final List<IShape> iShapeList = new ArrayList<>(); // for drawing
    private static final List<IShape> iShapeSelected = new ArrayList<>(); // for drawing
    public List<IShape> getiShapeSelected(){
        return iShapeSelected;
    }

    private final List<IShape> selectedObservers = new ArrayList<>();// observer

    List<Shape> undoneShapes = new ArrayList<>();

    private static final List<IShape> clipboard = new ArrayList<>();

    // getters


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
    public void removeExisting(Shape shape){
        existingShapes.remove(shape);
    }

    public void addIShapeList(IShape iShape){
        iShapeList.add(iShape);
    }
    public void removeIShape(IShape iShape) { iShapeList.remove(iShape); }

    public void addIShapeSelect(IShape iShape){
        iShapeSelected.add(iShape);
    }

    public void removeLastShape(){
        Shape lastShape = existingShapes.get(existingShapes.size() - 1);
        existingShapes.remove(existingShapes.size() - 1);
        undoneShapes.add(lastShape);
    }

    public static void delete(){
        // updating to new design pattern
    }

    public static void group() {
        
    }

    public static void unGroup() {
        iShapeSelected.clear();
    }

    @Override
    public void registerObserver(IShape observer) {
        selectedObservers.add(observer);
    }

    @Override
    public void removeObserver(IShape observer) {
        observer.removeOutline();
        selectedObservers.remove(observer);
    }

    public void notifySelectedObservers() {
        for (IShape sObserver : selectedObservers) {
            sObserver.updateOutline();
        }
    }


    ////////////////////////////////////////

    // All shapes & IShapes on Canvas
    private static final List<Shape> canvasShapes = new ArrayList<>();
    private static final List<IShape> canvasIShapes = new ArrayList<>();
    public List<Shape> getCanvasShapes() {
        return canvasShapes;
    }
    public List<IShape> getCanvasIShapes() {
        return canvasIShapes;
    }

    public void addToCanvasIShapes(IShape iShape){
        canvasIShapes.add(iShape);
    }
    public void addToCanvasShapes(Shape shape){
        canvasShapes.add(shape);
    }

    // ----------------SELECT SHAPES--------------------------------
    // Selected Shapes & IShapes
    private static final List<IShape> iSelectedShapes = new ArrayList<>();
    public List<IShape> getISelectedShapes() {
        return iSelectedShapes;
    }
    public void addISelectedShapes(IShape iShape){
        iSelectedShapes.add(iShape);
    }
    public int ISelectedShapesSize(){
        return iSelectedShapes.size();
    }

    private static final List<Shape> selectedShapes = new ArrayList<>();
    public List<Shape> getSelectedShapes() {
        return selectedShapes;
    }
    public void addSelectedShapes(Shape shape){
        selectedShapes.add(shape);
    }


    // ----------------COPY SHAPES----------------------------------
    // register the copied IShapes from selected IShapes
    public static void copy(){
        // register Copy Pieces
        CopyShapeList copyShapeListHandler = new CopyShapeList(iSelectedShapes);
        for(int i = 0; i < iSelectedShapes.size(); ++i){
            copyShapeListHandler.registerObserver(iSelectedShapes.get(i));
        }
        copyShapeListHandler.start();
    }

    // List for copied IShapes
    private static final List<IShape> copiedShapes = new ArrayList<>(); // observer
    public void addToCopiedShapes(IShape iShape){
        copiedShapes.add(iShape);
    }


    // ----------------PASTE SHAPES----------------------------------
    // List for pasting IShapes
    // register the copied IShapes from selected IShapes
    public static void paste(){
        pasteShapeList pasteShapeListHandler = new pasteShapeList(copiedShapes);
        for(int i = 0; i < iSelectedShapes.size(); ++i){
            pasteShapeListHandler.registerObserver(copiedShapes.get(i));
        }
        pasteShapeListHandler.start();
    }


}
