package com.nuclearthinking.myheroagency.model.ui.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nuclearthinking.myheroagency.controller.listener.button.menu.QuestTestListener;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import lombok.NonNull;
import lombok.val;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by mkuksin on 10.10.2016.
 */
public final class QuestHudComponent implements Component {

    public static final Map<String, Actor> QUEST_LIST = new LinkedHashMap<String, Actor>();

    private Table table;

    public Table getTable(){
        if(table == null){
            table = new Table(UiFactory.getSkin());
        }
        return table;
    }

    public void undo(){
        table = null;
    }

    public void addQuestToList(final String name, @NonNull final UiFactory factory){
        val quest = factory.getTextButton(name);

        quest.addListener(new QuestTestListener(quest));
        quest.setColor(Color.BLACK);
        QUEST_LIST.put(name, quest);

        //TODO: Пока не придумал ничего лучше кроме как сбрасывать и добавлять по новой
        table.reset();
        for(val a : QUEST_LIST.entrySet()){
            table.add(a.getValue()).pad(10);
            table.row();
        }
    }
}
