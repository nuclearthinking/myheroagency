package com.nuclearthinking.myheroagency.model.entity;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import com.nuclearthinking.myheroagency.model.entity.components.hud.HudComponent;
import com.nuclearthinking.myheroagency.model.entity.components.hud.PlayerHudComponent;
import com.nuclearthinking.myheroagency.model.entity.components.hud.UtilsHudComponent;
import com.nuclearthinking.myheroagency.model.entity.systems.MonsterSystem;
import com.nuclearthinking.myheroagency.model.entity.systems.NpcSystem;
import com.nuclearthinking.myheroagency.model.entity.systems.PlayerSystem;
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
    private Batch batch;

    public GameWorld(@NonNull final PooledEngine engine, @NonNull final Batch batch) {
        this.engine = engine;
        this.world = new World(GravityComponent.getGravity(), false);
        this.batch = batch;
    }

    public void create(){
        createWorld();
        val player = createPlayer();
        createCamera(player);
        createObject();//TODO: Удалить тестовый объект
        createMonster(player);
        createHud();
    }

    private void createMonster(@NonNull final Entity target){
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val bodyCom = engine.createComponent(BodyComponent.class);
        val monster = engine.createComponent(MonsterComponent.class);
        engine.getSystem(MonsterSystem.class).setActor(monster);

        monster.setTarget(target);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), idle);
        animation.getAnimations().put(AnimationState.RIGHT.getValue(), right);
        animation.getAnimations().put(AnimationState.LEFT.getValue(), left);

        bodyCom.getBodyDef().type = BodyDef.BodyType.DynamicBody;
        bodyCom.setBody(world.createBody(bodyCom.getBodyDef()));
        val bodyPolygon = new PolygonShape();
        bodyPolygon.setAsBox(12,12);
        bodyCom.getFixtureDef().shape = bodyPolygon;
        bodyCom.getFixtureDef().friction = 0.3f;
        bodyCom.getBody().createFixture(bodyCom.getFixtureDef());
        bodyPolygon.dispose();

        bodyCom.getBody().setFixedRotation(true);
        bodyCom.getBody().setTransform(600.0f, 2860.0f, 0.0f);

        state.set(AnimationState.IDLE.getValue());

        entity.add(animation);
        entity.add(state);
        entity.add(monster);
        entity.add(bodyCom);
        entity.add(new FunctionComponent());
        entity.add(new MovementComponent());
        entity.add(new TextureComponent());

        engine.addEntity(entity);
        createHud();
    }

    //TODO: УДАЛИТЬ!
    private void createObject(){
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val light = engine.createComponent(LightComponent.class);
        val bodyCom = engine.createComponent(BodyComponent.class);
        val npc = engine.createComponent(NpcComponent.class);
        engine.getSystem(NpcSystem.class).setActor(npc);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), idle);

        bodyCom.getBodyDef().type = BodyDef.BodyType.StaticBody;
        bodyCom.setBody(world.createBody(bodyCom.getBodyDef()));
        val bodyPolygon = new PolygonShape();
        bodyPolygon.setAsBox(12,12);
        bodyCom.getFixtureDef().shape = bodyPolygon;
        bodyCom.getFixtureDef().friction = 0.3f;
        bodyCom.getBody().createFixture(bodyCom.getFixtureDef());
        bodyPolygon.dispose();

        bodyCom.getBody().setFixedRotation(true);
        bodyCom.getBody().setTransform(300.0f, 2860.0f, 0.0f);

        light.setPlayerLight(new PointLight(light.getRayHandler(), 50));
        light.getPlayerLight().setDistance(250);
        light.getPlayerLight().setColor(light.getLightOn());
        light.setTarget(entity);

        state.set(AnimationState.IDLE.getValue());

        entity.add(animation);
        entity.add(state);
        entity.add(light);
        entity.add(npc);
        entity.add(bodyCom);
        entity.add(new TextureComponent());

        engine.addEntity(entity);
    }

    private Entity createPlayer(){
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val light = engine.createComponent(LightComponent.class);
        val bodyCom = engine.createComponent(BodyComponent.class);
        val player = engine.createComponent(PlayerComponent.class);
        engine.getSystem(PlayerSystem.class).setActor(player);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), idle);
        animation.getAnimations().put(AnimationState.RIGHT.getValue(), right);
        animation.getAnimations().put(AnimationState.LEFT.getValue(), left);

        bodyCom.getBodyDef().type = BodyDef.BodyType.DynamicBody;
        bodyCom.setBody(world.createBody(bodyCom.getBodyDef()));
        val bodyPolygon = new PolygonShape();
        bodyPolygon.setAsBox(12,12);
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
        entity.add(new FunctionComponent());
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

    private void createHud(){
        val entity = engine.createEntity();

        val hud = engine.createComponent(HudComponent.class);
        val utils = engine.createComponent(UtilsHudComponent.class);
        val player = engine.createComponent(PlayerHudComponent.class);

        hud.setStage(new Stage(new ScreenViewport(new OrthographicCamera()), batch));

        utils.setFps(hud.getUiFactory().getLabel("fps"));
        utils.getTable().setPosition(Gdx.graphics.getWidth() - 60, Gdx.graphics.getHeight() - 30);
        utils.getTable().add(utils.getFps());

        player.setPlayerLvl(hud.getUiFactory().getLabel("playerLvl"));
        player.getTable().setPosition(40, Gdx.graphics.getHeight()-30);
        player.getTable().add(player.getPlayerLvl());

        hud.getStage().addActor(utils.getTable());
        hud.getStage().addActor(player.getTable());

        entity.add(hud);
        entity.add(utils);
        entity.add(player);
        entity.add(new PlayerComponent());

        engine.addEntity(entity);
    }

}
