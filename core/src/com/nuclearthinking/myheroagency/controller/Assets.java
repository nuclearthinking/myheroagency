package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

/**
 * Created by Izonami on 10.05.2016.
 */
public class Assets {

    private static Assets instance;
    private AssetManager assetManager;
    private Locale locale;
    private Skin skin;

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    public void init() {
        assetManager = new AssetManager();
        locale = new Locale("ru"); //TODO: Брать локаль из настроек
        skin = new Skin();
    }

    public void loadAssets() {
        assetManager.load("img/splash.png", Texture.class);
        assetManager.load("i18n/BattleScreen", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(locale));
        assetManager.load("i18n/HomeScreen", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(locale));
        assetManager.load("i18n/StartScreen", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(locale));
        assetManager.load("i18n/TaskScreen", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(locale));
        assetManager.load("i18n/MainMenuScreen", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(locale));
        assetManager.load("ui/ui.atlas", TextureAtlas.class);
        assetManager.load("ui/ui.json", Skin.class, new SkinLoader.SkinParameter("ui/ui.atlas"));
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public Skin getSkin(){
        return skin;
    }

    public void setSkin(Skin skin){
        this.skin = skin;
    }

}
