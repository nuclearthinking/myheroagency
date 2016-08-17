package com.nuclearthinking.myheroagency.controller.button.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.controller.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.controller.observer.Observable;
import com.nuclearthinking.myheroagency.controller.observer.Observer;
import com.nuclearthinking.myheroagency.controller.observer.ObserverCon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izonami on 10.08.2016.
 */
public class RemoveStatsListener extends AbstractButtonListener implements Observable {

    private final ObjectManager objectManager;
    private final List<Observer> subscribers;

    public RemoveStatsListener(final TextButton button, final ObjectManager objectManager) {
        super(button);
        this.objectManager = objectManager;
        this.subscribers = new ArrayList<Observer>();
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(event.getListenerActor() == button){
            if(objectManager.getPlayer().getCON() >= 1){
                objectManager.getPlayer().setCon((byte)-1);
                notifyLayer();
            }
        }
    }

    @Override
    public void subscribe(Observer stats) {
        subscribers.add(stats);
    }

    @Override
    public void unsubscribe(Observer stats) {
        int index = subscribers.indexOf(stats);

        if(index >= 0){
            subscribers.remove(index);
        }
    }

    @Override
    public void notifyLayer() {
        for(Observer observerStats : subscribers){
            if(observerStats instanceof ObserverCon)
                ((ObserverCon) observerStats).updateHp();
            else
                observerStats.update();
        }
    }
}
