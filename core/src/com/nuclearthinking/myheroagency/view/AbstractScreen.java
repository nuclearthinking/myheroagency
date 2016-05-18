package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nuclearthinking.myheroagency.i18n.Localization;
import com.nuclearthinking.myheroagency.model.GameData;
import com.nuclearthinking.myheroagency.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

public abstract class AbstractScreen extends Stage implements Screen {
    final protected Logger logger = new SimpleLoggerFactory().getLogger(getName());
    final protected Localization locale = new Localization(this.getClass());
    final protected InputMultiplexer multi = new InputMultiplexer();

    protected GameData gameData;

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    AbstractScreen() {
        super(new StretchViewport(Constants.GAME_W, Constants.GAME_H, new OrthographicCamera()));
    }

    public abstract void buildStage();

    @Override
    public void show() {
        logger.info("Show screen: {}", getName());

        multi.addProcessor(this);
        Gdx.input.setInputProcessor(multi);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.act(delta);
        super.draw();
    }

    @Override
    public void resize(int width, int height) {
        logger.info("Resizing screen: {} to: y = {} x = {}", getName(), width, height);
        getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        logger.info("Pausing screen: {}", getName());
    }

    @Override
    public void resume() {
        logger.info("Resuming screen: {}", getName());
    }

    @Override
    public void hide() {
        logger.info("Hiding screen: {}", getName());
    }

    @Override
    public void dispose() {
        logger.info("Disposing screen: {}", getName());
    }

    private String getName() {
        return getClass().getSimpleName();
    }
}
