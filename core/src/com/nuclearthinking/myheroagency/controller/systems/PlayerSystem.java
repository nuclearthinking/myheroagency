package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.nuclearthinking.myheroagency.model.AnimationState;
import com.nuclearthinking.myheroagency.model.Components;
import com.nuclearthinking.myheroagency.model.components.*;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public final class PlayerSystem extends ActorSystem {
    private static final Family family = Family.all(StateComponent.class,
                                                    PlayerComponent.class,
                                                    BodyComponent.class,
                                                    MovementComponent.class).get();

    private @Setter byte accelX = 0;
    private @Setter byte accelY = 0;

    public PlayerSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        @NonNull val state = Components.STATE.get(entity);
        @NonNull val mov = Components.MOVEMENT.get(entity);
        @NonNull val body = Components.BODY.get(entity);

        if(accelX == 0 && state.getState() != AnimationState.IDLE.getValue()){
            mov.getVelocity().x = 0;
            state.set(AnimationState.IDLE.getValue());
        }

        if (accelX > 0 && state.getState() != AnimationState.RIGHT.getValue()) {
            mov.getVelocity().x = getSpeed();
            state.set(AnimationState.RIGHT.getValue());
        }

        if (accelX < 0 && state.getState() != AnimationState.LEFT.getValue()) {
            mov.getVelocity().x = -getSpeed();
            state.set(AnimationState.LEFT.getValue());
        }

        //TODO: Включить когда появится гравитация
        if (accelY > 0 && state.getState() != AnimationState.LEFT.getValue() ||
                            state.getState() != AnimationState.RIGHT.getValue()){
            //mov.setJump(true);
            //state.set(AnimationState.IDLE.getValue());
        }

        checkBorderWorld(body);
    }

    public void talkTo(@NonNull final Speaker object, final String command){
        object.showDialog(this, command);
    }

    private void checkBorderWorld(final BodyComponent t){
        final int borderLeft = MapComponent.getLevelPixelWidth() - MapComponent.getLevelPixelHeight();
        final int borderRight = MapComponent.getLevelPixelWidth();
        final int borderUp = MapComponent.getLevelPixelHeight();
        final int borderDown = MapComponent.getLevelPixelHeight() - MapComponent.getLevelPixelHeight();

        if (t.getBody().getPosition().x <= borderLeft) {
            t.getBody().setTransform(borderLeft, t.getBody().getPosition().y, 0.0f);
        }

        if (t.getBody().getPosition().x > borderRight) {
            t.getBody().setTransform(borderRight, t.getBody().getPosition().y, 0.0f);
        }

        if(t.getBody().getPosition().y > borderUp){
            t.getBody().setTransform(t.getBody().getPosition().x, borderUp, 0.0f);
        }

        if(t.getBody().getPosition().y <= borderDown){
            t.getBody().setTransform(t.getBody().getPosition().x, borderDown, 0.0f);
        }
    }

    public int getSpeed(){
        return (int) calcStat(Stats.RUN_SPEED, PlayerComponent.BASE_RUN_SPD);
    }

    public int getMaxHp(){
        return (int) calcStat(Stats.MAX_HP, PlayerComponent.BASE_HP_MAX);
    }

    @Override
    public boolean isPlayer(){
        return true;
    }

}
