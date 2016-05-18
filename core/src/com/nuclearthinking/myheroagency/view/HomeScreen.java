package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.GameData;
import com.nuclearthinking.myheroagency.ui.font.hud.HudGame;


/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class HomeScreen extends AbstractScreen {

    private SpriteBatch sb;
    private HudGame hudGame;
    private final Texture texture;
    private Image image;

    public HomeScreen() {
        sb = new SpriteBatch();
        gameData = new GameData();
        texture = Asset.getInstance().get("img/base.jpg", Texture.class);
    }

    public HomeScreen(GameData gameData) {
        sb = new SpriteBatch();
        this.gameData = gameData;
        texture = Asset.getInstance().get("img/base.jpg", Texture.class);
    }

    @Override
    public void buildStage() {
        hudGame = new HudGame(sb);
        multi.addProcessor(hudGame.stage);
        image = new Image(texture);
        addActor(image);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        sb.setProjectionMatrix(hudGame.stage.getCamera().combined);
        hudGame.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        hudGame.stage.getViewport().update(width, height, true);
    }
}
