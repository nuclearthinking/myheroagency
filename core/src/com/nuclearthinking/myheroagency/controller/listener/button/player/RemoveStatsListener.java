package com.nuclearthinking.myheroagency.controller.listener.button.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.listener.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.model.actor.base.GameActor;
import lombok.NonNull;

/**
 * Created by Izonami on 10.08.2016.
 */
public class RemoveStatsListener extends AbstractButtonListener {

    private final GameActor actor;

    public RemoveStatsListener(@NonNull final TextButton button, @NonNull final GameActor actor) {
        super(button);
        this.actor = actor;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(event.getListenerActor() == button){
            if(actor.getCON() >= 1){
                actor.setCON(actor.getCON() - 1);
            }
        }
    }

}
