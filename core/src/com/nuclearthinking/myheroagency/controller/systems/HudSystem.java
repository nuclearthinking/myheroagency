package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.model.Components;
import com.nuclearthinking.myheroagency.model.components.hud.*;
import lombok.val;

/**
 * Created by mkuksin on 03.10.2016.
 */
public final class HudSystem extends IteratingSystem {

    private static final Family family = Family.all(HudComponent.class,
                                                    UtilsHudComponent.class,
                                                    PlayerHudComponent.class,
                                                    SettingHudComponent.class,
                                                    QuestHudComponent.class,
                                                    StatHudComponent.class).get();

    public HudSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val hud = Components.HUD.get(entity);
        val uhc = Components.UHC.get(entity);
        val phc = Components.PHC.get(entity);
        val sthc = Components.SHC.get(entity);

        hud.getStage().draw();
        hud.getStage().act(deltaTime);

        uhc.getFps().setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        phc.getPlayerLvl().setText("Lvl: " /*+ player.getLvl()*/);
        //sthc.getCon().setText(Integer.toString(player.getCON()));
        //phc.getPlayerHp().setText("Hp: " + player.getMaxHp());
    }

    public void resize(int w, int h){
        HudComponent.getStage().getViewport().update(w, h, true);

        UtilsHudComponent.table.setPosition(w*0.85f, h*0.95f);
        PlayerHudComponent.table.setPosition(w*.10f, h*0.90f);

        SettingHudComponent.table.setPosition(-w,h);
        QuestHudComponent.table.setPosition(-w,h);
        StatHudComponent.table.setPosition(-w,h);

        SettingHudComponent.table.setSize(w, h);
        QuestHudComponent.table.setSize(w/2, h);
        StatHudComponent.table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
    }
}
