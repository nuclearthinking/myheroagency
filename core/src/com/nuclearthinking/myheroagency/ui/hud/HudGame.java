package com.nuclearthinking.myheroagency.ui.hud;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Izonami on 18.05.2016.
 */
public class HudGame{

    private final static Map<String, Actor> hudElements = new HashMap<String, Actor>();
    private final Stage stage;
    private final Table mainTable, buttomTable, rightTable;
    private TextButton questButton, testButton, testButton2;

    public HudGame(Batch batch){
        stage = new Stage(new StretchViewport(Settings.getWidth(), Settings.getHeight(), new OrthographicCamera()), batch);

        mainTable = new Table();
        buttomTable = new Table();
        rightTable = new Table();

        mainTable.setFillParent(true);
        mainTable.setDebug(Constants.DEBUG);
        mainTable.setPosition(Settings.getWidth(), Settings.getHeight());

        initButton();

        mainTable.add(buttomTable).expand().bottom();
        mainTable.add(rightTable).expand().right();

        stage.addActor(mainTable);
    }

    private void initButton(){
        questButton = new TextButton("Quest", Asset.getInstance().getSkin(), "kramola");
        testButton = new TextButton("Test", Asset.getInstance().getSkin(), "kramola");
        testButton2 = new TextButton("Test2", Asset.getInstance().getSkin(), "kramola");
        buttomTable.add(questButton).pad(10);
        buttomTable.add(testButton2);
        rightTable.add(testButton);
        addHudElement(questButton.getText().toString(), questButton);
        addHudElement(testButton.getText().toString(), testButton);
        addHudElement(testButton2.getText().toString(), testButton2);
    }

    public void renderHud(float delta) {
        stage.draw();
        stage.act(delta);
    }

    public void resizeHud(int width, int height){
        stage.getViewport().update(width, height, true);
    }

    public Stage getHudStage(){
        return stage;
    }

    public Table getMainTable(){
        return mainTable;
    }

    public Camera getHudCamera(){
        return stage.getCamera();
    }


    public void addHudElement(String name, Actor element){
        hudElements.put(name, element);
    }

    public Actor getHudElement(String name){
        return hudElements.get(name);
    }

    public Map<String, Actor> getHudElements(){
        return hudElements;
    }
}
