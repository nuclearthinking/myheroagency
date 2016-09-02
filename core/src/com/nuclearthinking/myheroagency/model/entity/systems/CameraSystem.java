package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.nuclearthinking.myheroagency.model.entity.World;
import com.nuclearthinking.myheroagency.model.entity.components.CameraComponent;
import com.nuclearthinking.myheroagency.model.entity.components.Components;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class CameraSystem extends IteratingSystem{

    public CameraSystem() {
        super(Family.all(CameraComponent.class).get());

    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        @NonNull val cam = Components.CAMERA.get(entity);
        @NonNull val target = Components.TRANSFORM.get(cam.getTarget());

        //val maxCamX = Math.max(cam.getCamera().position.x, target.getPos().x);
        //val maxCamY = Math.max(cam.getCamera().position.y, target.getPos().y);

        //System.out.println("Player: " + target.getPos().x);
        //System.out.println("Camera: " + cam.getCamera().position.x);
        //System.out.println("Half: " + cam.getCamera().position.x / 2);

        cam.getCamera().position.set(target.getPos().x, target.getPos().y, 0);

        if(cam.getCamera().position.x < 200)
            cam.getCamera().position.x = 200;
        if(cam.getCamera().position.x > 735)
            cam.getCamera().position.x = 735;

        //cam.getCamera().position.x = maxCamX;
        //cam.getCamera().position.y = maxCamY;

    }
}
