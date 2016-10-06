package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.nuclearthinking.myheroagency.model.entity.AnimationState;
import com.nuclearthinking.myheroagency.model.entity.Components;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 03.10.2016.
 */
public class MonsterSystem extends ActorSystem {

    private static final Family family = Family.all(MonsterComponent.class,
                                                    StateComponent.class,
                                                    TransformComponent.class,
                                                    MovementComponent.class,
                                                    FunctionComponent.class).get();

    private float accelX = 0.0f;

    public MonsterSystem() {
        super(family);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        @NonNull val monster = Components.MONSTER.get(entity);
        @NonNull val pos = Components.TRANSFORM.get(entity);
        @NonNull val target = Components.TRANSFORM.get(monster.getTarget());
        @NonNull val mov = Components.MOVEMENT.get(entity);
        @NonNull val state = Components.STATE.get(entity);

        accelX = target.getPos().x - pos.getPos().x;

        if(accelX == 10.0f && state.getState() != AnimationState.IDLE.getValue()){
            mov.getVelocity().x = 0;
            state.set(AnimationState.IDLE.getValue());
        }

        if (accelX > 10.0f && state.getState() != AnimationState.RIGHT.getValue()) {
            mov.getVelocity().x = getSpeed();
            state.set(AnimationState.RIGHT.getValue());
        }

        if (accelX < -10.0f && state.getState() != AnimationState.LEFT.getValue()) {
            mov.getVelocity().x = -getSpeed();
            state.set(AnimationState.LEFT.getValue());
        }

    }

    public int getSpeed(){
        return (int) calcStat(Stats.RUN_SPEED, MonsterComponent.BASE_RUN_SPD);
    }

    @Override
    public boolean isMonster(){
        return true;
    }

}
