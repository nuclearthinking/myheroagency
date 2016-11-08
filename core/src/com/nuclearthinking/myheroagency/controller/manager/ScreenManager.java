package com.nuclearthinking.myheroagency.controller.manager;

import com.badlogic.gdx.Game;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import lombok.NonNull;
import lombok.val;

/**
 * Created by Izonami on 09.05.2016.
 * Синглтоновский класс для управления экранами
 */
public final class ScreenManager {

    private static ScreenManager instance;

    private Game game;

    private ScreenManager() {
        super();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    //Вызывается в классе Game для правильной инициализации синглтона
    public void initialize(@NonNull final Game game) {
        this.game = game;
    }

    public void showScreen(@NonNull final ScreenEnum screenEnum, final Object... params) {
        val currentScreen = game.getScreen(); //Получаем текущий экран
        val newScreen = screenEnum.getScreen(params); //Получаем новый экран

        newScreen.buildStage(); //Строим новый экран
        game.setScreen(newScreen); //Устанавливаем новый экран

        //Если экран не нул, вызываем его диспоуз метод
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }

}
