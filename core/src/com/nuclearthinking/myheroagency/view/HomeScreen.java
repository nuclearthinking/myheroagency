package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nuclearthinking.myheroagency.controller.LayerController;
import com.nuclearthinking.myheroagency.model.GameData;
import com.nuclearthinking.myheroagency.model.MapManager;
import com.nuclearthinking.myheroagency.ui.hud.HudGame;


/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class HomeScreen extends AbstractScreen {

    private HudGame hudGame;
    private MapManager manager;
    private LayerController layerController;

    public HomeScreen() {
        this(new GameData());
    }

    public HomeScreen(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void buildStage() {
        hudGame = new HudGame(stage.getBatch());
        manager = new MapManager();
        layerController = new LayerController(hudGame);

        multi.addProcessor(hudGame.getHudStage());
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        manager.getRenderer().setView((OrthographicCamera) stage.getCamera());
        stage.getBatch().setProjectionMatrix(hudGame.getHudCamera().combined);

        layerController.update();
        manager.getRenderer().render();
        hudGame.renderHud(delta);

        //TODO: Это тестовый код контроллера, нужно приучить себя выносить все контроллеры отдельно
        if(Gdx.input.isKeyJustPressed(19))
            ((OrthographicCamera) stage.getCamera()).translate(0,100);
        else if(Gdx.input.isKeyJustPressed(20))
            ((OrthographicCamera) stage.getCamera()).translate(0,-100);
        else if(Gdx.input.isKeyJustPressed(21))
            ((OrthographicCamera) stage.getCamera()).translate(-100,0);
        else if(Gdx.input.isKeyJustPressed(22))
            ((OrthographicCamera) stage.getCamera()).translate(100,0);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        hudGame.resizeHud(width, height);
    }

}
