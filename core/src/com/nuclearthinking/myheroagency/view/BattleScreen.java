package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.ui.font.FontFactory;

/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class BattleScreen extends AbstractScreen {

    private static FontFactory fontFactory;

    public BattleScreen() {
        fontFactory = new FontFactory();
    }

    @Override
    public void buildStage() {

    }

    @Override
    public void render(float delta){
        super.render(delta);

        stage.getBatch().begin();
        fontFactory.getRobotoLight(18).draw(stage.getBatch(), "HELLO !", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        stage.getBatch().end();
    }
}
