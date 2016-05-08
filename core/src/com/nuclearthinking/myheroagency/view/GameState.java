package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nuclearthinking.myheroagency.Main;
import com.nuclearthinking.myheroagency.controller.GameStateManager;

/**
 * Created by Izonami on 06.05.2016.
 */
public abstract class GameState {
    protected GameStateManager gsm;
    protected Main app;
    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected BitmapFont font;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        this.app = gsm.getApp();
        batch = app.getBatch();
        camera = app.getCamera();
        font = app.getFont();
    }

    public void resize(int w, int h) {
        camera.setToOrtho(false, w, h);
    }

    public abstract void update(float delta);
    public abstract void render();
    public abstract void dispose();
}
