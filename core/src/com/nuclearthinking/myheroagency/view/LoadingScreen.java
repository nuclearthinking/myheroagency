package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.math.Interpolation;
import com.nuclearthinking.myheroagency.controller.Assets;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.ui.font.FontFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 10.05.2016.
 */
public class LoadingScreen extends AbstractScreen {

    private float loadingPercent;

    static {
        Assets.getInstance().init();
    }

    @Override
    public void buildStage() {
        Assets.getInstance().loadAssets();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        loadingPercent = loading();

        getBatch().begin();
        FontFactory.getFont9().draw(getBatch(), "Loading: " + Float.toString(loadingPercent * 100) + "%", Constants.GAME_W - 150, 35);
        getBatch().end();

    }

    private float loading() {
        float loadingProgress = Interpolation.linear.apply(loadingPercent, Assets.getInstance().getAssetManager().getProgress(), 0.1f);
        if (Assets.getInstance().getAssetManager().update() && loadingPercent >= Assets.getInstance().getAssetManager().getProgress() - .001f) {
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU_SCREEN);
        }
        return loadingProgress;
    }

}
