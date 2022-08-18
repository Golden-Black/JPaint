package model.interfaces;

public interface IEditSubject {
    void registerObserver(IShape observer);
    void removeObserver(IShape observer);
}
