package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.nuclearthinking.myheroagency.model.components.BodyComponent;
import com.nuclearthinking.myheroagency.model.components.NpcComponent;
import com.nuclearthinking.myheroagency.model.components.StateComponent;
import com.nuclearthinking.myheroagency.model.quest.Quest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 26.09.2016.
 */
@Slf4j(topic = "NpcSystem")
public final class NpcSystem extends ActorSystem {
    private static final Family family = Family.all(StateComponent.class,
                                                    NpcComponent.class,
                                                    BodyComponent.class).get();

    public NpcSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

    public void showDialog(@NonNull final PlayerSystem player, final String command){
        try {
            if(command.equalsIgnoreCase("openWindow")){
                log.info("Player " + player.getLevel() + " use command " + "[" + command + "]");
            }
            else if (command.startsWith("quest")){
                log.info("Player " + player.getLevel() + " use command " + "[" + command + "]");
                String quest = command.substring(5).trim();
                if (quest.length() > 0) {
                    showQuestWindow(player, quest);
                }
            }
        }
        catch (StringIndexOutOfBoundsException e){
            log.info("Incorrect command: " + "[" + command + "]" + ((NpcComponent)actor).getId());
        }
    }

    private void showQuestWindow(@NonNull final  PlayerSystem player, final String questId){
        // TODO: должно быть что то такое
        //Quest q = ((PlayerComponent)player.getActor()).getQuest(questId);
        //q.notifyTalk(this);

        Quest quest = Quest.getQ(questId);
        quest.notifyTalk(this);
    }

    @Override
    public boolean isNpc(){
        return true;
    }

}
