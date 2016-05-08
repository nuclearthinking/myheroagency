package com.nuclearthinking.myheroagency.controller;

import com.nuclearthinking.myheroagency.Main;
import com.nuclearthinking.myheroagency.view.GameState;
import com.nuclearthinking.myheroagency.view.HomeScreen;
import com.nuclearthinking.myheroagency.view.StartScreen;

import java.util.Stack;

/**
 * Created by Izonami on 06.05.2016.
 */
public class GameStateManager {
    private final Main app;
    private Stack<GameState> states;

    public enum State{
        SPLASH,
        GAME
    }

    public GameStateManager(final Main app){
        this.app = app;
        this.states = new Stack<GameState>();
        this.setState(State.SPLASH);
    }

    public void update(float delta) {
        states.peek().update(delta);
    }

    public void render() {
        states.peek().render();
    }

    public void dispose() {
        for(GameState gs : states) {
            gs.dispose();
        }
        states.clear();
    }

    public void resize(int w, int h) {
        states.peek().resize(w, h);
    }

    public void setState(State state) {
        if(states.size() >= 1) {
            states.pop().dispose();
        }
        states.push(getState(state));
    }

    private GameState getState(State state) {
        switch(state) {
            case SPLASH: return new StartScreen(this);
            case GAME: return new HomeScreen(this);
            default: return null;
        }
    }

    public Main getApp(){
        return app;
    }
}
