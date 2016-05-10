package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Izonami on 10.05.2016.
 */
public class Assets {

    private static Assets instance;
    private static AssetManager assetManager;

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    public void init(){
        assetManager = new AssetManager();
    }

    public void preLoadAssets(){
        assetManager.load("img/loading.pack", TextureAtlas.class);
    }

    public void postLoadAssets(){
        assetManager.load("img/splash.png", Texture.class);
    }

    public AssetManager getAssetManager(){
        return assetManager;
    }
}
