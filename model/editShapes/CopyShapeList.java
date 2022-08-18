package model.editShapes;

import model.ShapeList;
import model.interfaces.IEditSubject;
import model.interfaces.IShape;

import java.util.ArrayList;
import java.util.List;

public class CopyShapeList implements IEditSubject {

    List<IShape> selectedIShapes;
    private final List<IShape> copyObservers = new ArrayList<>();

    public void start() {
        notifyCopyObservers();
    }

    public CopyShapeList(List<IShape> selectedIShapes) {
        this.selectedIShapes = selectedIShapes;
    }

    @Override
    public void registerObserver(IShape observer) {
        copyObservers.add(observer);
    }

    @Override
    public void removeObserver(IShape observer) {
        copyObservers.remove(observer);
    }

    void notifyCopyObservers(){
        for (var n : copyObservers){
            n.copy();
        }
    }


}
