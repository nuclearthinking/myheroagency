package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.nuclearthinking.myheroagency.model.entity.components.BodyComponent;
import com.nuclearthinking.myheroagency.model.entity.components.Components;
import com.nuclearthinking.myheroagency.model.entity.components.MovementComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class MovementSystem extends IteratingSystem {
    private static final Family family = Family.all(TransformComponent.class,
                                                    BodyComponent.class,
                                                    MovementComponent.class).get();
    private final Vector2 tmp = new Vector2();

    public MovementSystem() {
        super(family);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        @NonNull val pos = Components.TRANSFORM.get(entity);
        @NonNull val mov = Components.MOVEMENT.get(entity);
        @NonNull val body = Components.BODY.get(entity);

        mov.getVelocity().y = body.getBody().getLinearVelocity().y;

        body.getBody().setLinearVelocity(mov.getVelocity());
        tmp.set(body.getBody().getLinearVelocity().scl(deltaTime));
        pos.getPos().add(tmp.x, tmp.y, 0.0f);
    }
}
