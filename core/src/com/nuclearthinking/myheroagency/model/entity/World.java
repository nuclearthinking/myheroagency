package com.nuclearthinking.myheroagency.model.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import com.nuclearthinking.myheroagency.model.entity.systems.RenderingSystem;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class World {
    public static final int WORLD_BORDER_L = 5;
    public static final int WORLD_BORDER_R = 895;
    public static final Vector2 GRAVITY = new Vector2(0, -12);

    private final TextureAtlas playerAtlas = Asset.getInstance().get("player/player.pack");
    private final Animation idle = new Animation(1 / 2f, playerAtlas.findRegions("still"), Animation.PlayMode.LOOP);
    private final Animation left = new Animation(1 / 6f, playerAtlas.findRegions("left"), Animation.PlayMode.LOOP);
    private final Animation right = new Animation(1 / 6f, playerAtlas.findRegions("right"), Animation.PlayMode.LOOP);

    private PooledEngine engine;

    public World (@NonNull final PooledEngine engine) {
        this.engine = engine;
    }

    public void create(){
        val player = createPlayer();
        createCamera(player);
        createBackground();
    }

    private Entity createPlayer(){
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val position = engine.createComponent(TransformComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val light = engine.createComponent(LightComponent.class);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), idle);
        animation.getAnimations().put(AnimationState.RIGHT.getValue(), right);
        animation.getAnimations().put(AnimationState.LEFT.getValue(), left);

        position.getPos().set(15.0f, 3000.0f, 0.0f);

        state.set(AnimationState.IDLE.getValue());
        light.setTarget(entity);

        entity.add(animation);
        entity.add(position);
        entity.add(state);
        entity.add(light);
        entity.add(new PlayerComponent());
        entity.add(new MovementComponent());
        entity.add(new GravityComponent());
        entity.add(new TextureComponent());

        engine.addEntity(entity);

        return entity;
    }

    private void createCamera(@NonNull final Entity target) {
        val entity = engine.createEntity();

        val camera = new CameraComponent();

        camera.setCamera(engine.getSystem(RenderingSystem.class).getCamera());
        camera.setTarget(target);

        entity.add(camera);

        engine.addEntity(entity);
    }

    private void createBackground() {
        val entity = engine.createEntity();

        entity.add(new MapComponent());
        entity.add(new TransformComponent());
        entity.add(new TextureComponent());

        engine.addEntity(entity);
    }

}
