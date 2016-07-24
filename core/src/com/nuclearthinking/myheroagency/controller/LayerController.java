package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.Gdx;
import com.nuclearthinking.myheroagency.ui.hud.HudGame;

/**
 * Created by Izonami on 30.05.2016.
 */
public class LayerController {

    private final HudGame layer;

    public LayerController(final HudGame layer){
        this.layer = layer;
    }

    public void update(){
        settingsUpdate();
        questLayer();
    }

    private void settingsUpdate(){
        if(Gdx.input.isKeyJustPressed(131) && !layer.getSettings().isShowTable()){
            layer.getSettings().setTableVisible(true);
        }
        else if (Gdx.input.isKeyJustPressed(131) && layer.getSettings().isShowTable()){
            layer.getSettings().setTableVisible(false);
        }
    }

    private void questLayer(){
        if(Gdx.input.isKeyJustPressed(38) && !layer.getQuestLayer().isShowTable()){
            layer.getQuestLayer().setTableVisible(true);
        }
        else if (Gdx.input.isKeyJustPressed(38) && layer.getQuestLayer().isShowTable()){
            layer.getQuestLayer().setTableVisible(false);
        }
    }
}
