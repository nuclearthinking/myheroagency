package com.nuclearthinking.myheroagency.controller.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.controller.ObservableStats;
import com.nuclearthinking.myheroagency.ui.hud.layer.ObserverStats;

/**
 * Created by Izonami on 10.08.2016.
 */
public class PlusConListener extends AbstractButtonListener implements ObservableStats{

    private final ObjectManager objectManager;

    public PlusConListener(final TextButton button, final ObjectManager objectManager) {
        super(button);
        this.objectManager = objectManager;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(objectManager.getPlayer().getCON() <= 98){
            objectManager.getPlayer().setCon((byte)1);
            System.out.println(objectManager.getPlayer().getCON());
            System.out.println(objectManager.getPlayer().getHp());
            notifyLayer();
        }
    }

    @Override
    public void subscribe(ObserverStats stats) {
        statsList.add(stats);
    }

    @Override
    public void unsubscribe(ObserverStats stats) {

    }

    @Override
    public void notifyLayer() {
        System.out.println(statsList.size());
        for (int i = 0; i < statsList.size(); i++) {
            statsList.get(i).update();
        }
    }
}
