package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nuclearthinking.myheroagency.controller.GameStateManager;
import com.nuclearthinking.myheroagency.utils.TextActor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.nuclearthinking.myheroagency.utils.Constants.GAME_H;
import static com.nuclearthinking.myheroagency.utils.Constants.GAME_W;

/**
 * Date: 05.05.2016
 * Time: 7:00
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class StartScreen extends GameState {
    private Image splashImage;
    private TextActor actor;
    private Stage stage;

    public StartScreen(final GameStateManager gsm) {
        super(gsm);

        stage = new Stage(new StretchViewport(GAME_W, GAME_H, camera));

        stage.clear();

        actor = new TextActor(font, "   " + "FOG", stage); //TODO: Пробелы это костыль, нужно передавать аргумент, что бы двигать положение текста
        actor.setOrigin(actor.getWidth() / 2, actor.getHeight() / 2);
        actor.setPosition(GAME_W / 2 - 32, GAME_H / 2 + 32);
        actor.fadeText();

        Runnable load = new Runnable() {
            @Override
            public void run() {
                gsm.setState(GameStateManager.State.GAME);
            }
        };

        Texture texture = new Texture(Gdx.files.internal("img/splash.png"));
        splashImage = new Image(texture);
        splashImage.setOrigin(splashImage.getWidth() / 2, splashImage.getHeight() / 2);
        splashImage.setPosition(GAME_W / 2 - 32, GAME_H + 32);
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
                                        moveTo(GAME_W / 2 - 32, GAME_H / 2 - 32, 2f, Interpolation.swing)), //Положение на экране
                        delay(1.5f), //Задержка
                        fadeOut(1.25f), //Исчезновение
                        run(load))); //Запуск сцены

        stage.addActor(splashImage);
    }

    @Override
    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render() {
        stage.draw();
    }

    @Override
    public void resize(int w, int h) {
        super.resize(w,h);

        stage.getViewport().update(w, h, true);
    }

    @Override
    public void dispose() {
    }
}
