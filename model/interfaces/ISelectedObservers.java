package model.interfaces;

public interface ISelectedObservers {
    void registerObserver(IShape observer);
    void removeObserver(IShape observer);
}
