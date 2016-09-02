package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.AnimationState;
import com.nuclearthinking.myheroagency.model.entity.World;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import lombok.Setter;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class PlayerSystem extends IteratingSystem {
    private static final Family family = Family.all(StateComponent.class,
                                                    PlayerComponent.class,
                                                    TransformComponent.class,
                                                    MovementComponent.class).get();

    private @Setter float accelX = 0.0f;

    public PlayerSystem() {
        super(family);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        accelX = 0.0f;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val t = Components.TRANSFORM.get(entity);
        val state = Components.STATE.get(entity);
        val mov = Components.MOVEMENT.get(entity);
        val player = Components.PLAYER.get(entity);

        if(accelX == 0.0f){
            mov.getVelocity().x = 0;
            state.set(AnimationState.IDLE.getValue());
        }

        if (accelX > 0.0f && state.getState() != AnimationState.RIGHT.getValue()) {
            mov.getVelocity().x = player.getSpeed();
            state.set(AnimationState.RIGHT.getValue());
        }

        if (accelX < 0.0f && state.getState() != AnimationState.LEFT.getValue()) {
            mov.getVelocity().x = -player.getSpeed();
            state.set(AnimationState.LEFT.getValue());
        }

        if (t.getPos().x <= World.WORLD_BORDER_L) {
            t.getPos().x = World.WORLD_BORDER_L;
        }

        if (t.getPos().x > World.WORLD_BORDER_R) {
            t.getPos().x = World.WORLD_BORDER_R;
        }
    }

}
