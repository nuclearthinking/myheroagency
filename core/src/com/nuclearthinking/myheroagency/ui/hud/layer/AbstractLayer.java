package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nuclearthinking.myheroagency.i18n.Localization;
import com.nuclearthinking.myheroagency.model.Settings;
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

    public abstract void setTableVisible(boolean isShowTable);

    public Table getTable(){
        if (table == null){
            table = new Table();
            table.setDebug(Constants.DEBUG);
            table.setPosition(-Settings.getWidth(), 0);
            return table;
        }
        return table;
    }

}
