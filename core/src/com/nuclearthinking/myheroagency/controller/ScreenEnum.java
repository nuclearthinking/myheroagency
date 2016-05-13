package com.nuclearthinking.myheroagency.controller;

import com.nuclearthinking.myheroagency.view.*;

/**
 * Created by Izonami on 09.05.2016.
 */
public enum ScreenEnum {
    LOADING_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new LoadingScreen();
        }
    },

    SPLASH_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new SplashScreen();
        }
    },

    MAIN_MENU_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new MainMenuScreen();
        }
    },

    SETTINGS_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new SettingsScreen();
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
