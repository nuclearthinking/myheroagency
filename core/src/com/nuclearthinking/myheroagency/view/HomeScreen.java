package com.nuclearthinking.myheroagency.view;

import com.badlogic.ashley.core.PooledEngine;
import com.nuclearthinking.myheroagency.controller.LayerController;
import com.nuclearthinking.myheroagency.controller.PlayerController;
import com.nuclearthinking.myheroagency.model.entity.GameWorld;
import com.nuclearthinking.myheroagency.model.entity.systems.*;

/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class HomeScreen extends AbstractScreen {

    private final GameWorld gameWorld;
    private final PlayerController pc;
    private final PooledEngine engine;
    private final LayerController lc;

    public HomeScreen() {
        engine = new PooledEngine();
        gameWorld = new GameWorld(engine, stage.getBatch());
        pc = new PlayerController(engine);
        lc = new LayerController(engine);
    }

    @Override
    public void buildStage() {
        engine.addSystem(new MapSystem(gameWorld.getWorld()));
        engine.addSystem(new RenderingSystem(engine.getSystem(MapSystem.class).getBatch(), gameWorld.getWorld()));
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new NpcSystem());
        engine.addSystem(new MonsterSystem());
        engine.addSystem(new LightSystem(gameWorld.getWorld()));
        engine.addSystem(new CameraSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new StateSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new HudSystem());

        engine.getSystem(MapSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());
        engine.getSystem(LightSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());

        gameWorld.create();

        multi.addProcessor(pc);
    }

    public void update (float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;

        engine.update(deltaTime);
        pc.update();
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        update(delta);
        lc.update();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        engine.getSystem(RenderingSystem.class).resize(width, height);
        engine.getSystem(HudSystem.class).resize(width,height);
    }

}
