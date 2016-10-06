package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.Components;
import com.nuclearthinking.myheroagency.model.entity.components.BodyComponent;
import com.nuclearthinking.myheroagency.model.entity.components.MovementComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class MovementSystem extends IteratingSystem {
    private static final Family family = Family.all(BodyComponent.class,
                                                    MovementComponent.class).get();

    public MovementSystem() {
        super(family);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        @NonNull val mov = Components.MOVEMENT.get(entity);
        @NonNull val body = Components.BODY.get(entity);

        mov.getVelocity().y = body.getBody().getLinearVelocity().y;

        body.getBody().setLinearVelocity(mov.getVelocity());
    }
}
