package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.nuclearthinking.myheroagency.model.Components;
import com.nuclearthinking.myheroagency.model.components.BodyComponent;
import com.nuclearthinking.myheroagency.model.components.NpcComponent;
import com.nuclearthinking.myheroagency.model.components.StateComponent;
import com.nuclearthinking.myheroagency.model.components.TouchComponent;
import com.nuclearthinking.myheroagency.model.quest.Quest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Created by mkuksin on 26.09.2016.
 */
@Slf4j(topic = "NpcSystem")
public final class NpcSystem extends ActorSystem implements Speaker{
    private static final Family family = Family.all(StateComponent.class,
                                                    NpcComponent.class,
                                                    BodyComponent.class,
                                                    TouchComponent.class).get();

    public NpcSystem() {
        super(family);
    }

    private @Getter @Setter boolean touch = false;

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        @NonNull val t = Components.TOUCH.get(entity);

        if(touch){
            t.setTouch(touch);
            t.setActor(this);
            touch = false;
        }
        else
            t.setTouch(touch);
    }

    @Override
    public void showDialog(@NonNull final PlayerSystem player, final String command){
        try {
            if(command == null || command.length() == 0){
                log.info("Player " + player.getLevel() + " starting dialog with " + this.getActor().getName());
            }
            else if(command.equalsIgnoreCase("openWindow")){
                log.info("Player " + player.getLevel() + " use command " + "[" + command + "]");
            }
            else if (command.startsWith("quest")){
                log.info("Player " + player.getLevel() + " use command " + "[" + command + "]");

                final String quest = command.substring(5).trim();
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

        val quest = Quest.getQ(questId);
        quest.notifyTalk(this);
    }

    @Override
    public boolean isNpc(){
        return true;
    }
}
