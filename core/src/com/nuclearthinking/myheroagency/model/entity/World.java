package com.nuclearthinking.myheroagency.model.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import com.nuclearthinking.myheroagency.model.entity.systems.RenderingSystem;

import java.util.Random;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class World {
    public static final float WORLD_WIDTH = 10;
    public static final float WORLD_HEIGHT = 15 * 20;

    private PooledEngine engine;
    public final Random rand;

    public World (PooledEngine engine) {
        this.engine = engine;
        this.rand = new Random();
    }

    public void create(){
        Entity player = createPlayer();
        createCamera(player);
    }

    private Entity createPlayer(){
        Entity entity = engine.createEntity();
        ObjectManager o = new ObjectManager();

        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);

        animation.animations.put(PlayerComponent.IDLE, o.idle);
        animation.animations.put(PlayerComponent.MOVE_R, o.right);
        animation.animations.put(PlayerComponent.MOVE_L, o.left);

        position.pos.set(5.0f, 1.0f, 0.0f);

        state.set(PlayerComponent.IDLE);

        entity.add(animation);
        entity.add(player);
        entity.add(movement);
        entity.add(position);
        entity.add(state);
        entity.add(texture);

        engine.addEntity(entity);

        return entity;
    }

    private void createCamera(Entity target) {
        Entity entity = engine.createEntity();

        CameraComponent camera = new CameraComponent();
        camera.camera = engine.getSystem(RenderingSystem.class).getCamera();
        camera.target = target;

        entity.add(camera);

        engine.addEntity(entity);
    }

}
