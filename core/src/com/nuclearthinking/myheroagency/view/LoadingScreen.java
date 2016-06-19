package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.math.Interpolation;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.ui.font.FontFactory;

public class LoadingScreen extends AbstractScreen {

    static {
        Asset.getInstance().init("asset/main.xml");
        Asset.getInstance().loadGroup("loading");
        Asset.getInstance().finishLoading();
        Asset.getInstance().loadGroup("localization");
        Asset.getInstance().loadGroup("base");
    }

    private FontFactory fontFactory;
    private float loadingPercent;

    @Override
    public void buildStage() {
        fontFactory = new FontFactory();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        loadingPercent = loading();

        stage.getBatch().begin();
        fontFactory.getRobotoLight(18).draw(stage.getBatch(), locale.format("loading", loadingPercent), Settings.getWidth() - 150, 35);
        stage.getBatch().end();
    }

    private float loading() {
        final float loadingProgress = Interpolation.linear.apply(loadingPercent, Asset.getInstance().getProgress(), 0.1f);
        if (Asset.getInstance().update() && loadingPercent >= Asset.getInstance().getProgress() - .001f) {
            logger.info("Assets loading done");
            ScreenManager.getInstance().showScreen(ScreenEnum.SPLASH_SCREEN);
        }
        return loadingProgress;
    }
}
