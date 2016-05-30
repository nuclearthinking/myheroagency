package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.ui.UiFactory;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Izonami on 23.05.2016.
 */
public class Quest extends AbstractLayer {

    //LinkedHashMap используется для того что бы отображать в порядке добавления
    private static final Map<String, Actor> questsList = new LinkedHashMap<String, Actor>();
    private Image i;
    private boolean isShowTable = false;

    public Quest(final UiFactory factory){
        super(factory);

        getTable().setSize(Settings.getWidth() - 500, Settings.getHeight());

        Texture t = Asset.getInstance().get("img/testQuestLayer.jpg", Texture.class);
        i = new Image(t);
        i.setSize(getTable().getWidth(), getTable().getHeight());
        i.setColor(Color.FOREST);

        addQuestToList("Test");
        addQuestToList("Test2");

        getTable().addActor(i);

        for(Map.Entry<String, Actor> a : questsList.entrySet()){
            getTable().add(a.getValue()).pad(10);
            getTable().row();
        }
    }

    public void setTableVisible(boolean isShowTable){
        this.isShowTable = isShowTable;

        if (isShowTable)
            getTable().addAction(sequence(moveTo(-Settings.getWidth(), 0), moveTo(0, 0, .5f)));
        else
            getTable().addAction(sequence(moveTo(0, 0), moveTo(-Settings.getWidth(), 0, .5f)));
    }

    public boolean isShowTable(){
        return isShowTable;
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
        getTable().reset();
        getTable().addActor(i);
        for(Map.Entry<String, Actor> a : questsList.entrySet()){
            getTable().add(a.getValue()).pad(10);
            getTable().row();
        }
    }

}
