package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nuclearthinking.myheroagency.controller.LayerController;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.controller.PlayerController;
import com.nuclearthinking.myheroagency.controller.SpriteManager;
import com.nuclearthinking.myheroagency.model.GameData;
import com.nuclearthinking.myheroagency.model.GameObject;
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
    private PlayerController playerController;
    private ObjectManager object;

    public HomeScreen() {
        this(new GameData());
    }

    public HomeScreen(final GameData gameData) {
        this.gameData = gameData;

        //TODO: Думаю стоит для этого сделать какую нибудь фабрику, что бы каждый раз не создавать
        /*final TextureAtlas playerAtlas = Asset.getInstance().get("player/player.pack");
        Animation idle, left,right;
        idle = new Animation(1 / 2f, playerAtlas.findRegions("still"), Animation.PlayMode.LOOP);
        left = new Animation(1 / 6f, playerAtlas.findRegions("left"), Animation.PlayMode.LOOP);
        right = new Animation(1 / 6f, playerAtlas.findRegions("right"), Animation.PlayMode.LOOP);
        player = new Player(null, 40, 35, idle,left,right);*/

        object = new ObjectManager();
    }

    @Override
    public void buildStage() {
        hudGame = new HudGame(stage.getBatch());
        manager = new MapManager();
        layerController = new LayerController(hudGame);
        playerController = new PlayerController(object.getPlayer());
        object.getPlayer().setPosition(1000,3000);
        SpriteManager.addGameObject(object.getPlayer());

        multi.addProcessor(hudGame.getHudStage());
        multi.addProcessor(playerController);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        manager.getRenderer().setView((OrthographicCamera) stage.getCamera());
        stage.getBatch().setProjectionMatrix(hudGame.getHudCamera().combined);

        stage.getCamera().position.set(object.getPlayer().getX(), object.getPlayer().getY(), 0);
        layerController.update();
        playerController.update();

        manager.getRenderer().render();
        manager.getBatch().begin();
        for(final GameObject object : SpriteManager.getAllObjects()){
            object.draw(manager.getRenderer().getBatch());
        }
        manager.getBatch().end();

        hudGame.renderHud(delta);

        //TODO: Это тестовый код контроллера, нужно приучить себя выносить все контроллеры отдельно
        /*if(Gdx.input.isKeyJustPressed(19))
            ((OrthographicCamera) stage.getCamera()).translate(0,100);
        else if(Gdx.input.isKeyJustPressed(20))
            ((OrthographicCamera) stage.getCamera()).translate(0,-100);
        else if(Gdx.input.isKeyJustPressed(21))
            ((OrthographicCamera) stage.getCamera()).translate(-100,0);
        else if(Gdx.input.isKeyJustPressed(22))
            ((OrthographicCamera) stage.getCamera()).translate(100,0);*/
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        hudGame.resizeHud(width, height);
    }

}
