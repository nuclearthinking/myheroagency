package com.nuclearthinking.myheroagency.ui.hud;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import com.nuclearthinking.myheroagency.ui.hud.layer.Quest;
import com.nuclearthinking.myheroagency.ui.hud.layer.SettingsLayer;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 18.05.2016.
 *
 * rightTable,leftTable, r,l это тестовые и их нужно будет убрать
 * есть идея разделить таблицы на отдельные классы, но пока непонятно как выглядит интерфейс это избыточно.
 *
 */
public class HudGame{

    private final Stage stage;
    private final Table mainTable, buttomTable, rightTable, leftTable;
    private TextButton questButton, r,l;
    private UiFactory uiFactory;
    private Quest quest;
    private SettingsLayer settings;

    public HudGame(final Batch batch){
        stage = new Stage(new ScreenViewport(new OrthographicCamera()), batch);
        uiFactory = new UiFactory();

        //Инициализация слоёв
        quest = new Quest(uiFactory); // Передаю uiFactory что бы не плодить лишние объекты
        settings = new SettingsLayer(uiFactory);

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
        stage.addActor(quest.getTable()); // Добавляю актера из слоя. Получение через гетер, что бы не экстендить весь класс Group
        stage.addActor(settings.getTable());
    }

    private void initButton(){
        questButton = uiFactory.getTextButton("?");
        r = uiFactory.getTextButton("Right");
        l = uiFactory.getTextButton("Left");
        l.addListener(new AddQuest());
        questButton.addListener(new QuestListener());
    }

    private Table getTable(){
        Table table = new Table();
        table.setDebug(Constants.DEBUG);

        return table;
    }

    public void renderHud(float delta) {
        stage.draw();
        stage.act(delta);
    }

    public void resizeHud(int width, int height){
        stage.getViewport().update(width, height);
        settings.resize(width,height);
        quest.resize(width,height);
    }

    public Stage getHudStage(){
        return stage;
    }

    public Camera getHudCamera(){
        return stage.getCamera();
    }

    private class QuestListener extends ClickListener {

        @Override
        public void clicked(InputEvent event, float x, float y) {
            if(!quest.isShowTable())
                quest.setTableVisible(true);
            else
                quest.setTableVisible(false);
        }
    }

    //TODO: Полностью является кодом для проверки
    private class AddQuest extends ClickListener{
        @Override
        public void clicked(InputEvent event, float x, float y) {
            for (int i = 0; i < 5; i++) {
                quest.addQuestToList("New Quest" + i);
            }
        }
    }

    public SettingsLayer getSettings(){
        return settings;
    }

}
