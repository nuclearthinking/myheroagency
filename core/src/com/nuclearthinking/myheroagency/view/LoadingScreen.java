package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.ui.font.FontFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

public class LoadingScreen extends AbstractScreen {

    static {
        Asset.getInstance().init("asset/main.xml");
        Asset.getInstance().loadGroup("loading");
        Asset.getInstance().finishLoading();
        Asset.getInstance().loadGroup("base");
    }

    private float loadingPercent;
    private BitmapFont font;

    @Override
    public void buildStage() {
        font = FontFactory.getFont();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        loadingPercent = Interpolation.linear.apply(loadingPercent, Asset.getInstance().getProgress(), 0.1f);
        if (Asset.getInstance().update() && loadingPercent >= Asset.getInstance().getProgress() - .1f) {
            logger.info("Assets loading done");
            ScreenManager.getInstance().showScreen(ScreenEnum.SPLASH_SCREEN);
        }
        getBatch().begin();
        font.draw(getBatch(), locale.format("loading", loadingPercent), Constants.GAME_W - 150, 35);
        getBatch().end();
    }

}
