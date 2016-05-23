package com.nuclearthinking.myheroagency.ui.hud;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Izonami on 18.05.2016.
 *
 * rightTable,leftTable, r,l это тестовые и их нужно будет убрать
 * есть идея разделить таблицы на отдельные классы, но пока непонятно как выглядит интерфейс это избыточно.
 *
 */
public class HudGame{

    private final Stage stage;
    private final UiFactory uiFactory = new UiFactory();
    private final Table mainTable, buttomTable, rightTable, leftTable;
    private TextButton questButton, r,l;
    private Quests quest;
    private boolean show = true;

    public HudGame(Batch batch){
        stage = new Stage(new StretchViewport(Settings.getWidth(), Settings.getHeight(), new OrthographicCamera()), batch);

        quest = new Quests(uiFactory);

        mainTable = getTable();
        mainTable.setFillParent(true);

        buttomTable = getTable();
        rightTable = getTable();
        leftTable = getTable();

        initButton();

        buttomTable.add(questButton);
        rightTable.add(r);
        leftTable.add(l);

        mainTable.add(rightTable).expand().right();
        mainTable.add(buttomTable).expand().bottom();
        mainTable.add(leftTable).expand().left();

        stage.addActor(mainTable);
        stage.addActor(quest.getQuestGroup());
    }

    private void initButton() {
        questButton = uiFactory.getTextButton("Quest");
        r = uiFactory.getTextButton("Right");
        l = uiFactory.getTextButton("Left");
        questButton.addListener(new Quest());
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

    public Camera getHudCamera(){
        return stage.getCamera();
    }

    public Table getTable(){
        Table table = new Table();
        table.setDebug(Constants.DEBUG);

        return table;
    }

    private class Quest extends ClickListener{
        @Override
        public void clicked(InputEvent event, float x, float y) {
            if(show){
                quest.getQuestGroup().addAction(sequence(moveTo(-Constants.GAME_W, 0), moveTo(0, 0, .5f)));
                show = false;
            }
            else{
                quest.getQuestGroup().addAction(sequence(moveTo(0, 0), moveTo(-Constants.GAME_W, 0, .5f)));
                show = true;
            }

        }
    }

}
