package com.nuclearthinking.myheroagency.controller;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.nuclearthinking.myheroagency.controller.systems.HudSystem;
import com.nuclearthinking.myheroagency.model.components.hud.QuestHudComponent;
import com.nuclearthinking.myheroagency.model.components.hud.SettingHudComponent;
import com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent;
import lombok.NonNull;

/**
 * Created by Izonami on 30.05.2016.
 */
public final class LayerController implements InputProcessor {

    private final Engine layer;

    public LayerController(@NonNull final Engine layer){
        this.layer = layer;
    }

    private void settingsMenu(){
        if(SettingHudComponent.isShowTable){
            layer.getSystem(HudSystem.class).table(SettingHudComponent.table, SettingHudComponent.isShowTable);
            SettingHudComponent.isShowTable = false;
        }
        else{
            layer.getSystem(HudSystem.class).table(SettingHudComponent.table, SettingHudComponent.isShowTable);
            SettingHudComponent.isShowTable = true;
        }
    }

    private void questList(){
        if(QuestHudComponent.isShowTable){
            layer.getSystem(HudSystem.class).table(QuestHudComponent.table, QuestHudComponent.isShowTable);
            QuestHudComponent.isShowTable = false;
        }
        else{
            layer.getSystem(HudSystem.class).table(QuestHudComponent.table, QuestHudComponent.isShowTable);
            QuestHudComponent.isShowTable = true;
        }
    }

    private void statList(){
        if(StatHudComponent.isShowTable){
            layer.getSystem(HudSystem.class).table(StatHudComponent.table, StatHudComponent.isShowTable);
            StatHudComponent.isShowTable = false;
        }
        else{
            layer.getSystem(HudSystem.class).table(StatHudComponent.table, StatHudComponent.isShowTable);
            StatHudComponent.isShowTable = true;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.ESCAPE:settingsMenu();
                break;
            case Input.Keys.J:questList();
                break;
            case Input.Keys.L:statList();
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
