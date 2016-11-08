package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.math.Interpolation;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.manager.ScreenManager;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.model.ui.FontFactory;
import com.nuclearthinking.myheroagency.scripts.QuestLoader;
import lombok.extern.slf4j.Slf4j;

import static com.nuclearthinking.myheroagency.utils.Constants.*;

@Slf4j(topic = "LoadingScreen")
public final class LoadingScreen extends AbstractScreen {

    //Статический блок, для загрузки ассетов
    static {
        Asset.getInstance().init(ASSET_FILE);
        Asset.getInstance().loadGroup(ASSET_GROUP_LOADING);
        Asset.getInstance().finishLoading();
        Asset.getInstance().loadGroup(ASSET_GROUP_LOCALE);
        Asset.getInstance().loadGroup(ASSET_GROUP_BASE);
        Asset.getInstance().loadGroup(ASSET_GROUP_QUEST);
        Asset.getInstance().finishLoading();
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
        loadingPercent = Interpolation.linear.apply(loadingPercent, Asset.getInstance().getProgress(), 0.1f);
        if (Asset.getInstance().update() && loadingPercent >= Asset.getInstance().getProgress() - .001f) {
            log.info("Assets loading done");
            ScreenManager.getInstance().showScreen(ScreenEnum.SPLASH_SCREEN);
        }

    }
}
