package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by Izonami on 13.05.2016.
 */
public class MainMenuScreen extends AbstractScreen {

    private Table table;
    private TextButton play, load, settings, exit;
    private ClickListener buttonListener;

    @Override
    public void buildStage() {
        Asset.getInstance().setSkin(Asset.getInstance().get("ui/ui.json", Skin.class)); // Создаем скин на основе json
        Asset.getInstance().getSkin().addRegions(Asset
                .getInstance()
                .get("ui/ui.atlas", TextureAtlas.class)); // Добавляем области картинки полученные из атласа

        table = new Table(); // Создаем таблицу
        table.setDebug(Constants.DEBUG); // Включаем дебаг режим (Разные прямоугольнико вокруг кнопок это оно самое)
        table.setFillParent(true);


        createListener(); // Создаем слушателя для кнопок
        initButton(); // Создаем кнопки

        // Таблица рулит размером кнопок, отступами и прочей хренотой
        table.add(play).left().expandX().width(100).height(40).pad(10);
        table.row(); // Перенос строки
        table.add(load).left().expandX().width(100).height(40).pad(10);
        table.row();
        table.add(settings).left().expandX().width(100).height(40).pad(10);
        table.row();
        table.add(exit).left().expandX().width(100).height(40).pad(10);

        addActor(table); // Добавляем таблицу на Stage
        // Этот экшен "выплывает" меню
        addAction(sequence(moveTo(getWidth(), 0), moveTo(0, 0, .5f))); // Это чисто попробовать возможности. Акшены очень мощьная штука.
    }

    private void initButton() {
        // При создании нужно передавать название кнопки(лейбл), скин и соответсвующий скину шрифт. Default не поддерживает русский
        play = new TextButton(locale.get("buttonPlay"), Asset.getInstance().getSkin(), "kramola");
        play.getLabel().setFontScale(.9f);
        play.getLabel().setColor(Color.FOREST);
        play.addListener(buttonListener); //Добавляет листнер кнопке
        load = new TextButton(locale.get("buttonLoad"), Asset.getInstance().getSkin(), "kramola");
        load.getLabel().setFontScale(.7f);
        load.addListener(buttonListener);
        settings = new TextButton(locale.get("buttonSettings"), Asset.getInstance().getSkin(), "kramola");
        settings.getLabel().setFontScale(.7f);
        settings.addListener(buttonListener);
        exit = new TextButton(locale.get("buttonExit"), Asset.getInstance().getSkin(), "kramola");
        exit.getLabel().setFontScale(.7f);
        exit.addListener(buttonListener);
    }

    private void createListener() {
        buttonListener = new ClickListener() {
            /**
             * Собитие по нажатию на кнопку
             * @param event - определяет куда мы ткнули
             * @param x - можно определять по координатам
             * @param y
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (event.getListenerActor() == play) {
                    ScreenManager.getInstance().showScreen(ScreenEnum.HOME_SCREEN);
                } else if (event.getListenerActor() == load) {

                } else if (event.getListenerActor() == settings) {
                    ScreenManager.getInstance().showScreen(ScreenEnum.SETTINGS_SCREEN);
                } else if (event.getListenerActor() == exit) {
                    Gdx.app.exit();
                } else {
                    logger.error("Event clicked for button {} not found", event.getListenerActor());
                }
            }

            /**
             * Слушатель при наведении на область (маусовер)
             * @param event - определяет куда мы навели
             * @param x - можно определять по координатам
             * @param y
             * @param pointer - не особо разобрался что это
             * @param fromActor - аналогично
             */
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (event.getListenerActor() == play) {
                    play.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
                } else if (event.getListenerActor() == load) {
                    load.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
                } else if (event.getListenerActor() == settings) {
                    settings.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
                } else if (event.getListenerActor() == exit) {
                    exit.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
                } else {
                    logger.error("Event enter for button {} not found", event.getListenerActor());
                }
            }
        };
    }
}
