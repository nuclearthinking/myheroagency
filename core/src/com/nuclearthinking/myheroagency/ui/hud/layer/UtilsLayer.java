package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nuclearthinking.myheroagency.ui.UiFactory;

/**
 * Created by Izonami on 23.07.2016.
 * Должен содержать в себе отладочную информацию
 */
public class UtilsLayer extends AbstractLayer {

    private Label fps;

    public UtilsLayer(final UiFactory factory) {
        super(factory);

        fps = factory.getLabel("fps");
    }

    @Override
    public void buildLayer() {
        fps.setText("FPS: " + Integer.toString(Gdx.graphics.getFramesPerSecond()));
        table.setPosition(Gdx.graphics.getWidth() - 60, Gdx.graphics.getHeight() - 30);

        table.add(fps);
    }

    public void update(){
        fps.setText("FPS: " + Integer.toString(Gdx.graphics.getFramesPerSecond()));
    }

    @Override
    public void resize(final int w, final int h){
        super.resize(w,h);

        table.setPosition(Gdx.graphics.getWidth() - 60, Gdx.graphics.getHeight() - 30);
    }

}
