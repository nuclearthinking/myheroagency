package com.nuclearthinking.myheroagency.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import static com.nuclearthinking.myheroagency.utils.Constants.PREFERENCES_NAME;

public class Settings {

    private static final String LANGUAGE_KEY = "language";
    private static final String DEFAULT_LANGUAGE = "ru";
    private static Logger logger = new SimpleLoggerFactory().getLogger("Settings");

    private String language;
    private String height;
    private String width;

    private static Settings defaultSettings() {
        logger.info("Loading default settings");
        Settings settings = new Settings();
        settings.setLanguage(DEFAULT_LANGUAGE);
        return settings;
    }

    public static Settings loadSettings() {
        logger.info("Loading settings {} from preferences", PREFERENCES_NAME);
        Settings settings = new Settings();
        Preferences preferences = Gdx.app.getPreferences(PREFERENCES_NAME);
        if (preferences == null) {
            logger.error("Can't load preferences {}", PREFERENCES_NAME);
            return defaultSettings();
        }
        settings.setLanguage(preferences.getString(LANGUAGE_KEY, DEFAULT_LANGUAGE));

        return settings;
    }

    public void save() {
        Preferences preferences = Gdx.app.getPreferences(PREFERENCES_NAME);
        preferences.clear();
        preferences.putString(LANGUAGE_KEY, language);
        preferences.putString("height", height);
        preferences.putString("width", width);
        preferences.flush();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getHeight()
    {
        return Integer.parseInt(height);
    }

    public void setHeight(String height){
        this.height = height;
    }

    public int getWidth()
    {
        return Integer.parseInt(width);
    }

    public void setWidth(String width){
        this.width = width;
    }

}
