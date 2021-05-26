package DesignPatterns.ObserverPatternExample;

/**
 * Created by rkhurana on 11/11/18.
 */
public interface Subject {
    void Attach(Observer o);
    void Dettach(Observer o);
    void Notify();
}
