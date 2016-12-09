package com.nuclearthinking.myheroagency.controller.manager;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nuclearthinking.myheroagency.controller.listener.button.menu.ExitListener;
import com.nuclearthinking.myheroagency.controller.listener.button.menu.SaveLayerListener;
import com.nuclearthinking.myheroagency.controller.listener.button.player.AddStatsListener;
import com.nuclearthinking.myheroagency.controller.listener.button.player.RemoveStatsListener;
import com.nuclearthinking.myheroagency.controller.systems.PlayerSystem;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.model.actor.base.SkillComponent;
import com.nuclearthinking.myheroagency.model.actor.player.PlayerComponent;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import com.nuclearthinking.myheroagency.model.ui.hud.*;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by mkuksin on 20.10.2016.
 */
@Slf4j(topic = "BuildHudManager")
//TODO: Думаю очень не хорошо постоянно хранить эти окна, нужно создавать и грохать каждый раз при открытии и закрытии окна
public final class BuildHudManager {

    private PooledEngine engine;
    private Batch batch;
    private final String screenName = getClass().getSimpleName();
    private HudComponent hud;
    private Entity entity;

    private SkillHudComponent skill;
    private StatHudComponent stat;
    private SettingHudComponent settings;
    private QuestHudComponent quest;

    private UtilsHudComponent utils;
    private PlayerHudComponent player;

    private static BuildHudManager instance;

    public static BuildHudManager getInstance(){
        if(instance == null){
            instance = new BuildHudManager();
        }
        return instance;
    }

    private BuildHudManager(){
    }

    public void init(@NonNull final PooledEngine engine, @NonNull final Batch batch){
        this.engine = engine;
        this.batch = batch;
    }

    public void createHud(){
        entity = engine.createEntity();

        hud = engine.createComponent(HudComponent.class);

        //Инициализируем Stage
        hud.setStage(new Stage(new ScreenViewport(new OrthographicCamera()), batch));
        hud.setActor(engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first());

        //Добавляем компоненты в Entity
        entity.add(hud);

        engine.addEntity(entity);

        buildUtils();
        buildPlayer();
    }

    private void buildUtils(){
        utils = engine.createComponent(UtilsHudComponent.class);

        utils.setFps(hud.getUiFactory().getLabel("fps"));
        utils.getTable().setPosition(Gdx.graphics.getWidth()*.85f, Gdx.graphics.getHeight()*.95f);
        utils.getTable().setDebug(Constants.DEBUG);
        utils.getTable().add(utils.getFps());

        hud.getStage().addActor(utils.getTable());
        entity.add(utils);
    }

    private void buildPlayer(){
        player = engine.createComponent(PlayerHudComponent.class);

        player.setPlayerHp(hud.getUiFactory().getLabel("playerHp"));
        player.setPlayerLvl(hud.getUiFactory().getLabel("playerLvl"));
        player.getTable().setPosition(Gdx.graphics.getWidth()*.10f, Gdx.graphics.getHeight()*.90f);
        player.getTable().setDebug(Constants.DEBUG);
        player.getTable().add(player.getPlayerLvl()).left();
        player.getTable().row();
        player.getTable().add(player.getPlayerHp()).left();

        hud.getStage().addActor(player.getTable());
        entity.add(player);
    }

    public void buildStat(){
        if(stat == null){
            stat = engine.createComponent(StatHudComponent.class);

            stat.buildWindow();

            stat.setCon(hud.getUiFactory().getLabel("con"));
            stat.setPlus(hud.getUiFactory().getTextButton("+"));
            stat.setMinus(hud.getUiFactory().getTextButton("-"));
            stat.setAddStatsListener(new AddStatsListener(stat.getPlus(), engine.getSystem(PlayerSystem.class).getEntities().first().getComponent(PlayerComponent.class)));
            stat.setRemoveStatsListener(new RemoveStatsListener(stat.getMinus(), engine.getSystem(PlayerSystem.class).getEntities().first().getComponent(PlayerComponent.class)));
            stat.getPlus().addListener(stat.getAddStatsListener());
            stat.getMinus().addListener(stat.getRemoveStatsListener());

            stat.getWindow().add(stat.getCon()).center();
            stat.getWindow().add(stat.getPlus());
            stat.getWindow().add(stat.getMinus());

            hud.getStage().addActor(stat.getWindow());
            entity.add(stat);
        }
        else {
            Settings.getInstance().setPosStatX(stat.getWindow().getX());
            Settings.getInstance().setPosStatY(stat.getWindow().getY());
            stat.setPosWinX(stat.getWindow().getX());
            stat.setPosWinY(stat.getWindow().getY());
            entity.remove(StatHudComponent.class);
            //hud.getStage().getActors().removeValue(stat.getWindow(), true);
            stat.dispose();
            stat = null;
        }
    }

