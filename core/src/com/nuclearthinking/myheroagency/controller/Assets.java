package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.I18NBundle;
import com.nuclearthinking.myheroagency.model.Settings;

import java.util.Locale;

/**
 * Created by Izonami on 10.05.2016.
 */
public class Assets {

    private static Assets instance;
    private AssetManager assetManager;
    private Locale locale;
    private Settings settings;

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    public void init() {
        assetManager = new AssetManager();
        settings = Settings.loadSettings();
        locale = new Locale(settings.getLanguage());
    }

    public void loadAssets() {
        assetManager.load("img/splash.png", Texture.class);
        assetManager.load("i18n/BattleScreen", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(locale));
        assetManager.load("i18n/HomeScreen", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(locale));
        assetManager.load("i18n/StartScreen", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(locale));
        assetManager.load("i18n/TaskScreen", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(locale));
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

}
