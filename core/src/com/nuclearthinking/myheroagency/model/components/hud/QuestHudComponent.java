package com.nuclearthinking.myheroagency.model.components.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nuclearthinking.myheroagency.controller.button.menu.QuestTestListener;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import lombok.Setter;
import lombok.val;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by mkuksin on 10.10.2016.
 */
public final class QuestHudComponent implements Component {
    public static final Table table = new Table();
    public static final Map<String, Actor> QUEST_LIST = new LinkedHashMap<String, Actor>();

    public static boolean isShowTable = true;

    private static @Setter UiFactory factory;

    public void addQuestToList(final String name){
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
