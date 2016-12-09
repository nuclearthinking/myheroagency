package com.nuclearthinking.myheroagency.view;

import com.badlogic.ashley.core.PooledEngine;
import com.nuclearthinking.myheroagency.controller.LayerController;
import com.nuclearthinking.myheroagency.controller.PlayerController;
import com.nuclearthinking.myheroagency.controller.manager.BuildHudManager;
import com.nuclearthinking.myheroagency.controller.manager.GameWorldManager;
import com.nuclearthinking.myheroagency.controller.systems.*;
import com.nuclearthinking.myheroagency.model.ui.hud.HudComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
@Slf4j(topic = "HomeScreen")
public final class HomeScreen extends AbstractScreen {

    private final GameWorldManager gameWorldManager;
    private final PlayerController pc;
    private final PooledEngine engine;
    private final LayerController lc;

    public HomeScreen() {
        engine = new PooledEngine();
        gameWorldManager = new GameWorldManager(engine, stage);
        pc = new PlayerController(engine);
        lc = new LayerController();
    }

    @Override
    public void buildStage() {
        log.info("Add Entity Systems");
        engine.addSystem(new MapSystem(gameWorldManager.getWorld()));
        engine.addSystem(new RenderingSystem(engine.getSystem(MapSystem.class).getBatch(), gameWorldManager.getWorld()));
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new NpcSystem());
        engine.addSystem(new MonsterSystem());
        engine.addSystem(new LightSystem(gameWorldManager.getWorld()));
        engine.addSystem(new CameraSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new StateSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new HudSystem());

        engine.getSystem(MapSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());
        engine.getSystem(LightSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());

        log.info("Creating World");
        gameWorldManager.create();

        log.info("Add Processor");
        multi.addProcessor(pc);
        multi.addProcessor(lc);
        multi.addProcessor(HudComponent.getStage());
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
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        engine.getSystem(RenderingSystem.class).resize(width, height);
        BuildHudManager.getInstance().rebuildAll(width, height);
    }
}
