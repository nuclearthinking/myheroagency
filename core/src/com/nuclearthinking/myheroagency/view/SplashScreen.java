package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.ScreenController;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.manager.ScreenManager;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.model.ui.FontFactory;
import com.nuclearthinking.myheroagency.utils.TextActor;
import lombok.val;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Date: 05.05.2016
 * Time: 7:00
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public final class SplashScreen extends AbstractScreen {

    private final Texture texture;
    private static Image splashImage;
    private static TextActor actor;
    private static FontFactory fontFactory;
    private static ScreenController controller;

    public SplashScreen() {
        super();

        texture = Asset.getInstance().get("img/splash.png", Texture.class);
        controller = new ScreenController();
    }

    @Override
    public void buildStage() {
        fontFactory = new FontFactory();
        //TODO: Пробелы это костыль, нужно передавать аргумент, что бы двигать положение текста
        actor = new TextActor(fontFactory.getRobotoLight(26), "   " + "FOG", stage);
        splashImage = new Image(texture);

        actor.setPosition(Settings.getWidth() / 2 - splashImage.getWidth(),
                Settings.getHeight() / 2 + splashImage.getHeight());
        actor.fadeText();

        val load = new Runnable() {
            @Override
            public void run() {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU_SCREEN);
            }
        };

        splashImage.setPosition(Gdx.graphics.getWidth() / 2 - splashImage.getWidth(),
                Gdx.graphics.getHeight() + splashImage.getHeight());
        splashImage.addAction(
                //Секвенция задаёт порядок действий
                sequence(
                        alpha(0), //Альфа канал 0
                        scaleTo(.1f, .1f), //Размер картинки
                        //Выполняется поралельно с сиквенцией
                        parallel
                                (
                                        fadeIn(2f, Interpolation.pow2), //Появление
                                        scaleTo(2f, 2f, 2.5f, Interpolation.pow5), //Изменение размера
                                        moveTo(Gdx.graphics.getWidth() / 2 - splashImage.getWidth(),
                                                Gdx.graphics.getHeight()  / 2 - splashImage.getHeight(), 2f,
                                                Interpolation.swing)), //Положение на экране
                        delay(1.5f), //Задержка
                        fadeOut(1.25f), //Исчезновение
                        run(load))); //Запуск сцены

        stage.addActor(splashImage);
    }

    @Override
    public void render(float delta){
        super.render(delta);

        controller.splashUpdate();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);

        actor.setPosition(width / 2 - splashImage.getWidth(),
                height / 2 + splashImage.getHeight());
        splashImage.setPosition(width / 2 - splashImage.getWidth(),
                height  + splashImage.getHeight());
    }

    @Override
    public void dispose(){
        super.dispose();

        texture.dispose();
        actor.dispose();
    }

}