    public void buildSettings(){
        if(settings == null){
            settings = engine.createComponent(SettingHudComponent.class);

            settings.getTable().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            settings.getTable().setPosition(-Gdx.graphics.getWidth(), 0);
            settings.getTable().setBackground("default-window");
            settings.getTable().addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));

            settings.setTitleLabel(hud.getUiFactory().getLabel("shc.mainTitle", screenName, settings.getLocale()));
            settings.setWidthLabel(hud.getUiFactory().getLabel("shc.widthLabel", screenName, settings.getLocale()));
            settings.setHeightLabel(hud.getUiFactory().getLabel("shc.heightLabel", screenName, settings.getLocale()));
            settings.setExit(hud.getUiFactory().getTextButton("shc.buttonExit", screenName, settings.getLocale()));
            settings.setSave(hud.getUiFactory().getTextButton("shc.buttonSave", screenName, settings.getLocale()));
            settings.getExit().addListener(new ExitListener(settings.getExit()));
            settings.getSave().addListener(new SaveLayerListener(settings.getSave(), settings));
            settings.setWidth(hud.getUiFactory().getTextField("800"));
            settings.setHeight(hud.getUiFactory().getTextField("600"));

            settings.getTable().add(settings.getTitleLabel()).spaceBottom(50).colspan(3).expandX().row();
            settings.getTable().row();
            settings.getTable().add(settings.getWidthLabel()).height(80).right();
            settings.getTable().add(settings.getWidth()).top().center().left();
            settings.getTable().add().row();
            settings.getTable().add(settings.getHeightLabel()).height(80).right();
            settings.getTable().add(settings.getHeight()).top().center().left();
            settings.getTable().row();
            settings.getTable().add(settings.getSave()).right().expandX().width(100).height(40);
            settings.getTable().add(settings.getExit()).left().expandX().width(100).height(40);

            hud.getStage().addActor(settings.getTable());
            entity.add(settings);
        }
        else {
            entity.remove(SettingHudComponent.class);
            hud.getStage().getActors().removeValue(settings.getTable(), true);
            settings.undo();
            settings = null;
        }
    }

    public void rebuildSettings(){
        if(settings != null) {
            settings.getTable().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        else buildSettings();
    }

    public void buildQuest(){
        if(quest == null){
            quest = engine.createComponent(QuestHudComponent.class);

            quest.getTable().setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
            quest.getTable().setPosition(-Gdx.graphics.getWidth(), 0);
            quest.getTable().setBackground("default-window");


            quest.addQuestToList(QuestManager.getQuestById(0).getName(), hud.getUiFactory());
            quest.getTable().addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));

            hud.getStage().addActor(quest.getTable());
            entity.add(quest);
        }
        else {
            entity.remove(QuestHudComponent.class);
            hud.getStage().getActors().removeValue(quest.getTable(), true);
            quest.undo();
            quest = null;
        }
    }

    public void buildSkill(){
        if(skill == null){
            skill = engine.createComponent(SkillHudComponent.class);

            skill.buildWindow();

            //TODO: Реализовать список скилов
            skill.getWindow().add(new Label(engine.getSystem(PlayerSystem.class).getEntities().first().getComponent(SkillComponent.class).getSkillList().get(1).getName(),
                    UiFactory.getSkin(),
                    Constants.UI_SKIN_TYPE));

            log.info(engine.getSystem(PlayerSystem.class).getEntities().first().getComponent(SkillComponent.class).getSkillList().toString());

            hud.getStage().addActor(skill.getWindow());
            entity.add(skill);
        }
        else {
            entity.remove(SkillHudComponent.class);
            //hud.getStage().getActors().removeValue(skill.getTable(), true);
            skill.dispose();
            skill = null;
        }
    }

    public void rebuildAll(final int w, final int h){
        if(hud != null)
            hud.getStage().getViewport().update(w, h, true);
        if(utils != null)
            utils.getTable().setPosition(w*.85f, h*.95f);
        if(player != null)
            player.getTable().setPosition(w*.10f, h*.90f);
    }
}
