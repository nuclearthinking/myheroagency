package com.nuclearthinking.myheroagency.controller;

import com.nuclearthinking.myheroagency.model.GameData;
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
            if (params.length > 0) {
                return new HomeScreen((GameData) params[0]);
            } else return new HomeScreen();
        }
    },

    BATTLE_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new BattleScreen();
        }
    },

    TASK_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new TaskScreen((GameData) params[0]);
        }
    };

    public abstract AbstractScreen getScreen(Object... params);

}
