package com.nuclearthinking.myheroagency.model;

import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static com.nuclearthinking.myheroagency.utils.Constants.PREFERENCES_NAME;

@Slf4j(topic = "Settings")
public final class Settings {

    private final String LANGUAGE_KEY = "language";
    private final String HEIGHT_KEY = "height";
    private final String WIDTH_KEY = "width";
    private final String DEFAULT_LANGUAGE = "en";

    private @Getter @Setter String language;
    private @Getter @Setter int height;
    private @Getter @Setter int width;

    //Сохранение позиций для UI
    private final String POS_STATX = "posstatx";
    private final String POS_STATY = "posstaty";

    private @Getter @Setter float posStatX;
    private @Getter @Setter float posStatY;

    private static Settings instance;

    private Settings(){}

    public static Settings getInstance(){
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }

    private void defaultSettings() {
        log.info("Loading default settings");

        setLanguage(DEFAULT_LANGUAGE);
        setWidth(Constants.GAME_W);
        setHeight(Constants.GAME_H);

        setPosStatX(Constants.GAME_W/2);
        setPosStatY(Constants.GAME_H/2);
    }

    public void loadSettings() {
        log.info("Loading settings {} from preferences", PREFERENCES_NAME);

        val preferences = Gdx.app.getPreferences(PREFERENCES_NAME);

        if (preferences.get().isEmpty()) {
            log.error("Can't load preferences {}", PREFERENCES_NAME);
            defaultSettings();
        }
        else{
            log.info("Loading settings from preferences {}", PREFERENCES_NAME);
            setLanguage(preferences.getString(LANGUAGE_KEY, language));
            setHeight(preferences.getInteger(HEIGHT_KEY, height));
            setWidth(preferences.getInteger(WIDTH_KEY, width));

            setPosStatX(preferences.getFloat(POS_STATX, posStatX));
            setPosStatY(preferences.getFloat(POS_STATY, posStatY));
        }
    }

    public void save() {
        val preferences = Gdx.app.getPreferences(PREFERENCES_NAME);

        preferences.clear();
        preferences.putString(LANGUAGE_KEY, language);
        preferences.putInteger(HEIGHT_KEY, height);
        preferences.putInteger(WIDTH_KEY, width);

        preferences.putFloat(POS_STATX, posStatX);
        preferences.putFloat(POS_STATY, posStatY);
        preferences.flush();
    }
}
