package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.nuclearthinking.myheroagency.ui.hud.HudGame;
import lombok.NonNull;

/**
 * Created by Izonami on 30.05.2016.
 */
public class LayerController {

    private final HudGame layer;

    public LayerController(@NonNull final HudGame layer){
        this.layer = layer;
    }

    public void update(){
        settingsUpdate();
        questLayer();
        playerStatsLayer();
    }

    private void settingsUpdate(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && !layer.getSettings().isShowTable()){
            layer.getSettings().setTableVisible(true);
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && layer.getSettings().isShowTable()){
            layer.getSettings().setTableVisible(false);
        }
    }

    private void questLayer(){
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
    }
}
