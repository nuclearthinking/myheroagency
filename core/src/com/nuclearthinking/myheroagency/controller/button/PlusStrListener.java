package com.nuclearthinking.myheroagency.controller.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ObjectManager;

/**
 * Created by Izonami on 10.08.2016.
 */
public class PlusStrListener extends AbstractButtonListener {

    private final ObjectManager objectManager;

    public PlusStrListener(final TextButton button, final ObjectManager objectManager) {
        super(button);
        this.objectManager = objectManager;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        objectManager.getPlayer().setCon((byte)60);
        System.out.println(objectManager.getPlayer().getCON());
        System.out.println(objectManager.getPlayer().getHp());
    }
}
