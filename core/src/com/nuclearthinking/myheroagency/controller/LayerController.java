package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nuclearthinking.myheroagency.model.components.hud.QuestHudComponent;
import com.nuclearthinking.myheroagency.model.components.hud.SettingHudComponent;
import com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent;
import lombok.NonNull;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Izonami on 30.05.2016.
 */
public final class LayerController implements InputProcessor {

    public LayerController(){}

    public void showTable(@NonNull final Table table){
        if(table.getX() <= -Gdx.graphics.getWidth()) table.addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
        else if(table.getX() == 0) table.addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.ESCAPE:showTable(SettingHudComponent.table);
                break;
            case Input.Keys.J:showTable(QuestHudComponent.table);
                break;
            case Input.Keys.L:showTable(StatHudComponent.table);
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
