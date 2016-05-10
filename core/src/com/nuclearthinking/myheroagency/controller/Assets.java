package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Izonami on 10.05.2016.
 */
public class Assets {

    private static Assets instance;
    private AssetManager assetManager;

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    public void init(){
        assetManager = new AssetManager();
    }

    //До загрузки
    public void preLoadAssets(){

    }

    //После загрузки
    public void postLoadAssets(){
        assetManager.load("img/splash.png", Texture.class);
    }

    public AssetManager getAssetManager(){
        return assetManager;
    }

}
