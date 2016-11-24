package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.actor.base.BodyComponent;
import com.nuclearthinking.myheroagency.model.actor.base.StateComponent;
import com.nuclearthinking.myheroagency.model.actor.npc.NpcComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 26.09.2016.
 */
@Slf4j(topic = "NpcSystem")
public final class NpcSystem extends IteratingSystem {

    private static final Family family = Family.all(StateComponent.class,
                                                    NpcComponent.class,
                                                    BodyComponent.class).get();

    public NpcSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    }
}
