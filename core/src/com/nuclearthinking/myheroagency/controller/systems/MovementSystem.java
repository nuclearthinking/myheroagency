package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.Components;
import com.nuclearthinking.myheroagency.model.components.BodyComponent;
import com.nuclearthinking.myheroagency.model.components.MovementComponent;
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

        if(mov.isJump()) {
            body.getBody().applyLinearImpulse(0, 0.4f, body.getBody().getPosition().x, body.getBody().getPosition().y, true);
            mov.setJump(false);
        }
    }
}
