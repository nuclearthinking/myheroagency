package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.nuclearthinking.myheroagency.GameState;
import com.nuclearthinking.myheroagency.GameStateManager;
import com.nuclearthinking.myheroagency.input.KeyboardListener;
import com.nuclearthinking.myheroagency.ui.FontFactory;

/**
 * Date: 05.05.2016
 * Time: 7:00
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class StartScreen extends GameState {

    public StartScreen(GameStateManager gsm) {
        super(gsm);
        Gdx.input.setInputProcessor(new KeyboardListener(this));
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        FontFactory.getFont9().draw(spriteBatch, "Start screen", 20, 20);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
