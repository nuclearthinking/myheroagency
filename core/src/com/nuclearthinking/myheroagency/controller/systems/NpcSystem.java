package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.nuclearthinking.myheroagency.model.Components;
import com.nuclearthinking.myheroagency.model.actor.base.BodyComponent;
import com.nuclearthinking.myheroagency.model.actor.base.StateComponent;
import com.nuclearthinking.myheroagency.model.actor.npc.NpcComponent;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Created by mkuksin on 26.09.2016.
 */
@Slf4j(topic = "NpcSystem")
public final class NpcSystem extends IteratingSystem {

    private static final Family family = Family.all(StateComponent.class,
                                                    NpcComponent.class,
                                                    BodyComponent.class).get();

    private final Vector3 posNpc = new Vector3();

    public NpcSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val name = Components.NAME.get(entity);
        val pos = Components.BODY.get(entity);

        int npcPosX = (int) pos.getBody().getPosition().x;
        int npcPosY = (int) pos.getBody().getPosition().y;

        posNpc.set(npcPosX, npcPosY, 0);
        getEngine().getSystem(RenderingSystem.class).getCamera().project(posNpc);

        int labelPosX = (int)(posNpc.x - 15);
        int labelPosY = (int)(posNpc.y + 15);
        name.getName().setPosition(labelPosX, labelPosY);
    }
}
