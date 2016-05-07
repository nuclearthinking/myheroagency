package com.nuclearthinking.myheroagency.input;

import com.badlogic.gdx.InputProcessor;
import com.nuclearthinking.myheroagency.State;

/**
 * Date: 07.05.2016
 * Time: 16:56
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class KeyboardListener implements InputProcessor {
    State state;

    public KeyboardListener(State state) {
        this.state = state;
    }

    @Override
    public boolean keyDown(int keycode) {
        state.handleKeyboard(keycode);
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
