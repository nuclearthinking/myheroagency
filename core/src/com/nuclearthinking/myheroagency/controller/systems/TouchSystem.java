package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.components.TouchComponent;

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

    }
}
