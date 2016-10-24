package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nuclearthinking.myheroagency.controller.manager.GameDataManager;
import com.nuclearthinking.myheroagency.i18n.Localization;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "AbstractScreen")
public abstract class AbstractScreen implements Screen {

    protected final Localization locale = new Localization(this.getClass());
    protected final InputMultiplexer multi = new InputMultiplexer();

    protected final Stage stage;

    protected @Getter @Setter
    GameDataManager gameDataManager;

    AbstractScreen() {
        stage = new Stage(new ScreenViewport(new OrthographicCamera()));
        locale.loadBundle();
    }

    public abstract void buildStage();

    @Override
    public void show() {
        log.info("Show screen: {}", getName());

        multi.addProcessor(stage);
        Gdx.input.setInputProcessor(multi);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        log.info("Resizing screen: {} to: x = {} y = {}", getName(), width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        log.info("Pausing screen: {}", getName());
    }

    @Override
    public void resume() {
        log.info("Resuming screen: {}", getName());
    }

    @Override
    public void hide() {
        log.info("Hiding screen: {}", getName());
    }

    @Override
    public void dispose() {
        log.info("Disposing screen: {}", getName());
    }

    private String getName() {
        return getClass().getSimpleName();
    }

}
