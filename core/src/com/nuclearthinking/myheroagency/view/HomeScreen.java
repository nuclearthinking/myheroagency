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

    private FontFactory fontFactory;

    public HomeScreen() {
    }

    @Override
    public void buildStage() {
        fontFactory = new FontFactory();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        getBatch().begin();
        fontFactory.getRobotoBold(14).draw(getBatch(), "Roboto Bold 14, Робото Болд", 100, 100);
        fontFactory.getRobotoBold(18).draw(getBatch(), "Roboto Bold 18, Робото Болд", 100, 150);
        fontFactory.getRobotoBold(26).draw(getBatch(), "Roboto Bold 26, Робото Болд", 100, 200);
        fontFactory.getRobotoLight(14).draw(getBatch(), "Roboto Light 14, Робото Лайт", 100, 250);
        fontFactory.getRobotoLight(18).draw(getBatch(), "Roboto Light 18, Робото Лайт", 100, 300);
        fontFactory.getRobotoLight(26).draw(getBatch(), "Roboto Light 26, Робото Лайт", 100, 350);
        getBatch().end();

        if (Gdx.input.isKeyPressed(131))
            Gdx.app.exit();
    }
}
