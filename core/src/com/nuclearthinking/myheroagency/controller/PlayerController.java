package com.nuclearthinking.myheroagency.controller;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.nuclearthinking.myheroagency.controller.systems.PlayerSystem;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Izonami on 23.06.2016.
 */
@RequiredArgsConstructor
public class PlayerController implements InputProcessor {

    @NonNull private final Engine engine;

    enum Keys {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    static final Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();

    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.UP, false);
        keys.put(Keys.DOWN, false);
    }

    private void leftPressed() {
        keys.get(keys.put(Keys.LEFT, true));
    }

    private void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    private void upPressed() {
        keys.get(keys.put(Keys.UP, true));
    }

    private void downPressed() {
        keys.get(keys.put(Keys.DOWN, true));
    }

    private void leftReleased()
    {
        keys.get(keys.put(Keys.LEFT, false));
    }

    private void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
    }

    private void upReleased() {
        keys.get(keys.put(Keys.UP, false));
    }

    private void downReleased() {
        keys.get(keys.put(Keys.DOWN, false));
    }

    public void update(){
        processInput();
    }

    private void processInput() {
        byte accelX = 0;
        byte accelY = 0;

        if (keys.get(Keys.LEFT))
            accelX = -1;

        if (keys.get(Keys.RIGHT))
            accelX = 1;

        if (keys.get(Keys.UP))
            accelY = 1;

        if (keys.get(Keys.DOWN))
            System.out.println("Down");

        if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)))
            System.out.println("1");

        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)))
            System.out.println("2");

        engine.getSystem(PlayerSystem.class).setAccelX(accelX);
        engine.getSystem(PlayerSystem.class).setAccelY(accelY);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:leftPressed();
                break;
            case Input.Keys.RIGHT:rightPressed();
                break;
            case Input.Keys.UP:upPressed();
                break;
            case Input.Keys.DOWN:downPressed();
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:leftReleased();
                break;
            case Input.Keys.RIGHT:rightReleased();
                break;
            case Input.Keys.UP:upReleased();
                break;
            case Input.Keys.DOWN:downReleased();
                break;
        }
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
