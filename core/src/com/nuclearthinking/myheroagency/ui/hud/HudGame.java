package com.nuclearthinking.myheroagency.ui.hud;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nuclearthinking.myheroagency.controller.button.QuestAddListener;
import com.nuclearthinking.myheroagency.controller.button.QuestCheck;
import com.nuclearthinking.myheroagency.controller.button.QuestListener;
import com.nuclearthinking.myheroagency.model.quest.QuestManager;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import com.nuclearthinking.myheroagency.ui.hud.layer.PlayerLayer;
import com.nuclearthinking.myheroagency.ui.hud.layer.QuestLayer;
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
    private PlayerLayer playerLayer;
    private QuestLayer questLayer;
    private SettingsLayer settings;

    public HudGame(final Batch batch){
        stage = new Stage(new ScreenViewport(new OrthographicCamera()), batch);
        uiFactory = new UiFactory();

        //Инициализация слоёв
        playerLayer = new PlayerLayer(uiFactory);
        questLayer = new QuestLayer(uiFactory); // Передаю uiFactory что бы не плодить лишние объекты
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

        // В каком порядке добавляется актер, в таком и отрисовывается
        stage.addActor(mainTable);
        stage.addActor(playerLayer.getTable());
        stage.addActor(questLayer.getTable()); // Добавляю актера из слоя. Получение через гетер, что бы не экстендить весь класс Group
        stage.addActor(settings.getTable());

    }

    private void initButton(){
        questButton = uiFactory.getTextButton("?");
        r = uiFactory.getTextButton("Right");
        r.addListener(new QuestCheck(r, QuestManager.getQuestById(1)));
        l = uiFactory.getTextButton("Left");
        l.addListener(new QuestAddListener(l, questLayer));
        questButton.addListener(new QuestListener(questButton, questLayer));
    }

    private Table getTable(){
        final Table table = new Table();

        table.setDebug(Constants.DEBUG);

        return table;
    }

    public void renderHud(final float delta) {
        stage.draw();
        stage.act(delta);
    }

    public void resizeHud(final int width, final int height){
        stage.getViewport().update(width, height);
        playerLayer.resize(width,height);
        questLayer.resize(width,height);
        settings.resize(width,height);
    }

    public Stage getHudStage(){
        return stage;
    }

    public Camera getHudCamera(){
        return stage.getCamera();
    }

    public SettingsLayer getSettings(){
        return settings;
    }

}
