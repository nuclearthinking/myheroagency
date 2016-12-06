package com.nuclearthinking.myheroagency.model.ui.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;

/**
 * Created by mkuksin on 01.12.2016.
 */
public class SkillHudComponent implements Component {

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
}
