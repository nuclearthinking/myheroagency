package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.listener.button.menu.ExitListener;
import com.nuclearthinking.myheroagency.controller.listener.button.menu.LoadListener;
import com.nuclearthinking.myheroagency.controller.listener.button.menu.PlayListener;
import com.nuclearthinking.myheroagency.controller.listener.button.menu.SettingListener;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Izonami on 13.05.2016.
 */
public final class MainMenuScreen extends AbstractScreen {

    private final Table table;
    private final UiFactory uiFactory;
    private final String screenName = getClass().getSimpleName();
    private TextButton play, load, settings, exit;

    public MainMenuScreen(){
        uiFactory = new UiFactory();
        table = new Table(); // Создаем таблицу
    }

    @Override
    public void buildStage() {
        table.setDebug(Constants.DEBUG); // Включаем дебаг режим (Разные прямоугольнико вокруг кнопок это оно самое)
        table.setFillParent(true);

        initButton(); // Создаем кнопки

        // Таблица рулит размером кнопок, отступами и прочей хренотой
        table.add(play).left().expandX().width(100).height(40).pad(10);
        table.row(); // Перенос строки
        table.add(load).left().expandX().width(100).height(40).pad(10);
        table.row();
        table.add(settings).left().expandX().width(100).height(40).pad(10);
        table.row();
        table.add(exit).left().expandX().width(100).height(40).pad(10);

        stage.addActor(table); // Добавляем таблицу на Stage
        // Этот экшен "выплывает" меню
        stage.addAction(sequence(moveTo(stage.getWidth(), 0), moveTo(0, 0, .5f))); // Это чисто попробовать возможности. Акшены очень мощьная штука.
    }

    private void initButton() {

        play = uiFactory.getTextButton("mms.buttonPlay", screenName, locale);
        play.getLabel().setFontScale(.9f);
        play.getLabel().setColor(Color.FOREST);
        play.addListener(new PlayListener(play)); //Добавляет листнер кнопке

        load = uiFactory.getTextButton("mms.buttonLoad", screenName, locale);
        load.getLabel().setFontScale(.7f);
        load.addListener(new LoadListener(load));

        settings = uiFactory.getTextButton("mms.buttonSettings", screenName, locale);
        settings.getLabel().setFontScale(.7f);
        settings.addListener(new SettingListener(settings));

        exit = uiFactory.getTextButton("mms.buttonExit", screenName, locale);
        exit.getLabel().setFontScale(.7f);
        exit.addListener(new ExitListener(exit));
    }

    @Override
    public void dispose() {
        super.dispose();

        play.clearListeners();
        load.clearListeners();
        settings.clearListeners();
        exit.clearListeners();
    }
}
