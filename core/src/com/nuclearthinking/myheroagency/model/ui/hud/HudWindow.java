package com.nuclearthinking.myheroagency.model.ui.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 09.12.2016.
 */
public abstract class HudWindow {

    protected @Getter Window window;

    protected @Setter float posWinX = Settings.getInstance().getPosStatX() == 0 ? Gdx.graphics.getWidth()/2 : Settings.getInstance().getPosStatX();
    protected @Setter float posWinY = Settings.getInstance().getPosStatY() == 0 ? Gdx.graphics.getHeight()/2 : Settings.getInstance().getPosStatY();

    public void buildWindow(){
        this.window = new Window(getClass().getSimpleName(), UiFactory.getSkin(), "dialog-kramola");
        this.window.setMovable(true);
        this.window.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        this.window.setPosition(posWinX,posWinY);

        this.window.setDebug(Constants.DEBUG);
    }

    public abstract void dispose();
}
