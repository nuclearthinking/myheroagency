package com.nuclearthinking.myheroagency;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nuclearthinking.myheroagency.input.KeyboardListener;
import com.nuclearthinking.myheroagency.view.BattleScreen;
import com.nuclearthinking.myheroagency.view.HomeScreen;
import com.nuclearthinking.myheroagency.view.StartScreen;
import com.nuclearthinking.myheroagency.view.TaskScreen;

import java.util.Stack;

/**
 * Date: 07.05.2016
 * Time: 15:57
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class GameStateManager {

    public enum GameState {START, HOME, QUEST, BATTLE}

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Stack<State> states;

    public GameStateManager() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        states = new Stack<State>();
        pushState(GameState.START);
        Gdx.input.setInputProcessor(new KeyboardListener(states.peek()));
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render() {
        states.peek().render();
    }

    private com.nuclearthinking.myheroagency.State getState(GameState gameState) {
        switch (gameState) {
            case START:
                return new StartScreen(this);
            case HOME:
                return new HomeScreen(this);
            case QUEST:
                return new TaskScreen(this);
            case BATTLE:
                return new BattleScreen(this);
            default:
                return null;
        }
    }

    public void setState(GameState gameState) {
        popState();
        pushState(gameState);
    }

    public void pushState(GameState gameState) {
        states.push(getState(gameState));
    }

    public void popState() {
        com.nuclearthinking.myheroagency.State g = states.pop();
        g.dispose();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
