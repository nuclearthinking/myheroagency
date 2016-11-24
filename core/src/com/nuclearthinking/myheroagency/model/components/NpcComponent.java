package com.nuclearthinking.myheroagency.model.components;

import com.nuclearthinking.myheroagency.controller.systems.PlayerSystem;
import com.nuclearthinking.myheroagency.controller.systems.Speaker;
import com.nuclearthinking.myheroagency.model.quest.Quest;
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
    public void showDialog(@NonNull PlayerSystem actor, String command) {
        val player = getPlayer(actor);
        try {
            if(command == null || command.length() == 0){
                log.info("Player " + player.getLvl() + " starting dialog with " + name);
                log.info(super.toString());
            }
            else if(command.equalsIgnoreCase("openWindow")){
                log.info("Player " + player.getLvl() + " use command " + "[" + command + "]");
            }
            else if (command.startsWith("quest")){
                log.info("Player "/* + player.getLevel()*/ + " use command " + "[" + command + "]");

                final String quest = command.substring(5).trim();
                if (quest.length() > 0) {
                    showQuestWindow(player, quest);
                }
            }
        }
        catch (StringIndexOutOfBoundsException e){
            log.info("Incorrect command: " + "[" + command + "]" + id);
        }
    }

    private void showQuestWindow(@NonNull final GameActor player, final String questId){
        // TODO: должно быть что то такое
        //Quest q = ((PlayerComponent)player.getActor()).getQuest(questId);
        //q.notifyTalk(this);

        val quest = Quest.getQ(questId);
        quest.notifyTalk(this);
    }

    private PlayerComponent getPlayer(@NonNull final PlayerSystem player){
        return player.getEntities().first().getComponent(PlayerComponent.class);
    }

    @Override
    public boolean isNpc(){
        return true;
    }
}
