package com.nuclearthinking.myheroagency.controller.manager;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nuclearthinking.myheroagency.controller.button.menu.ExitListener;
import com.nuclearthinking.myheroagency.controller.button.menu.SaveLayerListener;
import com.nuclearthinking.myheroagency.controller.button.player.AddStatsListener;
import com.nuclearthinking.myheroagency.controller.button.player.RemoveStatsListener;
import com.nuclearthinking.myheroagency.controller.systems.PlayerSystem;
import com.nuclearthinking.myheroagency.model.components.PlayerComponent;
import com.nuclearthinking.myheroagency.model.components.hud.*;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Created by mkuksin on 20.10.2016.
 */
@Slf4j(topic = "BuildHudManager")
public final class BuildHudManager {

    private final PooledEngine engine;
    private final Batch batch;
    private final String screenName = getClass().getSimpleName();

    public BuildHudManager(@NonNull final PooledEngine engine, @NonNull final Batch batch){
        this.engine = engine;
        this.batch = batch;
    }

    public void createHud(){
        val entity = engine.createEntity();

        val hud = engine.createComponent(HudComponent.class);

        //Инициализируем Stage
        hud.setStage(new Stage(new ScreenViewport(new OrthographicCamera()), batch));
        hud.setActor(engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first());

        //Билдим слои
        val utils = buildUtils(hud);
        val player = buildPlayer(hud);
        val stat = buildStat(hud);
        val settings = buildSettings(hud);
        val quest = buildQuest(hud);

        //Добавляем их на в Stage
        hud.getStage().addActor(utils.table);
        hud.getStage().addActor(player.table);
        hud.getStage().addActor(settings.table);
        hud.getStage().addActor(quest.table);
        hud.getStage().addActor(stat.table);

        //Добавляем компоненты в Entity
        entity.add(hud);
        entity.add(utils);
        entity.add(player);
        entity.add(settings);
        entity.add(quest);
        entity.add(stat);

        engine.addEntity(entity);
    }

    private UtilsHudComponent buildUtils(@NonNull final HudComponent hud){
        val utils = engine.createComponent(UtilsHudComponent.class);
        utils.setFps(hud.uiFactory.getLabel("fps"));
        utils.table.setPosition(Gdx.graphics.getWidth()*.85f, Gdx.graphics.getHeight()*.95f);
        utils.table.setDebug(Constants.DEBUG);
        utils.table.add(utils.getFps());

        return utils;
    }

    private PlayerHudComponent buildPlayer(@NonNull final HudComponent hud){
        val player = engine.createComponent(PlayerHudComponent.class);

        player.setPlayerHp(hud.uiFactory.getLabel("playerHp"));
        player.setPlayerLvl(hud.uiFactory.getLabel("playerLvl"));
        player.table.setPosition(Gdx.graphics.getWidth()*.10f, Gdx.graphics.getHeight()*.90f);
        player.table.setDebug(Constants.DEBUG);
        player.table.add(player.getPlayerLvl()).left();
        player.table.row();
        player.table.add(player.getPlayerHp()).left();

        return player;
    }

    private StatHudComponent buildStat(@NonNull final HudComponent hud){
        val stat = engine.createComponent(StatHudComponent.class);

        stat.setCon(hud.uiFactory.getLabel("con"));
        stat.setPlus(hud.uiFactory.getTextButton("+"));
        stat.setMinus(hud.uiFactory.getTextButton("-"));
        stat.setAddStatsListener(new AddStatsListener(stat.getPlus(), engine.getSystem(PlayerSystem.class).getEntities().first().getComponent(PlayerComponent.class)));
        stat.setRemoveStatsListener(new RemoveStatsListener(stat.getMinus(), engine.getSystem(PlayerSystem.class).getEntities().first().getComponent(PlayerComponent.class)));
        stat.getPlus().addListener(stat.getAddStatsListener());
        stat.getMinus().addListener(stat.getRemoveStatsListener());
        stat.table.setSkin(hud.uiFactory.getSkin());
        //stat.table.setBackground("default-window");
        stat.table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        stat.table.setPosition(-Gdx.graphics.getWidth(), 0);
        stat.table.add(stat.getCon()).center();
        stat.table.add(stat.getPlus());
        stat.table.add(stat.getMinus());

        return stat;
    }

    private SettingHudComponent buildSettings(@NonNull final HudComponent hud){
        val settings = engine.createComponent(SettingHudComponent.class);

        settings.setTitleLabel(hud.uiFactory.getLabel("shc.mainTitle", screenName, settings.getLocale()));
        settings.setWidthLabel(hud.uiFactory.getLabel("shc.widthLabel", screenName, settings.getLocale()));
        settings.setHeightLabel(hud.uiFactory.getLabel("shc.heightLabel", screenName, settings.getLocale()));
        settings.setExit(hud.uiFactory.getTextButton("shc.buttonExit", screenName, settings.getLocale()));
        settings.setSave(hud.uiFactory.getTextButton("shc.buttonSave", screenName, settings.getLocale()));
        settings.getExit().addListener(new ExitListener(settings.getExit()));
        settings.getSave().addListener(new SaveLayerListener(settings.getSave(), settings));
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

        return settings;
    }

    private QuestHudComponent buildQuest(@NonNull final HudComponent hud){
        val quest = engine.createComponent(QuestHudComponent.class);

        quest.setFactory(hud.uiFactory);
        quest.addQuestToList(QuestManager.getQuestById(0).getName());
        quest.table.setSkin(hud.uiFactory.getSkin());
        quest.table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        quest.table.setPosition(-Gdx.graphics.getWidth(), 0);
        quest.table.setBackground("default-window");

        return quest;
    }
}
