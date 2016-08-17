package com.nuclearthinking.myheroagency.controller.observer;

/**
 * Created by Izonami on 15.08.2016.
 */
public interface Observable {
    void subscribe(Observer stats);
    void unsubscribe(Observer stats);
    void notifyLayer();
}
