package com.nuclearthinking.myheroagency.model;

import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static com.nuclearthinking.myheroagency.utils.Constants.PREFERENCES_NAME;

@Slf4j(topic = "Settings")
public class Settings {

    private static final String LANGUAGE_KEY = "language";
    private static final String HEIGHT_KEY = "height";
    private static final String WIDTH_KEY = "width";
    private static final String DEFAULT_LANGUAGE = "en";

    private @Setter static String language;
    private @Setter static int height;
    private @Setter static int width;

    private Settings defaultSettings() {
        log.info("Loading default settings");

        val settings = new Settings();

        settings.setLanguage(DEFAULT_LANGUAGE);
        settings.setWidth(Constants.GAME_W);
        settings.setHeight(Constants.GAME_H);
        return settings;
    }

    public static Settings loadSettings() {
        log.info("Loading settings {} from preferences", PREFERENCES_NAME);

        val settings = new Settings();
        val preferences = Gdx.app.getPreferences(PREFERENCES_NAME);

        if (preferences == null) {
            log.error("Can't load preferences {}", PREFERENCES_NAME);
            return settings.defaultSettings();
        }
        settings.setLanguage(preferences.getString(LANGUAGE_KEY, DEFAULT_LANGUAGE));
        settings.setHeight(preferences.getInteger(HEIGHT_KEY, Constants.GAME_H));
        settings.setWidth(preferences.getInteger(WIDTH_KEY, Constants.GAME_W));

        return settings;
    }

    public void save() {
        val preferences = Gdx.app.getPreferences(PREFERENCES_NAME);

        preferences.clear();
        preferences.putString(LANGUAGE_KEY, language);
        preferences.putInteger(HEIGHT_KEY, height);
        preferences.putInteger(WIDTH_KEY, width);
        preferences.flush();
    }

    public static String getLanguage() {
        return language;
    }

    public static int getHeight()
    {
        return height;
    }

    public static int getWidth()
    {
        return width;
    }

}
