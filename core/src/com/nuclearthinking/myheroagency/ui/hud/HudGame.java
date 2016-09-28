package com.nuclearthinking.myheroagency.ui.hud;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import com.nuclearthinking.myheroagency.ui.hud.layer.*;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.NonNull;

/**
 * Created by Izonami on 18.05.2016.
 *
 * rightTable,leftTable, r,l это тестовые и их нужно будет убрать
 * есть идея разделить таблицы на отдельные классы, но пока непонятно как выглядит интерфейс это избыточно.
 *
 */
public final class HudGame{

    private static final UiFactory uiFactory = new UiFactory();

    private @Getter final Stage stage;
    private @Getter final PlayerLayer playerLayer;
    private @Getter final PlayerStatLayer playerStatLayer;
    private @Getter final QuestLayer questLayer;
    private @Getter final SettingsLayer settings;

    private UtilsLayer utilsLayer;

    public HudGame(@NonNull final Batch batch, @NonNull final Engine engine){
        stage = new Stage(new ScreenViewport(new OrthographicCamera()), batch);

        //Инициализация слоёв
        playerLayer = new PlayerLayer(uiFactory, engine);
        playerStatLayer = new PlayerStatLayer(uiFactory, engine);
        questLayer = new QuestLayer(uiFactory); // Передаю uiFactory что бы не плодить лишние объекты
        settings = new SettingsLayer(uiFactory);

        //Отладочный слой
        if(Constants.DEBUG){
            utilsLayer = new UtilsLayer(uiFactory);
            buildHud(utilsLayer);
        }

        buildHud(playerLayer);
        buildHud(playerStatLayer);
        buildHud(questLayer);
        buildHud(settings);

        registerObserver();
    }

    /**
     * Нужно обязательно билдить слои
     */
    private void buildHud(@NonNull final AbstractLayer layer){
        layer.buildLayer();
        stage.addActor(layer.getTable());
    }

    private void registerObserver(){
        //TODO: Пока не придумал как лучше сделать
        playerStatLayer.getObservable().register(playerLayer);
        playerStatLayer.getObservable().register(playerStatLayer);
        //TODO: Для проверки убрать после тестов
        playerStatLayer.getObs().register(playerLayer);
        playerStatLayer.getObs().register(playerStatLayer);
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

    public Camera getHudCamera(){
        return stage.getCamera();
    }

}
