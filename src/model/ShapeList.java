package model;

import model.editShapes.CopyShapeList;
import model.editShapes.deleteShapeList;
import model.editShapes.pasteShapeList;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeList {

    // for moving



    private final List<IShape> selectedObservers = new ArrayList<>();// observer

    List<Shape> undoneShapes = new ArrayList<>();

    // getters
    public List<Shape> getUndoneShapes() {
        return undoneShapes;
    }



    public static void group() {
        
    }

    public static void unGroup() {
        // iShapeSelected.clear();
    }



    ////////////////////////////////////////

    // ----------------DRAW SHAPES FOR PAINTCANVAS--------------------
    public static List<Shape> getCanvas(){
        return canvasShapes;
    }
    public static List<IShape> getICanavs(){
        return canvasIShapes;
    }

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

    public void removeIShapeFromCanvas(IShape iShape){
        canvasIShapes.remove(iShape);
    }

    public void removeShapeFromCanvas(Shape shape){
        canvasShapes.remove(shape);
    }

    // ----------------COLOR & SHADING INFO-------------------------
    private static final List<ShapeInfo> shapeInfoList = new ArrayList<>();

    public static List<ShapeInfo> getShapeInfoList(){
        return shapeInfoList;
    }
    public void addToShapeInfo (ShapeInfo shapeData){ shapeInfoList.add(shapeData); }
    public void removeShapeInfo (ShapeInfo shapeInfo) { shapeInfoList.remove(shapeInfo); }


    // ----------------SELECT SHAPES--------------------------------
    // Selected Shapes & IShapes
    private static final List<IShape> iSelectedShapes = new ArrayList<>();
    public List<IShape> getISelectedShapes() {
        return iSelectedShapes;
    }
    // add to selected Shapes
    public void addISelectedShapes(IShape iShape){
        iSelectedShapes.add(iShape);
    }
    // empty the selectedShapes
    public void clearISelectedShapes(){
        iSelectedShapes.clear();
    }
    // return the size of the selectedShapes
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
    public void clearSelectedShapes(){
        selectedShapes.clear();
    }


    // ----------------COPY SHAPES----------------------------------
    // register the copied IShapes from selected IShapes
    public static void copy(){
        // register Copy Pieces
        CopyShapeList copyShapeListHandler = new CopyShapeList(iSelectedShapes);
        copyShapeListHandler.clearEarlierCopyObservers();

        // System.out.println(":::::"+iSelectedShapes.size());
        for (IShape iSelectedShape : iSelectedShapes) {
            copyShapeListHandler.registerObserver(iSelectedShape);
        }
        System.out.println(copyShapeListHandler.sizeCopyObservers());
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
        pasteShapeList pasteShapeListHandler = new pasteShapeList(iSelectedShapes);
        // System.out.println(":::::"+iSelectedShapes.size());

        for (IShape iSelectedShapes : iSelectedShapes) {
            pasteShapeListHandler.registerObserver(iSelectedShapes);
        }
        pasteShapeListHandler.start();
    }


    // ----------------DELETE SHAPES----------------------------------
    // register the IShapes to be deleted from selected IShapes
    public static void delete(){
        deleteShapeList deleteShapeListHandler = new deleteShapeList(iSelectedShapes);
        for (IShape iSelectedShapes : iSelectedShapes) {
            deleteShapeListHandler.registerObserver(iSelectedShapes);
        }
        deleteShapeListHandler.start();
    }

}
