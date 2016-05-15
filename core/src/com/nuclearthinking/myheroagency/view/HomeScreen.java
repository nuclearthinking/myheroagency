package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.model.GameData;

/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class HomeScreen extends AbstractScreen {

    public HomeScreen() {
        gameData = new GameData();
    }

    public HomeScreen(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void buildStage() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (Gdx.input.isKeyPressed(131))
            Gdx.app.exit();
    }
}
