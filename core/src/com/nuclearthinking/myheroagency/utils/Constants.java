package com.nuclearthinking.myheroagency.utils;

import com.badlogic.gdx.Preferences;

/**
 * Created by Izonami on 05.05.2016.
 */
public final class Constants {

    public static final String GAME_TITLE = "My Hero Agency";
    public static final String COM_NAME = "FOG";
    public static final int GAME_W = 480;
    public static final int GAME_H = 320;
    public static final float ASPECT_RATIO = GAME_W / GAME_H;
    public static final float PIXELS_TO_METRES = 1.0f / 32.0f;
    public static final String ALL_CHARACTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz"
            + "1234567890.,:;_¡!¿?\"'+-*/()[]={}%";
    public static final String SAVE_NAME = "myheroagency.bin";
    /**Что бы избежать коллизии лучше задавать такое имя. Так советуют в {@link Preferences}**/
    public static final String PREFERENCES_NAME = "com.nuclearthinking.myheroagency.myheroagency.settings";
    public static boolean DEBUG = true; //TODO: Выключить в самом конце

    //Константы ассетов
    public static final String ASSET_FILE = "asset/main.xml";
    public static final String ASSET_GROUP_LOADING = "loading";
    public static final String ASSET_GROUP_LOCALE = "localization";
    public static final String ASSET_GROUP_BASE = "base";
    public static final String ASSET_GROUP_QUEST = "quest";

    public static final String SPLASH_IMG = "img/splash.png";
    public static final String PLAYER_PACK = "player/player.pack";
    public static final String MAP = "map/testMap.tmx";
    public static final String QUEST_JSON = "quest/quest.json";
    public static final String UI_JSON = "ui/ui.json";
    public static final String UI_ATLAS = "ui/ui.atlas";
    public static final String UI_SKIN_TYPE = "kramola";
    public static final String FONT_ROBO_LIGHT = "font/RobotoSlab-Light.ttf";
    public static final String FONT_ROBO_BOLD = "font/RobotoSlab-Bold.ttf";

}
