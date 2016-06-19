package com.nuclearthinking.myheroagency.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.nuclearthinking.myheroagency.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import static com.nuclearthinking.myheroagency.utils.Constants.PREFERENCES_NAME;

public class Settings {

    private static final String LANGUAGE_KEY = "language";
    private static final String HEIGHT_KEY = "height";
    private static final String WIDTH_KEY = "width";
    private static final String DEFAULT_LANGUAGE = "en";
    private static Logger logger = new SimpleLoggerFactory().getLogger("Settings");

    private static String language;
    private static int height;
    private static int width;

    private static Settings defaultSettings() {
        logger.info("Loading default settings");

        final Settings settings = new Settings();

        settings.setLanguage(DEFAULT_LANGUAGE);
        settings.setWidth(Constants.GAME_W);
        settings.setHeight(Constants.GAME_H);
        return settings;
    }

    public static Settings loadSettings() {
        logger.info("Loading settings {} from preferences", PREFERENCES_NAME);

        final Settings settings = new Settings();
        final Preferences preferences = Gdx.app.getPreferences(PREFERENCES_NAME);

        if (preferences == null) {
            logger.error("Can't load preferences {}", PREFERENCES_NAME);
            return defaultSettings();
        }
        settings.setLanguage(preferences.getString(LANGUAGE_KEY, DEFAULT_LANGUAGE));
        settings.setHeight(preferences.getInteger(HEIGHT_KEY, Constants.GAME_H));
        settings.setWidth(preferences.getInteger(WIDTH_KEY, Constants.GAME_W));

        return settings;
    }

    public void save() {
        final Preferences preferences = Gdx.app.getPreferences(PREFERENCES_NAME);

        preferences.clear();
        preferences.putString(LANGUAGE_KEY, language);
        preferences.putInteger(HEIGHT_KEY, height);
        preferences.putInteger(WIDTH_KEY, width);
        preferences.flush();
    }

    public static String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public static int getHeight()
    {
        return height;
    }

    public void setHeight(final int height){
        this.height = height;
    }

    public static int getWidth()
    {
        return width;
    }

    public void setWidth(final int width){
        this.width = width;
    }

}
