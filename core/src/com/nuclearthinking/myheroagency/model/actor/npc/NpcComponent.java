package com.nuclearthinking.myheroagency.model.actor.npc;

import com.nuclearthinking.myheroagency.controller.systems.PlayerSystem;
import com.nuclearthinking.myheroagency.controller.systems.Speaker;
import com.nuclearthinking.myheroagency.model.actor.base.GameActor;
import com.nuclearthinking.myheroagency.model.actor.player.PlayerComponent;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Created by mkuksin on 26.09.2016.
 */
@Slf4j(topic = "NpcComponent")
@ToString
public final class NpcComponent extends GameActor implements Speaker {

    public NpcComponent(){
        super();
    }

    @Override
    public void showDialog(@NonNull final PlayerSystem actor, @NonNull final DialogComponent dialog, final String command) {
        val player = getPlayer(actor);
        dialog.show(player, this, command);
    }

    private PlayerComponent getPlayer(@NonNull final PlayerSystem player){
        return player.getEntities().first().getComponent(PlayerComponent.class);
    }

    @Override
    public boolean isNpc(){
        return true;
    }
}
