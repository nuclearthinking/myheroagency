package com.nuclearthinking.myheroagency.model.actor.npc;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.listener.button.dialog.QuestListener;
import com.nuclearthinking.myheroagency.model.actor.base.GameActor;
import com.nuclearthinking.myheroagency.model.actor.player.PlayerComponent;
import com.nuclearthinking.myheroagency.model.quest.Quest;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import com.nuclearthinking.myheroagency.model.ui.hud.HudComponent;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Created by mkuksin on 24.11.2016.
 */
@Slf4j(topic = "DialogComponent")
public class DialogComponent implements Component {

    private @Getter Dialog dialog = new Dialog("Dialog", UiFactory.getSkin(), "dialog-kramola");
    private @Getter TextButton questButton = new TextButton("Quest", UiFactory.getSkin(), "kramola");
    private @Getter Label text = new Label("text",UiFactory.getSkin(), "kramola");

    public void show(@NonNull final PlayerComponent player, @NonNull final NpcComponent npc, @NonNull final String command) {
        if(command == null || command.length() == 0) {
            dialog.text("Something wrong, " + "Player " + player.getName() + " use command " + "[" + command + "]");
        }
        else if(command.equalsIgnoreCase("openWindow")) {
            log.info("Player " + player.getName() + " use command " + "[" + command + "]");
            dialog.getContentTable().clear();
            text.setText("Welcome friend! What do you want ? Дебажичек fg ыв в п ыцп фй фукр фур фуру фру ");
            text.setFontScale(.5f);
            dialog.text(text);
            if(npc.getQuests().length > 0){
                dialog.getButtonTable().clear();
                dialog.getButtonTable().clearListeners();
                dialog.button(questButton);
                questButton.addListener(new QuestListener(this, questButton, player, npc, "quest" + npc.getQuests()[0]));
            }
        }
        else if (command.startsWith("quest")) {
            try {
                log.info("Player " + player.getName() + " use command " + "[" + command + "]");
                dialog.getButtonTable().removeActor(questButton);
                dialog.getButtonTable().clearListeners();
                final String quest = command.substring(5).trim();
                if (quest.length() > 0) {
                    showQuestWindow(player, npc, quest);
                }
            }
            catch (NullPointerException e){
                log.error("Incorrect command " + "[" + command + "]");
            }
        }
        else {
            dialog.text("Something wrong, " + "Player " + player.getName() + " use unrecognized command " + "[" + command + "]");
        }
        dialog.show(HudComponent.getStage());
    }

    private void showQuestWindow(@NonNull final GameActor player, @NonNull final NpcComponent npc, final String questId){
        // TODO: должно быть что то такое
        //Quest q = ((PlayerComponent)player.getActor()).getQuest(questId);
        //q.notifyTalk(this);

        dialog.getContentTable().clear();
        text.setText(Quest.getQ(questId).getQuest().getDescription());
        dialog.text(text);

        val quest = Quest.getQ(questId);
        quest.notifyTalk(npc);
    }
}
