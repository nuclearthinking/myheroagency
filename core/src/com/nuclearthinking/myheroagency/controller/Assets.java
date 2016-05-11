package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Izonami on 10.05.2016.
 */
public class Assets {

    private static Assets instance;
    private AssetManager assetManager;
    private Skin skin;

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    public void init(){
        assetManager = new AssetManager();
        skin = new Skin();
    }

    //До загрузки
    public void preLoadAssets(){

    }

    //После загрузки
    public void postLoadAssets(){
        assetManager.load("img/splash.png", Texture.class);
        assetManager.load("ui/ui.atlas", TextureAtlas.class);
        assetManager.load("ui/ui.png", Texture.class);
    }

    public AssetManager getAssetManager(){
        return assetManager;
    }

    public Skin getSkin(){
        return skin;
    }

}
