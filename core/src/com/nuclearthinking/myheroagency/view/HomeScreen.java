package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.GameData;
import com.nuclearthinking.myheroagency.ui.hud.HudGame;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


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
    int countClick = 0;

    public HomeScreen() {
        gameData = new GameData();
        texture = Asset.getInstance().get("img/base.jpg", Texture.class);
    }

    public HomeScreen(GameData gameData) {
        this.gameData = gameData;
        texture = Asset.getInstance().get("img/base.jpg", Texture.class);
    }

    @Override
    public void buildStage() {
        hudGame = new HudGame(getBatch());

        multi.addProcessor(hudGame.getHudStage());
        image = new Image(texture);
        addActor(image);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        getBatch().setProjectionMatrix(hudGame.getHudCamera().combined);
        hudGame.renderHud(delta);

        if(Gdx.input.isTouched())
        {
            countClick++;
            if(countClick == 1)
                hudGame.getMainTable().addAction(sequence(moveTo(getWidth(), 0), moveTo(0, 0, .5f)));

            hudGame.getHudElement("Quest").addAction(sequence(moveTo(10,10), moveTo(0,0,.1f)));

        }

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        hudGame.resizeHud(width, height);
    }
}
