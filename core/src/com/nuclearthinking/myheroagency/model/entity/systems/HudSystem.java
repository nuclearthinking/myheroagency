package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.model.entity.Components;
import com.nuclearthinking.myheroagency.model.entity.components.PlayerComponent;
import com.nuclearthinking.myheroagency.model.entity.components.hud.HudComponent;
import com.nuclearthinking.myheroagency.model.entity.components.hud.PlayerHudComponent;
import com.nuclearthinking.myheroagency.model.entity.components.hud.SettingHudComponent;
import com.nuclearthinking.myheroagency.model.entity.components.hud.UtilsHudComponent;
import lombok.val;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by mkuksin on 03.10.2016.
 */
public class HudSystem extends IteratingSystem {

    private static final Family family = Family.all(HudComponent.class,
                                                    UtilsHudComponent.class,
                                                    PlayerHudComponent.class,
                                                    PlayerComponent.class,
                                                    SettingHudComponent.class).get();

    public HudSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val hud = Components.HUD.get(entity);
        val uhc = Components.UHC.get(entity);
        val phc = Components.PHC.get(entity);
        val player = Components.PLAYER.get(entity);

        hud.getStage().draw();
        hud.getStage().act(deltaTime);

        uhc.getFps().setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        phc.getPlayerLvl().setText("Lvl: " + player.getLevel());
        phc.getPlayerHp().setText("Hp: " + getEngine().getSystem(PlayerSystem.class).getMaxHp());
    }

    public void resize(int w, int h){
        HudComponent.getStage().getViewport().update(w, h, true);
        UtilsHudComponent.table.setPosition(w*0.85f, h*0.95f);
        PlayerHudComponent.table.setPosition(w*.10f, h*0.90f);
        SettingHudComponent.table.setSize(w, h);
    }

    public void tableVisible(){
        if (SettingHudComponent.isShowTable){
            SettingHudComponent.table.addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
            SettingHudComponent.isShowTable = false;
        }
        else{
            SettingHudComponent.table.addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
            SettingHudComponent.isShowTable = true;
        }

    }
}
