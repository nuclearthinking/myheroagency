package com.nuclearthinking.myheroagency.model.actor.npc;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nuclearthinking.myheroagency.controller.manager.GameWorldManager;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 24.11.2016.
 */
public class NameComponent implements Component {

    private @Getter @Setter Label label;

    public void show(){
        GameWorldManager.getStage().addActor(label);
    }
}
