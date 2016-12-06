package com.nuclearthinking.myheroagency.controller.manager;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.nuclearthinking.myheroagency.controller.listener.PlayerContact;
import com.nuclearthinking.myheroagency.controller.systems.RenderingSystem;
import com.nuclearthinking.myheroagency.model.actor.base.*;
import com.nuclearthinking.myheroagency.model.actor.monster.MonsterComponent;
import com.nuclearthinking.myheroagency.model.actor.monster.MonsterInstance;
import com.nuclearthinking.myheroagency.model.actor.npc.NpcInstance;
import com.nuclearthinking.myheroagency.model.actor.player.CameraComponent;
import com.nuclearthinking.myheroagency.model.actor.player.PlayerComponent;
import com.nuclearthinking.myheroagency.model.effect.LightComponent;
import com.nuclearthinking.myheroagency.model.item.ItemInstance;
import com.nuclearthinking.myheroagency.model.skills.SkillInstance;
import com.nuclearthinking.myheroagency.model.world.GravityComponent;
import com.nuclearthinking.myheroagency.model.world.MapComponent;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
@Slf4j(topic = "GameWorldManager")
public final class GameWorldManager {

    public static final TextureAtlas playerAtlas = AssetsManager.getInstance().get(Constants.PLAYER_PACK);
    public static final Animation IDLE = new Animation(1 / 2f, playerAtlas.findRegions("still"), Animation.PlayMode.LOOP);
    public static final Animation LEFT = new Animation(1 / 6f, playerAtlas.findRegions("left"), Animation.PlayMode.LOOP);
    public static final Animation RIGHT = new Animation(1 / 6f, playerAtlas.findRegions("right"), Animation.PlayMode.LOOP);

    private @Getter static PooledEngine engine;
    private @Getter static World world;
    private @Getter static Stage stage;
    private PlayerContact al;

    private final BuildNpcManager buildNpcManager;

    public GameWorldManager(@NonNull final PooledEngine engine, @NonNull final Stage stage) {
        this.engine = engine;
        this.stage = stage;
        this.world = new World(GravityComponent.getGravity(), false);
        BuildHudManager.getInstance().init(engine, stage.getBatch());
        this.buildNpcManager = new BuildNpcManager();
        this.al = new PlayerContact();
    }

    public void create() {
        log.info("Build Map");
        createWorld();
        log.info("Create Player");
        val player = createPlayer();
        log.info("Create Camera");
        createCamera(player);
        log.info("Create Player Listener");
        world.setContactListener(al);
        log.info("Build Hud");
        BuildHudManager.getInstance().createHud();
        log.info("Create Npc");
        NpcInstance.getInstance();
        log.info("Create Monster");
        MonsterInstance.getInstance();
        log.info("Create Items");
        ItemInstance.getInstance();
        log.info("Create Skills");
        SkillInstance.getInstance();

        //TODO: Затычки для спавна и АИ
        MonsterInstance.getInstance().getMonsterList().get(0).getComponent(MonsterComponent.class).setTarget(player);
        buildNpcManager.spawnNpc(NpcInstance.getInstance().getNpsList().get(0), 300, 2860);
        buildNpcManager.spawnNpc(MonsterInstance.getInstance().getMonsterList().get(0), 600, 2860);
    }

    private Entity createPlayer() {
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val light = engine.createComponent(LightComponent.class);
        val bodyCom = engine.createComponent(BodyComponent.class);
        val player = engine.createComponent(PlayerComponent.class);
        player.initialize(null);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), IDLE);
        animation.getAnimations().put(AnimationState.RIGHT.getValue(), RIGHT);
        animation.getAnimations().put(AnimationState.LEFT.getValue(), LEFT);

        bodyCom.getBodyDef().type = BodyDef.BodyType.DynamicBody;
        bodyCom.setBody(world.createBody(bodyCom.getBodyDef()));
        val bodyPolygon = new PolygonShape();
        bodyPolygon.setAsBox(10,10);
        bodyCom.getFixtureDef().shape = bodyPolygon;
        bodyCom.getFixtureDef().friction = 0.3f;
        bodyCom.getFixtureDef().filter.categoryBits = Constants.BIT_PLAYER;
        bodyCom.getFixtureDef().filter.maskBits = Constants.BIT_NPC + Constants.BIT_MONSTER;
        bodyCom.getBody().createFixture(bodyCom.getFixtureDef()).setUserData("player");
        bodyPolygon.dispose();

        bodyCom.getBody().setFixedRotation(true);
        bodyCom.getBody().setTransform(15.0f, 2860.0f, 0.0f);

        light.setPlayerLight(new PointLight(LightComponent.getRayHandler(), 50));
        light.getPlayerLight().setDistance(250);
        light.getPlayerLight().setColor(LightComponent.getLightOn());
        light.setTarget(entity);

        state.set(AnimationState.IDLE.getValue());

        entity.add(animation);
        entity.add(state);
        entity.add(light);
        entity.add(player);
        entity.add(new MovementComponent());
        entity.add(bodyCom);
        entity.add(new TextureComponent());
        entity.add(new SkillComponent());

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
