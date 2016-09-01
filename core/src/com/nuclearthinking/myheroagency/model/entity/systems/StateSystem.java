package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.components.StateComponent;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class StateSystem extends IteratingSystem {
    private ComponentMapper<StateComponent> sm;

    public StateSystem() {
        super(Family.all(StateComponent.class).get());

        sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        sm.get(entity).time += deltaTime;
    }

}
