package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;

/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class HomeScreen extends AbstractScreen {
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
