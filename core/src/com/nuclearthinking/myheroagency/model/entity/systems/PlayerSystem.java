package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.actor.Player;
import com.nuclearthinking.myheroagency.model.entity.World;
import com.nuclearthinking.myheroagency.model.entity.components.MovementComponent;
import com.nuclearthinking.myheroagency.model.entity.components.PlayerComponent;
import com.nuclearthinking.myheroagency.model.entity.components.StateComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class PlayerSystem extends IteratingSystem {
    private static final Family family = Family.all(PlayerComponent.class,
                                                    StateComponent.class,
                                                    TransformComponent.class,
                                                    MovementComponent.class).get();

    private float accelX = 0.0f;
    private World world;

    private ComponentMapper<PlayerComponent> pm;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;

    public PlayerSystem(World world) {
        super(family);

        this.world = world;

        pm = ComponentMapper.getFor(PlayerComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
    }

    public void setAccelX(float accelX) {
        this.accelX = accelX;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        accelX = 0.0f;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent t = tm.get(entity);
        StateComponent state = sm.get(entity);
        MovementComponent mov = mm.get(entity);
        PlayerComponent bob = pm.get(entity);

        if(accelX == 0){
            state.set(PlayerComponent.IDLE);
        }

        if (state.get() != PlayerComponent.MOVE_R) {
            mov.velocity.x = accelX / 10.0f * PlayerComponent.MOVE_VELOCITY;
            state.set(PlayerComponent.MOVE_R);
        }

        if (state.get() != PlayerComponent.MOVE_L) {
            mov.velocity.x = accelX / 10.0f * PlayerComponent.MOVE_VELOCITY;
            state.set(PlayerComponent.MOVE_L);
        }

        if (t.pos.x < 0) {
            t.pos.x = World.WORLD_WIDTH;
        }

        if (t.pos.x > World.WORLD_WIDTH) {
            t.pos.x = 0;
        }

        t.scale.x = mov.velocity.x < 0.0f ? Math.abs(t.scale.x) * -1.0f : Math.abs(t.scale.x);

        bob.heightSoFar = Math.max(t.pos.y, bob.heightSoFar);
    }
}
