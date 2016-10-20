package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.model.ui.FontFactory;

/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class BattleScreen extends AbstractScreen {

    private static FontFactory fontFactory;
    private String name;

    public BattleScreen() {
        fontFactory = new FontFactory();
    }

    @Override
    public void buildStage() {
        name = "This is " + getClass().getSimpleName();
    }

    @Override
    public void render(float delta){
        super.render(delta);

        stage.getBatch().begin();
        fontFactory.getRobotoLight(18).draw(stage.getBatch(),
                name,
                Gdx.graphics.getWidth() / name.length(),
                Gdx.graphics.getHeight() / 2);
        stage.getBatch().end();
    }
}
