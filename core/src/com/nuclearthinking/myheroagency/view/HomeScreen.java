package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nuclearthinking.myheroagency.controller.LayerController;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.controller.PlayerController;
import com.nuclearthinking.myheroagency.controller.SpriteManager;
import com.nuclearthinking.myheroagency.model.GameData;
import com.nuclearthinking.myheroagency.model.actor.GameObject;
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

        object = new ObjectManager();
        hudGame = new HudGame(stage.getBatch()); //Инициализируем худ
        manager = new MapManager(); // Создаем карту
        layerController = new LayerController(hudGame); // Добоавляем слои
        playerController = new PlayerController(object.getPlayer()); // Создаем контроллер для игрока
    }

    @Override
    public void buildStage() {
        object.getPlayer().setPosition(1000,3000); // Устанавливаем начальную позицию для игрока
        SpriteManager.addGameObject(object.getPlayer()); // Добавляем игрока в менеджер спрайтов

        // Мультиконтроллер. Все новые контроллеры добавлять чере addProcessor
        multi.addProcessor(hudGame.getHudStage());
        multi.addProcessor(playerController);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        manager.getRenderer().setView((OrthographicCamera) stage.getCamera()); // Рендерим карту
        stage.getCamera().position.set(object.getPlayer().getX(), object.getPlayer().getY(), 0); // Привязываем позицию камеры к персонажу
        stage.getBatch().setProjectionMatrix(hudGame.getHudCamera().combined); // Накладываем худ

        // Обновляем контроллеры
        layerController.update();
        playerController.update();

        manager.getRenderer().render();
        manager.getBatch().begin();
        for(final GameObject object : SpriteManager.getAllObjects()) {
            object.draw(manager.getRenderer().getBatch());
        }
        manager.getBatch().end();

        hudGame.renderHud(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        hudGame.resizeHud(width, height);
    }

}
