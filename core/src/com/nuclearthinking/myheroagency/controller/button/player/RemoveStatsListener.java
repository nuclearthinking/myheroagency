package com.nuclearthinking.myheroagency.controller.button.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.controller.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.controller.observer.Observable;
import com.nuclearthinking.myheroagency.controller.observer.Observer;
import com.nuclearthinking.myheroagency.controller.observer.ObserverCon;
import com.nuclearthinking.myheroagency.controller.observer.ObserverMen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izonami on 10.08.2016.
 */
public class RemoveStatsListener extends AbstractButtonListener {

    private final ObjectManager objectManager;

    public RemoveStatsListener(final TextButton button, final ObjectManager objectManager) {
        super(button);
        this.objectManager = objectManager;

    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(event.getListenerActor() == button){
            if(objectManager.getPlayer().getCON() >= 1){
                objectManager.getPlayer().setCon((byte)-1);
                notifyObservers();
            }
        }
    }

    @Override
    public void notifyObservers() {
        for(Observer observerStats : observers){
            if(observerStats instanceof ObserverCon)
                ((ObserverCon) observerStats).updateHp();
            if(observerStats instanceof ObserverMen)
                ((ObserverMen)observerStats).updateMp();
            else
                observerStats.update();
        }
    }
}
