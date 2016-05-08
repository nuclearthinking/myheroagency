package com.nuclearthinking.myheroagency;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Date: 07.05.2016
 * Time: 16:13
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public abstract class State {

    public SpriteBatch spriteBatch;
    public OrthographicCamera camera;
    public GameStateManager gsm;

    public State(GameStateManager gsm){
        this.spriteBatch = gsm.getSpriteBatch();
        this.camera = gsm.getCamera();
        this.gsm  = gsm;
    }

    public abstract void handleKeyboard(int key);

    public abstract void update(float dt);

    public abstract void render();

    public abstract void dispose();

}
