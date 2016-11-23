package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.Components;
import com.nuclearthinking.myheroagency.model.components.TouchComponent;
import lombok.val;

/**
 * Created by mkuksin on 08.11.2016.
 */
public class TouchSystem extends IteratingSystem {

    private static final Family family = Family.all(TouchComponent.class).get();

    public TouchSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val touch = Components.TOUCH.get(entity);
        val npc = Components.NPC.get(entity);

        //TODO: Нужно добавить тип нпс и проверять наличие возможных действий с ним
        if(touch.isTouch()){
            System.out.println(npc.toString());
            //player.talkTo(touch.getActor(), "openWindow");
            touch.setTouch(false);
        }
    }
}
