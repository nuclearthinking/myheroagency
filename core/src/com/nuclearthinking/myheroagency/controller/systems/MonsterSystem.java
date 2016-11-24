package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.Components;
import com.nuclearthinking.myheroagency.model.actor.base.AnimationState;
import com.nuclearthinking.myheroagency.model.actor.base.BodyComponent;
import com.nuclearthinking.myheroagency.model.actor.base.MovementComponent;
import com.nuclearthinking.myheroagency.model.actor.base.StateComponent;
import com.nuclearthinking.myheroagency.model.actor.monster.MonsterComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 03.10.2016.
 */
public final class MonsterSystem extends IteratingSystem {

    private static final Family family = Family.all(MonsterComponent.class,
                                                    StateComponent.class,
                                                    BodyComponent.class,
                                                    MovementComponent.class).get();

    private float accelX = 0.0f;

    public MonsterSystem() {
        super(family);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        @NonNull val monster = Components.MONSTER.get(entity);
        @NonNull val pos = Components.BODY.get(entity);
        @NonNull val target = Components.BODY.get(monster.getTarget());
        @NonNull val mov = Components.MOVEMENT.get(entity);
        @NonNull val state = Components.STATE.get(entity);

        accelX = target.getBody().getPosition().x - pos.getBody().getPosition().x;

        if(accelX == 10.0f && state.getState() != AnimationState.IDLE.getValue()){
            mov.getVelocity().x = 0;
            state.set(AnimationState.IDLE.getValue());
        }

        if (accelX > 10.0f && state.getState() != AnimationState.RIGHT.getValue()) {
            mov.getVelocity().x = monster.getSpeed();
            state.set(AnimationState.RIGHT.getValue());
        }

        if (accelX < -10.0f && state.getState() != AnimationState.LEFT.getValue()) {
            mov.getVelocity().x = -monster.getSpeed();
            state.set(AnimationState.LEFT.getValue());
        }
    }
}
