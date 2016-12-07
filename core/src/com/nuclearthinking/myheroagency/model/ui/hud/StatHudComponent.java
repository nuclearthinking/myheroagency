package com.nuclearthinking.myheroagency.model.ui.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.nuclearthinking.myheroagency.controller.listener.button.player.AddStatsListener;
import com.nuclearthinking.myheroagency.controller.listener.button.player.RemoveStatsListener;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 10.10.2016.
 */
public final class StatHudComponent implements Component {

    private @Getter Window window;

    private @Getter @Setter TextButton plus;
    private @Getter @Setter TextButton minus;
    private @Getter @Setter Label con;
    private @Getter @Setter AddStatsListener addStatsListener;
    private @Getter @Setter RemoveStatsListener removeStatsListener;
    //TODO:Положить в настройки(Settings.class)
    private @Setter float posWinX = Settings.getInstance().getPosStatX() == 0 ? Gdx.graphics.getWidth()/2 : Settings.getInstance().getPosStatX();
    private @Setter float posWinY = Settings.getInstance().getPosStatY() == 0 ? Gdx.graphics.getHeight()/2 : Settings.getInstance().getPosStatY();

    public void buildWindow() {
        this.window = new Window("Характеристики", UiFactory.getSkin(), "dialog-kramola");
        this.window.setMovable(true);
        this.window.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        this.window.setPosition(posWinX,posWinY);

        this.window.setDebug(Constants.DEBUG);
    }

    public void undo() {
        plus.getListeners().removeValue(addStatsListener, true);
        plus = null;
        minus.getListeners().removeValue(removeStatsListener, true);
        minus = null;
        con = null;
        window = null;
    }
}
