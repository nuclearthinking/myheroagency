package com.nuclearthinking.myheroagency.view;

import com.badlogic.ashley.core.PooledEngine;
import com.nuclearthinking.myheroagency.controller.LayerController;
import com.nuclearthinking.myheroagency.controller.PlayerController;
import com.nuclearthinking.myheroagency.controller.manager.GameWorldManager;
import com.nuclearthinking.myheroagency.model.components.hud.HudComponent;
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
        gameWorldManager = new GameWorldManager(engine, stage.getBatch());
        pc = new PlayerController(engine);
        lc = new LayerController(engine);
    }

    @Override
    public void buildStage() {
        log.info("Add Entity Systems");
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.MapSystem(gameWorldManager.getWorld()));
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.RenderingSystem(engine.getSystem(com.nuclearthinking.myheroagency.controller.systems.MapSystem.class).getBatch(), gameWorldManager.getWorld()));
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.PlayerSystem());
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.NpcSystem());
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.MonsterSystem());
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.LightSystem(gameWorldManager.getWorld()));
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.CameraSystem());
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.MovementSystem());
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.StateSystem());
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.AnimationSystem());
        engine.addSystem(new com.nuclearthinking.myheroagency.controller.systems.HudSystem());

        engine.getSystem(com.nuclearthinking.myheroagency.controller.systems.MapSystem.class).setCamera(engine.getSystem(com.nuclearthinking.myheroagency.controller.systems.RenderingSystem.class).getCamera());
        engine.getSystem(com.nuclearthinking.myheroagency.controller.systems.LightSystem.class).setCamera(engine.getSystem(com.nuclearthinking.myheroagency.controller.systems.RenderingSystem.class).getCamera());

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

        engine.getSystem(com.nuclearthinking.myheroagency.controller.systems.RenderingSystem.class).resize(width, height);
        engine.getSystem(com.nuclearthinking.myheroagency.controller.systems.HudSystem.class).resize(width,height);
    }

}
