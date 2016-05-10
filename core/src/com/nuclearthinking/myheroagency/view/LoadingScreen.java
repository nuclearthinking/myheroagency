package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.math.Interpolation;
import com.nuclearthinking.myheroagency.controller.Assets;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 10.05.2016.
 */
public class LoadingScreen extends AbstractScreen {

    private float percent;

    @Override
    public void buildStage() {
        Assets.getInstance().init();
        Assets.getInstance().postLoadAssets(); //Загрузка остальных ассетов
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render(float delta) {
       super.render(delta);

        //Получает процент загрузки
        percent = Interpolation.linear.apply(percent, Assets.getInstance().getAssetManager().getProgress(), 0.1f);

        if (Assets.getInstance().getAssetManager().update() && percent >= Assets.getInstance().getAssetManager().getProgress() - .001f) {
            ScreenManager.getInstance().showScreen( ScreenEnum.START_SCREEN );
        }

        getBatch().begin();
        Assets.getInstance().getFont().draw(getBatch(), "Loading: " + Float.toString(percent * 100) + "%", Constants.GAME_W - 150, 35);
        getBatch().end();

    }

}
