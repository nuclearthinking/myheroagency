package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.I18NBundle;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.Assets;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.ui.font.FontFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

import java.util.Locale;

/**
 * Created by Izonami on 10.05.2016.
 */
public class LoadingScreen extends AbstractScreen {

    private static I18NBundle loadingLocalization;

    static {
        FileHandle baseFileHandle = Gdx.files.internal("i18n/LoadingScreen");
        Locale locale = new Locale("ru");
        loadingLocalization = I18NBundle.createBundle(baseFileHandle, locale);
    }

    private float loadingPercent;
    private BitmapFont font;

    @Override
    public void buildStage() {
        Asset.getInstance().init("asset/main.xml");
        Asset.getInstance().loadGroup("base");
        font = FontFactory.getFont();
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
        font.draw(getBatch(), loadingLocalization.format("loading", loadingPercent), Constants.GAME_W - 150, 35);
        getBatch().end();
    }

}
