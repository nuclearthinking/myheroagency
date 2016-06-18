package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nuclearthinking.myheroagency.i18n.Localization;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 29.05.2016.
 */
public abstract class AbstractLayer {

    protected final UiFactory factory;
    protected final Localization locale = new Localization(this.getClass());
    private Table table;

    AbstractLayer(final UiFactory factory){
        this.factory = factory;
    }

    public abstract void setTableVisible(final boolean isShowTable);

    public void resize(final int w, final int h){
        getTable().getStage().getViewport().update(w, h, true);
    }

    public Table getTable(){
        if (table == null){
            table = new Table();
            table.setDebug(Constants.DEBUG);
            table.setPosition(-Gdx.graphics.getWidth(), 0);
            return table;
        }
        return table;
    }

}
