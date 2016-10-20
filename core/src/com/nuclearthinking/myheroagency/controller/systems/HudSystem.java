package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.model.Components;
import lombok.val;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by mkuksin on 03.10.2016.
 */
public class HudSystem extends IteratingSystem {

    private static final Family family = Family.all(com.nuclearthinking.myheroagency.model.components.hud.HudComponent.class,
                                                    com.nuclearthinking.myheroagency.model.components.hud.UtilsHudComponent.class,
                                                    com.nuclearthinking.myheroagency.model.components.hud.PlayerHudComponent.class,
                                                    com.nuclearthinking.myheroagency.model.components.hud.SettingHudComponent.class,
                                                    com.nuclearthinking.myheroagency.model.components.hud.QuestHudComponent.class,
                                                    com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent.class).get();

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
        com.nuclearthinking.myheroagency.model.components.hud.HudComponent.getStage().getViewport().update(w, h, true);
        com.nuclearthinking.myheroagency.model.components.hud.UtilsHudComponent.table.setPosition(w*0.85f, h*0.95f);
        com.nuclearthinking.myheroagency.model.components.hud.PlayerHudComponent.table.setPosition(w*.10f, h*0.90f);
        com.nuclearthinking.myheroagency.model.components.hud.SettingHudComponent.table.setSize(w, h);
        com.nuclearthinking.myheroagency.model.components.hud.QuestHudComponent.table.setSize(w/2, h);
        com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent.table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
    }

    public void tableVisible(){
        if (com.nuclearthinking.myheroagency.model.components.hud.SettingHudComponent.isShowTable){
            com.nuclearthinking.myheroagency.model.components.hud.SettingHudComponent.table.addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
            com.nuclearthinking.myheroagency.model.components.hud.SettingHudComponent.isShowTable = false;
        }
        else{
            com.nuclearthinking.myheroagency.model.components.hud.SettingHudComponent.table.addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
            com.nuclearthinking.myheroagency.model.components.hud.SettingHudComponent.isShowTable = true;
        }
    }

    public void questList(){
        if (com.nuclearthinking.myheroagency.model.components.hud.QuestHudComponent.isShowTable){
            com.nuclearthinking.myheroagency.model.components.hud.QuestHudComponent.table.addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
            com.nuclearthinking.myheroagency.model.components.hud.QuestHudComponent.isShowTable = false;
        }
        else{
            com.nuclearthinking.myheroagency.model.components.hud.QuestHudComponent.table.addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
            com.nuclearthinking.myheroagency.model.components.hud.QuestHudComponent.isShowTable = true;
        }
    }

    public void statMenu(){
        if (com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent.isShowTable){
            com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent.table.addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
            com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent.isShowTable = false;
        }
        else{
            com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent.table.addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
            com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent.isShowTable = true;
        }
    }


}
