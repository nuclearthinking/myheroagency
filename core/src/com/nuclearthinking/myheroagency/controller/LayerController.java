package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.nuclearthinking.myheroagency.controller.manager.AssetsManager;
import com.nuclearthinking.myheroagency.controller.manager.BuildHudManager;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import com.nuclearthinking.myheroagency.model.ui.hud.HudComponent;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.NonNull;
import lombok.val;

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
            case Input.Keys.ESCAPE:
                BuildHudManager.getInstance().buildSettings();
                break;
            case Input.Keys.J:
                BuildHudManager.getInstance().buildQuest();
                break;
            case Input.Keys.L:
                BuildHudManager.getInstance().buildStat();
                break;
            case Input.Keys.K:
                BuildHudManager.getInstance().buildSkill();
                break;
            case Input.Keys.T:
                testWindow();
                break;
        }
        return false;
    }

    //TODO: Тестирование создания окна
    private void testWindow(){
        val win = new Window("Test IWindow", UiFactory.getSkin());
        val t = AssetsManager.getInstance().get(Constants.SPLASH_IMG, Texture.class);

        val s = new Image(t);
        val s2 = new Image(t);
        val s3 = new Image(t);
        val s4 = new Image(t);

        val s5 = new Image(t);
        val s6 = new Image(t);
        val s7 = new Image(t);
        val s8 = new Image(t);
        win.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        win.left().top();
        setSkill(win, s,s2,s3,s4);
        setSkill(win, s5,s6,s7,s8);
        //win.left().top();
        //win.add(s).pad(1f);
        //win.add(s2).pad(1f);
        //win.add(s3).pad(1f);
        //win.add(s4).pad(1f);
        //win.row();
        //win.add(s5).pad(1f);
        //win.add(s6).pad(1f);
        //win.add(s7).pad(1f);
        //win.add(s8).pad(1f);
        //win.row();
        win.debug();
        HudComponent.getStage().addActor(win);
    }

    private void setSkill(@NonNull final Window window, @NonNull Actor... actor){
        for (int i = 0; i < actor.length; i++) {
            window.add(actor[i]).pad(1f);
        }
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
