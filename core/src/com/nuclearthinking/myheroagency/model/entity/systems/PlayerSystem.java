package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.nuclearthinking.myheroagency.model.entity.AnimationState;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import lombok.Setter;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class PlayerSystem extends ObjectSystem {
    private static final Family family = Family.all(StateComponent.class,
                                                    PlayerComponent.class,
                                                    TransformComponent.class,
                                                    MovementComponent.class,
                                                    FunctionComponent.class).get();

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

        if(accelX == 0.0f && state.getState() != AnimationState.IDLE.getValue()){
            mov.getVelocity().x = 0;
            state.set(AnimationState.IDLE.getValue());
        }

        if (accelX > 0.0f && state.getState() != AnimationState.RIGHT.getValue()) {
            mov.getVelocity().x = getSpeed();
            state.set(AnimationState.RIGHT.getValue());
        }

        if (accelX < 0.0f && state.getState() != AnimationState.LEFT.getValue()) {
            mov.getVelocity().x = -getSpeed();
            state.set(AnimationState.LEFT.getValue());
        }

        checkBorderWorld(t);
    }

    private void checkBorderWorld(final TransformComponent t){
        final int borderLeft = MapComponent.getLevelPixelWidth() - MapComponent.getLevelPixelHeight();
        final int borderRight = MapComponent.getLevelPixelWidth();
        final int borderUp = MapComponent.getLevelPixelHeight();
        final int borderDown = MapComponent.getLevelPixelHeight() - MapComponent.getLevelPixelHeight();

        if (t.getPos().x <= borderLeft) {
            t.getPos().x = borderLeft;
        }

        if (t.getPos().x > borderRight) {
            t.getPos().x = borderRight;
        }

        if(t.getPos().y > borderUp){
            t.getPos().y = borderUp;
        }

        if(t.getPos().y <= borderDown){
            t.getPos().y = borderDown;
        }
    }

    @Override
    public int getLevel(){
        return PlayerComponent.getLevel();
    }

    @Override
    public int getBaseSTR(){
        return PlayerComponent.getBaseSTR();
    }

    @Override
    public int getBaseINT(){
        return PlayerComponent.getBaseINT();
    }

    @Override
    public int getBaseCON(){
        return PlayerComponent.getBaseCON();
    }

    @Override
    public int getBaseMEN(){
        return PlayerComponent.getBaseMEN();
    }

    @Override
    public int getBaseWIT(){
        return PlayerComponent.getBaseWIT();
    }

    @Override
    public int getBaseDEX(){
        return PlayerComponent.getBaseDEX();
    }

    @Override
    public boolean isPlayer(){
        return true;
    }

}
