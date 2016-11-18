package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.nuclearthinking.myheroagency.model.components.BodyComponent;
import com.nuclearthinking.myheroagency.model.components.NpcComponent;
import com.nuclearthinking.myheroagency.model.components.StateComponent;
import com.nuclearthinking.myheroagency.model.components.TouchComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 26.09.2016.
 */
@Slf4j(topic = "NpcSystem")
public final class NpcSystem extends ActorSystem {

    private static final Family family = Family.all(StateComponent.class,
                                                    NpcComponent.class,
                                                    BodyComponent.class,
                                                    TouchComponent.class).get();

    public NpcSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    }

    @Override
    public boolean isNpc(){
        return true;
    }
}
