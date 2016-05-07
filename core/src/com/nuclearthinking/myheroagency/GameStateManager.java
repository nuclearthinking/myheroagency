package com.nuclearthinking.myheroagency;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public enum State {START, HOME, QUEST, BATTLE}

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Stack<GameState> gameStates;

    public GameStateManager() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        gameStates = new Stack<GameState>();
        pushState(State.START);
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    public void render() {
        gameStates.peek().render();
    }

    private GameState getState(State state) {
        switch (state) {
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

    public void setState(State state) {
        popState();
        pushState(state);
    }

    public void pushState(State state) {
        gameStates.push(getState(state));
    }

    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
