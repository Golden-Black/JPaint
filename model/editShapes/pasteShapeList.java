package model.editShapes;

import model.interfaces.IEditSubject;
import model.interfaces.IShape;

import java.util.ArrayList;
import java.util.List;

public class pasteShapeList implements IEditSubject {

    List<IShape> copiedIShapes;
    private final List<IShape> pasteObservers = new ArrayList<>();

    public pasteShapeList(List<IShape> copiedIShapes) {
        this.copiedIShapes = copiedIShapes;
    }

    public void start() {
        notifyCopyObservers();
    }

    @Override
    public void registerObserver(IShape observer) {
        pasteObservers.add(observer);
    }

    @Override
    public void removeObserver(IShape observer) {
        pasteObservers.add(observer);
    }

    private void notifyCopyObservers() {
        for (var n : pasteObservers){
            n.paste();
        }
    }
}
