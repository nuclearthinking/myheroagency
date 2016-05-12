package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.nuclearthinking.myheroagency.view.AbstractScreen;

/**
 * Created by Izonami on 09.05.2016.
 */
public class ScreenManager {
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

    public void initialize(Game game) {
        this.game = game;
    }

    public void showScreen(ScreenEnum screenEnum, Object... params) {
        Screen currentScreen = game.getScreen();

        AbstractScreen newScreen = screenEnum.getScreen(params);
        newScreen.buildStage();
        game.setScreen(newScreen);

        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }

}