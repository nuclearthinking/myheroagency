package com.nuclearthinking.myheroagency.model.ui.hud;

import com.badlogic.ashley.core.Component;
import com.nuclearthinking.myheroagency.model.Settings;

/**
 * Created by mkuksin on 01.12.2016.
 */
public class SkillHudComponent extends HudWindow implements Component {

    @Override
    public void dispose(){
        Settings.getInstance().setPosStatX(window.getX());
        Settings.getInstance().setPosStatY(window.getY());
        posWinX = window.getX();
        posWinY = window.getY();
        HudComponent.getStage().getActors().removeValue(window, true);
        window = null;
    }
}
