package com.nuclearthinking.myheroagency;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Main extends Game {
    GameStateManager gsm;
    @Override
    public void create() {
      gsm =   new GameStateManager();
    }

    @Override
    public void render() {
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render();
    }
}
