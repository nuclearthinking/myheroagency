package com.nuclearthinking.myheroagency.ui.hud;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import com.nuclearthinking.myheroagency.ui.hud.layer.*;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 18.05.2016.
 *
 * rightTable,leftTable, r,l это тестовые и их нужно будет убрать
 * есть идея разделить таблицы на отдельные классы, но пока непонятно как выглядит интерфейс это избыточно.
 *
 */
public class HudGame{

    private static final UiFactory uiFactory = new UiFactory();

    private final Stage stage;
    private final PlayerLayer playerLayer;
    private final PlayerStatLayer playerStatLayer;
    private final QuestLayer questLayer;
    private final SettingsLayer settings;

    private UtilsLayer utilsLayer;

    public HudGame(final Batch batch){
        stage = new Stage(new ScreenViewport(new OrthographicCamera()), batch);

        //Инициализация слоёв
        playerLayer = new PlayerLayer(uiFactory);
        playerStatLayer = new PlayerStatLayer(uiFactory);
        questLayer = new QuestLayer(uiFactory); // Передаю uiFactory что бы не плодить лишние объекты
        settings = new SettingsLayer(uiFactory);

        //Отладочный слой
        if(Constants.DEBUG) utilsLayer = new UtilsLayer(uiFactory);
    }

    /**
     * Нужно обязательно билдить слои
     */
    public void buildHud(){
        playerLayer.buildLayer();
        playerStatLayer.buildLayer();
        questLayer.buildLayer();
        settings.buildLayer();

        //Отладочный слой
        if(Constants.DEBUG) utilsLayer.buildLayer();

        // В каком порядке добавляется актер, в таком и отрисовывается
        stage.addActor(playerLayer.getTable());
        stage.addActor(playerStatLayer.getTable());
        stage.addActor(questLayer.getTable()); // Добавляю актера из слоя. Получение через гетер, что бы не экстендить весь класс Group
        stage.addActor(settings.getTable());

        //Отладочный слой
        if(Constants.DEBUG) stage.addActor(utilsLayer.getTable());
    }

    public void renderHud(final float delta) {
        stage.draw();
        stage.act(delta);

        //Отладочный слой
        if(Constants.DEBUG) utilsLayer.update();
    }

    public void resizeHud(final int width, final int height){
        stage.getViewport().update(width, height);
        playerLayer.resize(width,height);
        playerStatLayer.resize(width,height);
        questLayer.resize(width,height);
        settings.resize(width,height);

        //Отладочный слой
        if(Constants.DEBUG) utilsLayer.resize(width,height);
    }

    public Stage getHudStage(){
        return stage;
    }

    public Camera getHudCamera(){
        return stage.getCamera();
    }

    public SettingsLayer getSettings(){
        return settings;
    }

    public PlayerLayer getPlayerLayer(){
        return playerLayer;
    }

    public QuestLayer getQuestLayer(){
        return questLayer;
    }

    public PlayerStatLayer getPlayerStatLayer(){
        return playerStatLayer;
    }

}
