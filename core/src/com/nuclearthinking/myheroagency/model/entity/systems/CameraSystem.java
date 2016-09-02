package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.nuclearthinking.myheroagency.model.entity.World;
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

        //val maxCamX = Math.max(cam.getCamera().position.x, target.getPos().x);
        //val maxCamY = Math.max(cam.getCamera().position.y, target.getPos().y);

        System.out.println("Player: " + target.getPos().x);
        System.out.println("Camera: " + cam.getCamera().position.x);
        System.out.println("Half: " + cam.getCamera().position.x / 2);

        cam.getCamera().position.set(target.getPos().x, target.getPos().y, 0);

        if(cam.getCamera().position.x < 200)
            cam.getCamera().position.x = 200;
        if(cam.getCamera().position.x > 735)
            cam.getCamera().position.x = 735;

        //cam.getCamera().position.x = maxCamX;
        //cam.getCamera().position.y = maxCamY;

    }
}
