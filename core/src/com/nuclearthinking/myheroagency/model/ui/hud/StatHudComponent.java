package com.nuclearthinking.myheroagency.model.ui.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.listener.button.player.AddStatsListener;
import com.nuclearthinking.myheroagency.controller.listener.button.player.RemoveStatsListener;
import com.nuclearthinking.myheroagency.model.Settings;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 10.10.2016.
 */
public final class StatHudComponent extends HudWindow implements Component {

    private @Getter @Setter TextButton plus;
    private @Getter @Setter TextButton minus;
    private @Getter @Setter Label con;
    private @Getter @Setter AddStatsListener addStatsListener;
    private @Getter @Setter RemoveStatsListener removeStatsListener;

    @Override
    public void dispose() {
        Settings.getInstance().setPosStatX(window.getX());
        Settings.getInstance().setPosStatY(window.getY());
        posWinX = window.getX();
        posWinY = window.getY();
        HudComponent.getStage().getActors().removeValue(window, true);
        plus.getListeners().removeValue(addStatsListener, true);
        plus = null;
        minus.getListeners().removeValue(removeStatsListener, true);
        minus = null;
        con = null;
        window = null;
    }
}
