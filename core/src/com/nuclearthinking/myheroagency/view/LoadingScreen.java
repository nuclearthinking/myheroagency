package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.Assets;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 10.05.2016.
 */
public class LoadingScreen extends AbstractScreen {

    private float loadingPercent;
    private BitmapFont font;

    @Override
    public void buildStage() {
        Asset.getInstance().init("asset/main.xml");
        Asset.getInstance().loadGroup("base");
        font = new BitmapFont();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        //Получает процент загрузки
        loadingPercent = Interpolation.linear.apply(loadingPercent, Asset.getInstance().getProgress(), 0.1f);


        if (Asset.getInstance().update() && loadingPercent >= Asset.getInstance().getProgress() - .1f) {
            ScreenManager.getInstance().showScreen(ScreenEnum.START_SCREEN);
        }
        getBatch().begin();
        font.draw(getBatch(), "Loading: " + Float.toString(loadingPercent * 100) + "%", Constants.GAME_W - 150, 35);
        getBatch().end();
    }

}
