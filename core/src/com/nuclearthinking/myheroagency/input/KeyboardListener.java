package com.nuclearthinking.myheroagency.input;

import com.badlogic.gdx.InputProcessor;
import com.nuclearthinking.myheroagency.GameState;
import com.nuclearthinking.myheroagency.GameStateManager;

/**
 * Date: 07.05.2016
 * Time: 16:56
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class KeyboardListener implements InputProcessor {
    GameState gameState;

    public KeyboardListener(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 45) {
            gameState.setState(GameStateManager.State.BATTLE);
        } else if (keycode == 51) {
            gameState.setState(GameStateManager.State.HOME);
        } else if (keycode == 33) {
            gameState.setState(GameStateManager.State.START);
        } else if (keycode == 46) {
            gameState.setState(GameStateManager.State.QUEST);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
