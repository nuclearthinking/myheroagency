package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.ui.font.FontFactory;


/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class HomeScreen extends AbstractScreen {

    FontFactory fontFactory;

    @Override
    public void buildStage() {
        fontFactory = new FontFactory();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        getBatch().begin();
        fontFactory.getRobotoBold14().draw(getBatch(), "Roboto Bold 10, Робото Болд", 100, 100);
        fontFactory.getRobotoBold18().draw(getBatch(), "Roboto Bold 18, Робото Болд", 100, 150);
        fontFactory.getRobotoBold26().draw(getBatch(), "Roboto Bold 26, Робото Болд", 100, 200);
        fontFactory.getRobotoLight14().draw(getBatch(), "Roboto Light 10, Робото Лайт", 100, 250);
        fontFactory.getRobotoLight18().draw(getBatch(), "Roboto Light 18, Робото Лайт", 100, 300);
        fontFactory.getRobotoLight26().draw(getBatch(), "Roboto Light 26, Робото Лайт", 100, 350);
        getBatch().end();

        if (Gdx.input.isKeyPressed(131))
            Gdx.app.exit();
    }
}
