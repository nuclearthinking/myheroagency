package com.nuclearthinking.myheroagency.view;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.nuclearthinking.myheroagency.model.entity.World;
import com.nuclearthinking.myheroagency.model.entity.systems.*;

/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class HomeScreen extends AbstractScreen {

    World world;
    PooledEngine engine;

    public HomeScreen() {
        engine = new PooledEngine();
        world = new World(engine);
    }

    @Override
    public void buildStage() {
        engine.addSystem(new PlayerSystem(world));
        engine.addSystem(new CameraSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new StateSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderingSystem(stage.getBatch()));

        world.create();
    }

    public void update (float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;

        engine.update(deltaTime);

        updateRunning();
    }

    private void updateRunning () {
        Application.ApplicationType appType = Gdx.app.getType();

        float accelX = 0.0f;

        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            accelX = Gdx.input.getAccelerometerX();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) accelX = 5f;
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) accelX = -5f;
        }

        engine.getSystem(PlayerSystem.class).setAccelX(accelX);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

}
