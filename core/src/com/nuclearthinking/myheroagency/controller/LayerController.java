package com.nuclearthinking.myheroagency.controller;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.nuclearthinking.myheroagency.model.entity.components.hud.SettingHudComponent;
import com.nuclearthinking.myheroagency.model.entity.systems.HudSystem;
import lombok.NonNull;

/**
 * Created by Izonami on 30.05.2016.
 */
public class LayerController {

    private final Engine layer;

    public LayerController(@NonNull final Engine layer){
        this.layer = layer;
    }

    public void update(){
        settingsUpdate();
        //questLayer();
        //playerStatsLayer();
    }

    private void settingsUpdate(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && SettingHudComponent.isShowTable){
            layer.getSystem(HudSystem.class).tableVisible();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && !SettingHudComponent.isShowTable){
            layer.getSystem(HudSystem.class).tableVisible();
        }
    }

    /*private void questLayer(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.J) && !layer.getQuestLayer().isShowTable()){
            layer.getQuestLayer().setTableVisible(true);
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.J) && layer.getQuestLayer().isShowTable()){
            layer.getQuestLayer().setTableVisible(false);
        }
    }

    private void playerStatsLayer(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.L) && !layer.getPlayerStatLayer().isShowTable()){
            layer.getPlayerStatLayer().setTableVisible(true);
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.L) && layer.getPlayerStatLayer().isShowTable()){
            layer.getPlayerStatLayer().setTableVisible(false);
        }
    }*/
}
