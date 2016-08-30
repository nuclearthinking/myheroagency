package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.quest.QuestManager;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import lombok.val;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Izonami on 23.05.2016.
 */
public class QuestLayer extends AbstractLayer implements ILayer {

    //LinkedHashMap используется для того что бы отображать в порядке добавления
    private static final Map<String, Actor> questsList = new LinkedHashMap<String, Actor>();
    private Image i;
    private boolean isShowTable = false;

    public QuestLayer(final UiFactory factory){
        super(factory);

        //TODO: Удалить это или переписать если так уж надо картинку
        val t = Asset.getInstance().get("img/testQuestLayer.jpg", Texture.class);
        i = new Image(t);
    }

    @Override
    public void buildLayer() {

        table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());

        i.setFillParent(true);
        i.setColor(Color.FOREST);

        addQuestToList(QuestManager.getQuestById(1).getName()); //TODO: Убрать, это для теста

        table.addActor(i);

        for(val a : questsList.entrySet()){
            table.add(a.getValue()).pad(10);
            table.row();
        }
    }

    public void setTableVisible(final boolean isShowTable){
        this.isShowTable = isShowTable;

        if (isShowTable)
            table.addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
        else
            table.addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
    }

    public boolean isShowTable(){
        return isShowTable;
    }

    /**
     * Служит для добавления нового задания в карту и перерисовку таблицы
     * @param name - является ключом и названием кнопки
     */
    public void addQuestToList(final String name){
        val quest = factory.getLabel(name);

        quest.setColor(Color.BLACK);
        questsList.put(name, quest);

        //TODO: Пока не придумал ничего лучше кроме как сбрасывать и добавлять по новой
        table.reset();
        table.addActor(i);
        for(val a : questsList.entrySet()){
            table.add(a.getValue()).pad(10);
            table.row();
        }
    }

    @Override
    public void resize(final int w, final int h){
        super.resize(w,h);

        table.setPosition(-Gdx.graphics.getWidth(), 0);
        table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
    }

}
