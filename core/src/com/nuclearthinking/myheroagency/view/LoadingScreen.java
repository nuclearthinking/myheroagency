package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.math.Interpolation;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.manager.ScreenManager;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.model.ui.FontFactory;
import com.nuclearthinking.myheroagency.scripts.QuestLoader;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "LoadingScreen")
public final class LoadingScreen extends AbstractScreen {

    static {
        Asset.getInstance().init("asset/main.xml");
        Asset.getInstance().loadGroup("loading");
        Asset.getInstance().finishLoading();
        Asset.getInstance().loadGroup("localization");
        Asset.getInstance().loadGroup("base");
        Asset.getInstance().loadGroup("quest");
        Asset.getInstance().finishLoading();
        QuestLoader.load();
    }

    private static FontFactory fontFactory;
    private static float loadingPercent;

    @Override
    public void buildStage() {
        fontFactory = new FontFactory();
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
        fontFactory.getRobotoLight(18).draw(stage.getBatch(), locale.format("loading", loadingPercent), Settings.getWidth() - 150, 35);
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
