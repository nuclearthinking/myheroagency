package com.nuclearthinking.myheroagency.ui.hud;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 18.05.2016.
 */
public class HudGame{

    private final Stage stage;
    private final Table mainTable, buttomTable, rightTable;
    private TextButton questButton, testButton, testButton2;
    private UiFactory uiFactory;

    public HudGame(Batch batch){
        stage = new Stage(new StretchViewport(Settings.getWidth(), Settings.getHeight(), new OrthographicCamera()), batch);
        uiFactory = new UiFactory();

        mainTable = new Table();
        buttomTable = new Table();
        rightTable = new Table();

        mainTable.setFillParent(true);
        mainTable.setDebug(Constants.DEBUG);
        mainTable.setPosition(Settings.getWidth(), Settings.getHeight());

        initButton();

        buttomTable.add(questButton).pad(10);
        buttomTable.add(testButton2);

        rightTable.add(testButton);

        mainTable.add(buttomTable).expand().bottom();
        mainTable.add(rightTable).expand().right();

        stage.addActor(mainTable);
    }

    private void initButton(){
        questButton = uiFactory.getTextButton("Quest");
        testButton = uiFactory.getTextButton("Test");
        testButton2 = uiFactory.getTextButton("Test2");
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

    public UiFactory getUiFactory(){
        return uiFactory;
    }
}
