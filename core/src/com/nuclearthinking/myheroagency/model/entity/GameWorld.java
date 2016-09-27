package com.nuclearthinking.myheroagency.model.entity;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import com.nuclearthinking.myheroagency.model.entity.systems.RenderingSystem;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class GameWorld {
    private final TextureAtlas playerAtlas = Asset.getInstance().get("player/player.pack");
    private final Animation idle = new Animation(1 / 2f, playerAtlas.findRegions("still"), Animation.PlayMode.LOOP);
    private final Animation left = new Animation(1 / 6f, playerAtlas.findRegions("left"), Animation.PlayMode.LOOP);
    private final Animation right = new Animation(1 / 6f, playerAtlas.findRegions("right"), Animation.PlayMode.LOOP);

    private PooledEngine engine;

    private @Getter World world;

    public GameWorld(@NonNull final PooledEngine engine) {
        this.engine = engine;
        this.world = new World(GravityComponent.getGravity(), false);
    }

    public void create(){
        createWorld();
        val player = createPlayer();
        createCamera(player);
        createObject();//TODO: Удалить тестовый объект
    }

    //TODO: УДАЛИТЬ!
    private void createObject(){
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val pos = engine.createComponent(TransformComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val light = engine.createComponent(LightComponent.class);
        val bounds = engine.createComponent(BoundComponent.class);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), idle);

        pos.getPos().set(300.0f, 2860.0f, 0.0f);
        bounds.getBounds().width = pos.getScale().x;
        bounds.getBounds().height = pos.getScale().y;

        light.setPlayerLight(new PointLight(light.getRayHandler(), 50));
        light.getPlayerLight().setDistance(250);
        light.getPlayerLight().setColor(light.getLightOn());
        light.setTarget(entity);

        state.set(AnimationState.IDLE.getValue());

        entity.add(animation);
        entity.add(pos);
        entity.add(state);
        entity.add(light);
        entity.add(new NpcComponent());
        entity.add(bounds);
        entity.add(new TextureComponent());

        engine.addEntity(entity);
    }

    private Entity createPlayer(){
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val position = engine.createComponent(TransformComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val light = engine.createComponent(LightComponent.class);
        val bounds = engine.createComponent(BoundComponent.class);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), idle);
        animation.getAnimations().put(AnimationState.RIGHT.getValue(), right);
        animation.getAnimations().put(AnimationState.LEFT.getValue(), left);

        position.getPos().set(15.0f, 2860.0f, 0.0f);

        bounds.getBounds().width = position.getScale().x;
        bounds.getBounds().height = position.getScale().y;

        light.setPlayerLight(new PointLight(light.getRayHandler(), 50));
        light.getPlayerLight().setDistance(250);
        light.getPlayerLight().setColor(light.getLightOn());
        light.setTarget(entity);

        state.set(AnimationState.IDLE.getValue());

        entity.add(animation);
        entity.add(position);
        entity.add(state);
        entity.add(light);
        entity.add(new PlayerComponent());
        entity.add(new FunctionComponent());
        entity.add(new MovementComponent());
        entity.add(bounds);
        entity.add(new BodyComponent());
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

    private void createWorld() {
        val entity = engine.createEntity();

        entity.add(new MapComponent());
        entity.add(new TransformComponent());
        entity.add(new TextureComponent());

        engine.addEntity(entity);
    }

}
