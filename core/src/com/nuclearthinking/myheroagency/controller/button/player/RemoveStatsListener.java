package com.nuclearthinking.myheroagency.controller.button.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.model.entity.systems.ActorSystem;
import lombok.NonNull;

/**
 * Created by Izonami on 10.08.2016.
 */
public class RemoveStatsListener extends AbstractButtonListener {

    private final ActorSystem actor;

    public RemoveStatsListener(@NonNull final TextButton button, @NonNull final ActorSystem actor) {
        super(button);
        this.actor = actor;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(event.getListenerActor() == button){
            if(actor.getBaseCON() >= 1){
                actor.setCon(actor.getBaseCON() - 1);
            }
        }
    }

}
