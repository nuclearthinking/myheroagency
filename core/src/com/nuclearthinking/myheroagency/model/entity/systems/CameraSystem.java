package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.components.CameraComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class CameraSystem extends IteratingSystem{
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<CameraComponent> cm;

    public CameraSystem() {
        super(Family.all(CameraComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        cm = ComponentMapper.getFor(CameraComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        val cam = cm.get(entity);

        if (cam.getTarget() == null) {
            return;
        }

        val target = tm.get(cam.getTarget());

        if (target == null) {
            return;
        }

        cam.getCamera().position.y = Math.max(cam.getCamera().position.y, target.getPos().y);
    }
}
