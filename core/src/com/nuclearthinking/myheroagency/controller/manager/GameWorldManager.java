package com.nuclearthinking.myheroagency.controller.manager;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.systems.PlayerSystem;
import com.nuclearthinking.myheroagency.controller.systems.RenderingSystem;
import com.nuclearthinking.myheroagency.model.AnimationState;
import com.nuclearthinking.myheroagency.model.components.*;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public final class GameWorldManager {

    public static final TextureAtlas playerAtlas = Asset.getInstance().get(Constants.PLAYER_PACK);
    public static final Animation IDLE = new Animation(1 / 2f, playerAtlas.findRegions("still"), Animation.PlayMode.LOOP);
    public static final Animation LEFT = new Animation(1 / 6f, playerAtlas.findRegions("left"), Animation.PlayMode.LOOP);
    public static final Animation RIGHT = new Animation(1 / 6f, playerAtlas.findRegions("right"), Animation.PlayMode.LOOP);

    private PooledEngine engine;
    private @Getter World world;

    private final BuildHudManager buildHudManager;
    private final BuildNpcManager buildNpcManager;

    public GameWorldManager(@NonNull final PooledEngine engine, @NonNull final Batch batch) {
        this.engine = engine;
        this.world = new World(GravityComponent.getGravity(), false);
        this.buildHudManager = new BuildHudManager(engine, batch);
        this.buildNpcManager = new BuildNpcManager(engine,world);
    }

    public void create(){
        createWorld();
        val player = createPlayer();
        createCamera(player);
        buildNpcManager.createMonster(player);
        buildHudManager.createHud();
        buildNpcManager.createNpc();
        buildNpcManager.spawnNpc(300, 2860);
        buildNpcManager.spawnMonster(600, 2860);
    }

    private Entity createPlayer(){
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val light = engine.createComponent(LightComponent.class);
        val bodyCom = engine.createComponent(BodyComponent.class);
        val player = engine.createComponent(PlayerComponent.class);
        engine.getSystem(PlayerSystem.class).setActor(player);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), IDLE);
        animation.getAnimations().put(AnimationState.RIGHT.getValue(), RIGHT);
        animation.getAnimations().put(AnimationState.LEFT.getValue(), LEFT);

        bodyCom.getBodyDef().type = BodyDef.BodyType.DynamicBody;
        bodyCom.setBody(world.createBody(bodyCom.getBodyDef()));
        val bodyPolygon = new PolygonShape();
        bodyPolygon.setAsBox(10,10);
        bodyCom.getFixtureDef().shape = bodyPolygon;
        bodyCom.getFixtureDef().friction = 0.3f;
        bodyCom.getBody().createFixture(bodyCom.getFixtureDef());
        bodyPolygon.dispose();

        bodyCom.getBody().setFixedRotation(true);
        bodyCom.getBody().setTransform(15.0f, 2860.0f, 0.0f);

        light.setPlayerLight(new PointLight(light.getRayHandler(), 50));
        light.getPlayerLight().setDistance(250);
        light.getPlayerLight().setColor(light.getLightOn());
        light.setTarget(entity);

        state.set(AnimationState.IDLE.getValue());

        entity.add(animation);
        entity.add(state);
        entity.add(light);
        entity.add(player);
        entity.add(new MovementComponent());
        entity.add(bodyCom);
        entity.add(new TextureComponent());

        engine.addEntity(entity);

        return entity;
    }

    private void createCamera(@NonNull final Entity target) {
        val entity = engine.createEntity();

        val camera = engine.createComponent(CameraComponent.class);

        camera.setCamera(engine.getSystem(RenderingSystem.class).getCamera());
        camera.setTarget(target);

        entity.add(camera);

        engine.addEntity(entity);
    }

    private void createWorld() {
        val entity = engine.createEntity();

        entity.add(new MapComponent());
        entity.add(new TextureComponent());

        engine.addEntity(entity);
    }
}
