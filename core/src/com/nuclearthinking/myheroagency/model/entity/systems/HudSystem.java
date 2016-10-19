package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.model.entity.Components;
import com.nuclearthinking.myheroagency.model.entity.components.hud.*;
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
        phc.getPlayerLvl().setText("Lvl: " + getEngine().getSystem(PlayerSystem.class).getLevel());
        sthc.getCon().setText(Integer.toString(getEngine().getSystem(PlayerSystem.class).getBaseCON()));
        phc.getPlayerHp().setText("Hp: " + getEngine().getSystem(PlayerSystem.class).getMaxHp());
    }

    public void resize(int w, int h){
        HudComponent.getStage().getViewport().update(w, h, true);
        UtilsHudComponent.table.setPosition(w*0.85f, h*0.95f);
        PlayerHudComponent.table.setPosition(w*.10f, h*0.90f);
        SettingHudComponent.table.setSize(w, h);
        QuestHudComponent.table.setSize(w/2, h);
        StatHudComponent.table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
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

    public void questList(){
        if (QuestHudComponent.isShowTable){
            QuestHudComponent.table.addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
            QuestHudComponent.isShowTable = false;
        }
        else{
            QuestHudComponent.table.addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
            QuestHudComponent.isShowTable = true;
        }
    }

    public void statMenu(){
        if (StatHudComponent.isShowTable){
            StatHudComponent.table.addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
            StatHudComponent.isShowTable = false;
        }
        else{
            StatHudComponent.table.addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
            StatHudComponent.isShowTable = true;
        }
    }


}
