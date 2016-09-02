package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.components.Components;
import com.nuclearthinking.myheroagency.model.entity.components.StateComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class StateSystem extends IteratingSystem {
    public StateSystem() {
        super(Family.all(StateComponent.class).get());
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        @NonNull val sc = Components.STATE.get(entity);

        sc.setTime(sc.getTime() + deltaTime);
    }

}
