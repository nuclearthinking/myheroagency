package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.math.Interpolation;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.loader.QuestLoader;
import com.nuclearthinking.myheroagency.controller.manager.AssetsManager;
import com.nuclearthinking.myheroagency.controller.manager.ScreenManager;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.model.ui.FontFactory;
import lombok.extern.slf4j.Slf4j;

import static com.nuclearthinking.myheroagency.utils.Constants.*;

@Slf4j(topic = "LoadingScreen")
public final class LoadingScreen extends AbstractScreen {

    //Статический блок, для загрузки ассетов
    static {
        AssetsManager.getInstance().init(ASSET_FILE);
        AssetsManager.getInstance().loadGroup(ASSET_GROUP_LOADING);
        AssetsManager.getInstance().finishLoading();
        AssetsManager.getInstance().loadGroup(ASSET_GROUP_LOCALE);
        AssetsManager.getInstance().loadGroup(ASSET_GROUP_BASE);
        AssetsManager.getInstance().loadGroup(ASSET_GROUP_OBJECT);
        AssetsManager.getInstance().finishLoading();
        QuestLoader.load();
    }

    private FontFactory fontFactory;
    private float loadingPercent;

    public LoadingScreen(){
        fontFactory = new FontFactory();
    }

    @Override
    public void buildStage() {
        loadingPercent = .0f;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        loading();

        stage.getBatch().begin();
        fontFactory.getRobotoLight(18).draw(stage.getBatch(), locale.format("loading", loadingPercent), Settings.getInstance().getWidth() - 150, 35);
        stage.getBatch().end();
    }

    private void loading() {
        loadingPercent = Interpolation.linear.apply(loadingPercent, AssetsManager.getInstance().getProgress(), 0.1f);
        if (AssetsManager.getInstance().update() && loadingPercent >= AssetsManager.getInstance().getProgress() - .001f) {
            log.info("Assets loading done");
            ScreenManager.getInstance().showScreen(ScreenEnum.SPLASH_SCREEN);
        }
    }
}
