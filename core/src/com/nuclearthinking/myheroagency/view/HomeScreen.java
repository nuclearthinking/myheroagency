package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.GameData;
import com.nuclearthinking.myheroagency.ui.hud.HudGame;


/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class HomeScreen extends AbstractScreen {

    private HudGame hudGame;
    private final Texture texture;
    private Image image;

    public HomeScreen() {
        this(new GameData());
    }

    public HomeScreen(GameData gameData) {
        this.gameData = gameData;
        texture = Asset.getInstance().get("img/base.jpg", Texture.class);
    }

    @Override
    public void buildStage() {
        hudGame = new HudGame(stage.getBatch());

        multi.addProcessor(hudGame.getHudStage());
        image = new Image(texture);
        stage.addActor(image);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        stage.getBatch().setProjectionMatrix(hudGame.getHudCamera().combined);
        hudGame.renderHud(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        hudGame.resizeHud(width, height);
    }
}
