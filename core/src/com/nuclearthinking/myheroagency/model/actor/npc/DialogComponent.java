package com.nuclearthinking.myheroagency.model.actor.npc;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.model.actor.base.GameActor;
import com.nuclearthinking.myheroagency.model.actor.player.PlayerComponent;
import com.nuclearthinking.myheroagency.model.quest.Quest;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import com.nuclearthinking.myheroagency.model.ui.hud.HudComponent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Created by mkuksin on 24.11.2016.
 */
@Slf4j(topic = "DialogComponent")
public class DialogComponent implements Component {

    private @Getter @Setter Dialog dialog;
    private @Getter TextButton questButton = new TextButton("Quest", UiFactory.getSkin());

    public void show(@NonNull final PlayerComponent player, @NonNull final NpcComponent npc, @NonNull final String command){
        try {
            if(command == null || command.length() == 0){
                dialog.text("Player " + player.getName() + " starting dialog with " + npc.getName());
            }
            else if(command.equalsIgnoreCase("openWindow")){
                log.info("Player " + player.getName() + " use command " + "[" + command + "]");
                dialog.getContentTable().clear();
                dialog.text("Welcome friend! What do you want ?");
            }
            else if (command.startsWith("quest")){
                log.info("Player " + player.getName() + " use command " + "[" + command + "]");

                final String quest = command.substring(5).trim();
                if (quest.length() > 0) {
                    showQuestWindow(player, npc, quest);
                }
            }
        }
        catch (StringIndexOutOfBoundsException e){
            log.info("Incorrect command: " + "[" + command + "]" + npc.getId());
        }
        dialog.show(HudComponent.getStage());
    }

    private void showQuestWindow(@NonNull final GameActor player, @NonNull final NpcComponent npc, final String questId){
        // TODO: должно быть что то такое
        //Quest q = ((PlayerComponent)player.getActor()).getQuest(questId);
        //q.notifyTalk(this);

        dialog.getContentTable().clear();
        dialog.text(Quest.getQ(questId).getQuest().getDescription());

        val quest = Quest.getQ(questId);
        quest.notifyTalk(npc);
    }
}
