package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.AnimationState;
import com.nuclearthinking.myheroagency.model.entity.World;
import com.nuclearthinking.myheroagency.model.entity.components.MovementComponent;
import com.nuclearthinking.myheroagency.model.entity.components.PlayerComponent;
import com.nuclearthinking.myheroagency.model.entity.components.StateComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class PlayerSystem extends IteratingSystem {
    private static final Family family = Family.all(PlayerComponent.class,
                                                    StateComponent.class,
                                                    TransformComponent.class,
                                                    MovementComponent.class).get();

    private @Setter float accelX = 0.0f;

    private ComponentMapper<PlayerComponent> pm;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;

    public PlayerSystem(@NonNull World world) {
        super(family);

        pm = ComponentMapper.getFor(PlayerComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        accelX = 0.0f;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val t = tm.get(entity);
        val state = sm.get(entity);
        val mov = mm.get(entity);

        if(accelX == 0.0f){
            mov.getVelocity().x = 0;
            state.set(AnimationState.IDLE.getValue());
        }

        if (accelX > 0.0f && state.getState() != AnimationState.RIGHT.getValue()) {
            mov.getVelocity().x = accelX / 10.0f * PlayerComponent.MOVE_VELOCITY;
            state.set(AnimationState.RIGHT.getValue());
        }

        if (accelX < 0.0f && state.getState() != AnimationState.LEFT.getValue()) {
            mov.getVelocity().x = accelX / 10.0f * PlayerComponent.MOVE_VELOCITY;
            state.set(AnimationState.LEFT.getValue());
        }

        if (t.getPos().x < 0) {
            t.getPos().x = World.WORLD_WIDTH;
        }

        if (t.getPos().x > World.WORLD_WIDTH) {
            t.getPos().x = 0;
        }
    }
}
