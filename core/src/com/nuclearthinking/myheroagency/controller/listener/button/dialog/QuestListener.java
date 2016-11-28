package com.nuclearthinking.myheroagency.controller.listener.button.dialog;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.listener.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.model.actor.npc.DialogComponent;
import com.nuclearthinking.myheroagency.model.actor.npc.NpcComponent;
import com.nuclearthinking.myheroagency.model.actor.player.PlayerComponent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 28.11.2016.
 */
@Slf4j(topic = "QuestListener")
public class QuestListener extends AbstractButtonListener {

    private final DialogComponent dialog;
    private final PlayerComponent player;
    private final NpcComponent npc;
    private final String command;

    public QuestListener(@NonNull final DialogComponent dialog,
                         @NonNull final TextButton button,
                         @NonNull final PlayerComponent player,
                         @NonNull final NpcComponent npc,
                         @NonNull final String command) {
        super(button);

        this.dialog = dialog;
        this.player = player;
        this.npc = npc;
        this.command = command;
        log.debug("Command " + command);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        dialog.show(player, npc, command);
    }
}
