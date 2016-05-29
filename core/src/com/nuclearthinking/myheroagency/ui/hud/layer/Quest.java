package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.ui.UiFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Izonami on 23.05.2016.
 */
public class Quest {

    //LinkedHashMap используется для того что бы отображать в порядке добавления
    private static final Map<String, Actor> questsList = new LinkedHashMap<String, Actor>();
    private final Table questTable;
    private final UiFactory factory;
    private Image i;

    public Quest(final UiFactory factory){
        this.factory = factory;

        questTable = new Table();
        questTable.setSize(Settings.getWidth() - 500, Settings.getHeight());
        questTable.setDebug(true);
        questTable.setPosition(-Settings.getWidth(), 0);

        Texture t = Asset.getInstance().get("img/testQuestLayer.jpg", Texture.class);
        i = new Image(t);
        i.setSize(questTable.getWidth(), questTable.getHeight());
        i.setColor(Color.FOREST);

        addQuestToList("Test");
        addQuestToList("Test2");

        questTable.addActor(i);

        for(Map.Entry<String, Actor> a : questsList.entrySet()){
            questTable.add(a.getValue()).pad(10);
            questTable.row();
        }
    }

    /**
     * Служит для добавления нового задания в карту и перерисовку таблицы
     * @param name - является ключом и названием кнопки
     */
    public void addQuestToList(String name){
        Label quest = factory.getLabel(name);
        quest.setColor(Color.BLACK);
        questsList.put(name, quest);

        //TODO: Пока не придумал ничего лучше кроме как сбрасывать и добавлять по новой
        questTable.reset();
        questTable.addActor(i);
        for(Map.Entry<String, Actor> a : questsList.entrySet()){
            questTable.add(a.getValue()).pad(10);
            questTable.row();
        }
    }

    public Table getQuestTable(){
        return questTable;
    }

}
