package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.components.Components;
import com.nuclearthinking.myheroagency.model.entity.components.GravityComponent;
import com.nuclearthinking.myheroagency.model.entity.components.MovementComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 02.09.2016.
 */
public class GravitySystem extends IteratingSystem {
    private static final Family family = Family.all(GravityComponent.class,
                                                    MovementComponent.class).get();

    public GravitySystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        @NonNull val mov = Components.MOVEMENT.get(entity);
        @NonNull val gc = Components.GRAVITY.get(entity);

        //Накладывает постоянное давление на объекты с GravityComponent
        mov.getVelocity().add(gc.getGravity().x * deltaTime, gc.getGravity().y * deltaTime);
    }
}
