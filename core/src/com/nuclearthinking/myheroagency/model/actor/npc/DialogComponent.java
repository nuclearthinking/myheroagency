package com.nuclearthinking.myheroagency.model.actor.npc;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.nuclearthinking.myheroagency.model.ui.hud.HudComponent;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 24.11.2016.
 */
public class DialogComponent implements Component {

    private @Getter @Setter Dialog dialog;

    public void show(){
        dialog.show(HudComponent.getStage());
    }
}
