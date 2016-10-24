package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.model.Components;
import com.nuclearthinking.myheroagency.model.components.CameraComponent;
import com.nuclearthinking.myheroagency.model.components.MapComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public final class CameraSystem extends IteratingSystem{
    private static final Family family = Family.all(CameraComponent.class).get();

    public CameraSystem() {
        super(family);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        @NonNull val cam = Components.CAMERA.get(entity);
        @NonNull val target = Components.BODY.get(cam.getTarget());

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        //Камера следует за игроком, границы камеры, равны границам карты
        cam.getCamera().position.x = Math.min(Math.max(target.getBody().getPosition().x, width / 2), MapComponent.getLevelPixelWidth() - (width / 2));
        cam.getCamera().position.y = Math.min(Math.max(target.getBody().getPosition().y, height / 2), MapComponent.getLevelPixelHeight() - (height / 2));
    }
}
