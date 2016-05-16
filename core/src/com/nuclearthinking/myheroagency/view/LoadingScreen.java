package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.math.Interpolation;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.ui.font.FontFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

public class LoadingScreen extends AbstractScreen {

    static {
        Asset.getInstance().init("asset/main.xml");
        Asset.getInstance().loadGroup("base");
        Asset.getInstance().finishLoadingAsset("i18n/LoadingScreen");
        Asset.getInstance().finishLoadingAsset("font/RobotoSlab-Light.ttf");
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

        getBatch().begin();
        fontFactory.getRobotoLight(18).draw(getBatch(), locale.format("loading", loadingPercent), Constants.GAME_W - 150, 35);
        getBatch().end();

    }

    private float loading() {
        float loadingProgress = Interpolation.linear.apply(loadingPercent, Asset.getInstance().getProgress(), 0.1f);
        if (Asset.getInstance().update() && loadingPercent >= Asset.getInstance().getProgress() - .001f) {
            logger.info("Assets loading done");
            ScreenManager.getInstance().showScreen(ScreenEnum.START_SCREEN);
        }
        return loadingProgress;
    }
}
