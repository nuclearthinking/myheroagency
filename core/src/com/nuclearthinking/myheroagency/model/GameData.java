package com.nuclearthinking.myheroagency.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameData {

    private static final String SAVE_NAME = "MyHeroAcademy Save";
    private static final String PLAYER_LEVEL = "playerLevel";
    private static final String PLAYER_EXP = "playerExp";

    private Player player;


    public GameData() {
        player = new Player();
    }

    public static void save(GameData gameData) {
        Preferences preferences = Gdx.app.getPreferences(SAVE_NAME);
        preferences.putInteger(PLAYER_LEVEL, gameData.player.level);
        preferences.putInteger(PLAYER_EXP, gameData.player.exp);
        preferences.flush();
    }

    public static GameData load() {
        GameData gameData = new GameData();
        Preferences preferences = Gdx.app.getPreferences("MyHeroAcademy Save");
        gameData.player = new Player();
        gameData.player.level = preferences.getInteger(PLAYER_LEVEL);
        gameData.player.exp = preferences.getInteger(PLAYER_EXP);

        return gameData;
    }
}
