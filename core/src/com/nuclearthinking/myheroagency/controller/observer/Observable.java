package com.nuclearthinking.myheroagency.controller.observer;

/**
 * Created by Izonami on 15.08.2016.
 */
public interface Observable {

    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers();

}
