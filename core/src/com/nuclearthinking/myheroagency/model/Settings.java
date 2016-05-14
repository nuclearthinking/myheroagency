package com.nuclearthinking.myheroagency.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

public class Settings {

    private static final String PREFERENCES_NAME = "MyHeroAgency Settings";
    private static final String LANGUAGE_KEY = "language";
    private static final String DEFAULT_LANGUAGE = "ru";
    private static Logger logger = new SimpleLoggerFactory().getLogger("Settings");

    private String language;

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
        } else if (preferences.contains(LANGUAGE_KEY)) {
            settings.setLanguage(preferences.getString(LANGUAGE_KEY));
            logger.info("Language setting loaded");
        } else {
            logger.info("Preferences does not containing language, setting default value {}", DEFAULT_LANGUAGE);
            settings.setLanguage(DEFAULT_LANGUAGE);
        }

        return settings;
    }

    public void save() {
        Preferences preferences = Gdx.app.getPreferences(PREFERENCES_NAME);
        preferences.clear();
        preferences.putString(LANGUAGE_KEY, language);
        preferences.flush();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}
