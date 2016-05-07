package com.nuclearthinking.myheroagency;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Date: 07.05.2016
 * Time: 16:13
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public abstract class GameState {

    public SpriteBatch spriteBatch;
    public OrthographicCamera camera;
    private GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.spriteBatch = gsm.getSpriteBatch();
        this.camera = gsm.getCamera();
        this.gsm  = gsm;
    }

    public abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render();

    public abstract void dispose();

    public void setState(GameStateManager.State state){
        gsm.pushState(state);
    }
}
