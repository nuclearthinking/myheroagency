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
import com.nuclearthinking.myheroagency.controller.button.player.AddStatsListener;
import com.nuclearthinking.myheroagency.controller.button.player.RemoveStatsListener;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import com.nuclearthinking.myheroagency.model.entity.components.hud.*;
import com.nuclearthinking.myheroagency.model.entity.systems.MonsterSystem;
import com.nuclearthinking.myheroagency.model.entity.systems.NpcSystem;
import com.nuclearthinking.myheroagency.model.entity.systems.PlayerSystem;
import com.nuclearthinking.myheroagency.model.entity.systems.RenderingSystem;
import com.nuclearthinking.myheroagency.model.quest.QuestManager;
import com.nuclearthinking.myheroagency.utils.Constants;
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
        val light = engine.createComponent(LightComponent.class);
        val monster = engine.createComponent(MonsterComponent.class);
        engine.getSystem(MonsterSystem.class).setActor(monster);

        monster.setTarget(target);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), idle);
        animation.getAnimations().put(AnimationState.RIGHT.getValue(), right);
        animation.getAnimations().put(AnimationState.LEFT.getValue(), left);

        bodyCom.getBodyDef().type = BodyDef.BodyType.DynamicBody;
        bodyCom.setBody(world.createBody(bodyCom.getBodyDef()));
        val bodyPolygon = new PolygonShape();
        bodyPolygon.setAsBox(10,10);
        bodyCom.getFixtureDef().shape = bodyPolygon;
        bodyCom.getFixtureDef().friction = 0.3f;
        bodyCom.getBody().createFixture(bodyCom.getFixtureDef());
        bodyPolygon.dispose();

        bodyCom.getBody().setFixedRotation(true);
        bodyCom.getBody().setTransform(600.0f, 2860.0f, 0.0f);

        state.set(AnimationState.IDLE.getValue());

        light.setPlayerLight(new PointLight(light.getRayHandler(), 50));
        light.getPlayerLight().setDistance(50);
        light.getPlayerLight().setColor(light.getLightOn());
        light.setTarget(entity);

        entity.add(animation);
        entity.add(state);
        entity.add(light);
        entity.add(monster);
        entity.add(bodyCom);
        entity.add(new MovementComponent());
        entity.add(new TextureComponent());

        engine.addEntity(entity);
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
        bodyPolygon.setAsBox(10,10);
        bodyCom.getFixtureDef().shape = bodyPolygon;
        bodyCom.getFixtureDef().isSensor = true; //TODO: Вместо этого настроить фильтры
        bodyCom.getBody().createFixture(bodyCom.getFixtureDef());
        bodyPolygon.dispose();

        bodyCom.getBody().setFixedRotation(true);
        bodyCom.getBody().setTransform(300.0f, 2860.0f, 0.0f);

        light.setPlayerLight(new PointLight(light.getRayHandler(), 50));
        light.getPlayerLight().setDistance(100);
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

    private void createHud(){
        val entity = engine.createEntity();

        val hud = engine.createComponent(HudComponent.class);
        val utils = engine.createComponent(UtilsHudComponent.class);
        val player = engine.createComponent(PlayerHudComponent.class);
        val settings = engine.createComponent(SettingHudComponent.class);
        val quest = engine.createComponent(QuestHudComponent.class);
        val stat = engine.createComponent(StatHudComponent.class);

        hud.setStage(new Stage(new ScreenViewport(new OrthographicCamera()), batch));

        utils.setFps(hud.uiFactory.getLabel("fps"));
        utils.table.setPosition(Gdx.graphics.getWidth()*.85f, Gdx.graphics.getHeight()*.95f);
        utils.table.setDebug(Constants.DEBUG);
        utils.table.add(utils.getFps());

        player.setPlayerHp(hud.uiFactory.getLabel("playerHp"));
        player.setPlayerLvl(hud.uiFactory.getLabel("playerLvl"));
        player.table.setPosition(Gdx.graphics.getWidth()*.10f, Gdx.graphics.getHeight()*.90f);
        player.table.setDebug(Constants.DEBUG);
        player.table.add(player.getPlayerLvl()).left();
        player.table.row();
        player.table.add(player.getPlayerHp()).left();

        stat.setCon(hud.uiFactory.getLabel("con"));
        stat.setPlus(hud.uiFactory.getTextButton("+"));
        stat.setMinus(hud.uiFactory.getTextButton("-"));
        stat.setAddStatsListener(new AddStatsListener(stat.getPlus(), engine.getSystem(PlayerSystem.class)));
        stat.setRemoveStatsListener(new RemoveStatsListener(stat.getMinus(), engine.getSystem(PlayerSystem.class)));
        stat.getPlus().addListener(stat.getAddStatsListener());
        stat.getMinus().addListener(stat.getRemoveStatsListener());
        stat.table.setSkin(hud.uiFactory.getSkin());
        //stat.table.setBackground("default-window");
        stat.table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        stat.table.setPosition(60, Gdx.graphics.getHeight()-60);
        stat.table.add(stat.getCon()).center();
        stat.table.add(stat.getPlus());
        stat.table.add(stat.getMinus());

        quest.setFactory(hud.uiFactory);
        quest.addQuestToList(QuestManager.getQuestById(1).getName());
        quest.table.setSkin(hud.uiFactory.getSkin());
        quest.table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        quest.table.setPosition(-Gdx.graphics.getWidth(), 0);
        quest.table.setBackground("default-window");

        settings.setTitleLabel(hud.uiFactory.getLabel(settings.getLocale().get("mainTitle")));
        settings.setWidthLabel(hud.uiFactory.getLabel(settings.getLocale().get("widthLabel")));
        settings.setHeightLabel(hud.uiFactory.getLabel(settings.getLocale().get("heightLabel")));
        settings.setExit(hud.uiFactory.getTextButton(settings.getLocale().get("buttonExit")));
        settings.setSave(hud.uiFactory.getTextButton(settings.getLocale().get("buttonSave")));
        settings.setWidth(hud.uiFactory.getTextField("800"));
        settings.setHeight(hud.uiFactory.getTextField("600"));
        settings.table.setSkin(hud.uiFactory.getSkin());
        settings.table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        settings.table.setPosition(-Gdx.graphics.getWidth(), 0);
        settings.table.setBackground("default-window");

        settings.table.add(settings.getTitleLabel()).spaceBottom(50).colspan(3).expandX().row();
        settings.table.row();
        settings.table.add(settings.getWidthLabel()).height(80).right();
        settings.table.add(settings.getWidth()).top().center().left();
        settings.table.add().row();
        settings.table.add(settings.getHeightLabel()).height(80).right();
        settings.table.add(settings.getHeight()).top().center().left();
        settings.table.row();
        settings.table.add(settings.getSave()).right().expandX().width(100).height(40);
        settings.table.add(settings.getExit()).left().expandX().width(100).height(40);

        hud.getStage().addActor(utils.table);
        hud.getStage().addActor(player.table);
        hud.getStage().addActor(settings.table);
        hud.getStage().addActor(quest.table);
        hud.getStage().addActor(stat.table);

        entity.add(hud);
        entity.add(utils);
        entity.add(player);
        entity.add(new PlayerComponent());
        entity.add(settings);
        entity.add(quest);
        entity.add(stat);

        engine.addEntity(entity);
    }

}
