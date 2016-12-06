package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.model.Components;
import com.nuclearthinking.myheroagency.model.ui.hud.HudComponent;
import com.nuclearthinking.myheroagency.model.ui.hud.PlayerHudComponent;
import com.nuclearthinking.myheroagency.model.ui.hud.UtilsHudComponent;
import lombok.val;

/**
 * Created by mkuksin on 03.10.2016.
 */
public final class HudSystem extends IteratingSystem {

    private static final Family family = Family.all(HudComponent.class,
                                                    UtilsHudComponent.class,
                                                    PlayerHudComponent.class).get();

    public HudSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val hud = Components.HUD.get(entity);
        val uhc = Components.UHC.get(entity);
        val phc = Components.PHC.get(entity);
        //val sthc = Components.SHC.get(entity);
        val player = Components.PLAYER.get(hud.getActor());

        hud.getStage().draw();
        hud.getStage().act(deltaTime);

        uhc.getFps().setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        phc.getPlayerLvl().setText("Lvl: " + player.getLvl());
        //sthc.getCon().setText(Integer.toString(player.getCON()));
        phc.getPlayerHp().setText("Hp: " + player.getMaxHp());
    }
}
