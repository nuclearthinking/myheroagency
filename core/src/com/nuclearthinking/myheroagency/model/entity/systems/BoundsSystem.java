package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.components.BoundComponent;
import com.nuclearthinking.myheroagency.model.entity.components.Components;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 02.09.2016.
 */
public class BoundsSystem extends IteratingSystem {

    public BoundsSystem() {
        super(Family.all(BoundComponent.class, TransformComponent.class).get());
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        @NonNull val pos = Components.TRANSFORM.get(entity);
        @NonNull val bounds = Components.BOUND.get(entity);

        bounds.getBounds().x = pos.getPos().x - bounds.getBounds().width * 0.5f;
        bounds.getBounds().y = pos.getPos().y - bounds.getBounds().height * 0.5f;
    }
}
