package com.nuclearthinking.myheroagency;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nuclearthinking.myheroagency.controller.GameStateManager;

public class Main extends Game {
    private SpriteBatch batch;
    private GameStateManager gsm;
    private OrthographicCamera camera;
    private BitmapFont font;
    Texture img;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        font = new BitmapFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        gsm = new GameStateManager(this);
    }

    @Override
    public void render() {
        super.render();

        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Очистка экрана

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render();
    }

    @Override
    public void resize(int width, int height){
        super.resize(width, height);

       gsm.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        gsm.dispose();
        batch.dispose();
    }

    public SpriteBatch getBatch(){
        return batch;
    }

    public OrthographicCamera getCamera(){
        return camera;
    }

    public BitmapFont getFont(){
        return font;
    }
}
