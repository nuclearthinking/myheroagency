package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.nuclearthinking.myheroagency.model.entity.components.Components;
import com.nuclearthinking.myheroagency.model.entity.components.MovementComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class MovementSystem extends IteratingSystem {
    private final Vector2 tmp = new Vector2();

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class).get());
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        @NonNull val pos = Components.TRANSFORM.get(entity);
        @NonNull val mov = Components.MOVEMENT.get(entity);

        tmp.set(mov.getAccel()).scl(deltaTime);
        mov.getVelocity().add(tmp);

        tmp.set(mov.getVelocity()).scl(deltaTime);
        pos.getPos().add(tmp.x, tmp.y, 0.0f);
    }
}
