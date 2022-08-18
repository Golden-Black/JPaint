package model.editShapes;

import model.interfaces.IEditSubject;
import model.interfaces.IShape;

import java.util.ArrayList;
import java.util.List;

public class deleteShapeList implements IEditSubject {

    List<IShape> selectedIShapes;
    private final List<IShape> deleteObservers = new ArrayList<>();

    public deleteShapeList(List<IShape> selectedIShapes) {
        this.selectedIShapes = selectedIShapes;
    }

    public void start() {
        notifyCopyObservers();
    }



    @Override
    public void registerObserver(IShape observer) {
        deleteObservers.add(observer);
    }

    @Override
    public void removeObserver(IShape observer) {
        deleteObservers.remove(observer);
    }

    private void notifyCopyObservers() {
        for (var n : deleteObservers){
            n.delete();
        }
    }
}
