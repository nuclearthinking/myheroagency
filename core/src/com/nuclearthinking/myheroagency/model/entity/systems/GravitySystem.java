package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.World;
import com.nuclearthinking.myheroagency.model.entity.components.GravityComponent;
import com.nuclearthinking.myheroagency.model.entity.components.MovementComponent;
import lombok.val;

/**
 * Created by mkuksin on 02.09.2016.
 */
public class GravitySystem extends IteratingSystem {
    private static final Family family = Family.all(GravityComponent.class,
                                                    MovementComponent.class).get();
    private ComponentMapper<MovementComponent> mm;

    public GravitySystem() {
        super(family);

        mm = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val mov = mm.get(entity);
        mov.getVelocity().add(World.GRAVITY.x * deltaTime, World.GRAVITY.y * deltaTime);
    }
}
