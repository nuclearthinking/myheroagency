package com.nuclearthinking.myheroagency.view;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.nuclearthinking.myheroagency.model.entity.GameWorld;
import com.nuclearthinking.myheroagency.model.entity.systems.*;
import lombok.val;

/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class HomeScreen extends AbstractScreen {

    private final GameWorld gameWorld;
    private final PooledEngine engine;

    public HomeScreen() {
        engine = new PooledEngine();
        gameWorld = new GameWorld(engine);
    }

    @Override
    public void buildStage() {
        engine.addSystem(new MapSystem(gameWorld.getWorld()));
        engine.addSystem(new LightSystem(gameWorld.getWorld()));
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new NpcSystem());
        engine.addSystem(new CameraSystem());
        engine.addSystem(new GravitySystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new CollisionSystem(gameWorld));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new StateSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderingSystem(engine.getSystem(MapSystem.class).getBatch()));

        engine.getSystem(MapSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());
        engine.getSystem(LightSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());

        gameWorld.create();
    }

    public void update (float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;

        engine.update(deltaTime);

        gameWorld.getWorld().step(Gdx.graphics.getDeltaTime(), 0, 0);

        updateRunning();
    }

    private void updateRunning () {
        val appType = Gdx.app.getType();

        float accelX = 0.0f;

        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            accelX = Gdx.input.getAccelerometerX();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) accelX = 5f;
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) accelX = -5f;
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
