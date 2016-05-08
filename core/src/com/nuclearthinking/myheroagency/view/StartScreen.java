package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.nuclearthinking.myheroagency.State;
import com.nuclearthinking.myheroagency.GameStateManager;
import com.nuclearthinking.myheroagency.ui.FontFactory;

import static com.badlogic.gdx.Input.Keys;

/**
 * Date: 05.05.2016
 * Time: 7:00
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class StartScreen extends State {

    public StartScreen(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void handleKeyboard(int key) {
        if (key == Keys.Q) {
            gsm.setState(GameStateManager.GameState.BATTLE);
        } else if (key == Keys.W) {
            gsm.setState(GameStateManager.GameState.HOME);
        } else if (key == Keys.E) {
            gsm.setState(GameStateManager.GameState.START);
        } else if (key == Keys.R) {
            gsm.setState(GameStateManager.GameState.QUEST);
        }
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
