package com.nuclearthinking.myheroagency.controller;

import com.nuclearthinking.myheroagency.model.GameData;
import com.nuclearthinking.myheroagency.view.AbstractScreen;
import com.nuclearthinking.myheroagency.view.HomeScreen;
import com.nuclearthinking.myheroagency.view.LoadingScreen;
import com.nuclearthinking.myheroagency.view.StartScreen;

/**
 * Created by Izonami on 09.05.2016.
 */
public enum ScreenEnum {
    LOADING_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new LoadingScreen();
        }
    },

    START_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new StartScreen();
        }
    },

    HOME_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new HomeScreen();
        }
    };

    public abstract AbstractScreen getScreen(Object... params);
}
