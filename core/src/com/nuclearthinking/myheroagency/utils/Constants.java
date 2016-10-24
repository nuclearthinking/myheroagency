package com.nuclearthinking.myheroagency.utils;

import com.badlogic.gdx.Preferences;

/**
 * Created by Izonami on 05.05.2016.
 */
public final class Constants {

    public static final String GAME_TITLE = "My Hero Agency";
    public static final int GAME_W = 480;
    public static final int GAME_H = 320;
    public static final float ASPECT_RATIO = GAME_W / GAME_H;
    public static final String ALL_CHARACTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz"
            + "1234567890.,:;_¡!¿?\"'+-*/()[]={}%";
    public static final String SAVE_NAME = "myheroagency.bin";
    /**Что бы избежать коллизии лучше задавать такое имя. Так советуют в {@link Preferences}**/
    public static final String PREFERENCES_NAME = "com.nuclearthinking.myheroagency.myheroagency.settings";
    public static boolean DEBUG = true; //TODO: Выключить в самом конце

}
