package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nuclearthinking.myheroagency.i18n.Localization;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

/**
 * Created by Izonami on 29.05.2016.
 */
public abstract class AbstractLayer {

    protected final Logger logger = new SimpleLoggerFactory().getLogger(getClass().getSimpleName());

    protected final UiFactory factory;
    protected final Localization locale = new Localization(this.getClass());
    protected final Table table;

    AbstractLayer(final UiFactory factory){
        this.factory = factory;

        table = new Table();
        table.setDebug(Constants.DEBUG);
    }

    public abstract void buildLayer();

    public void resize(final int w, final int h){
        table.getStage().getViewport().update(w, h, true);
    }

    public Table getTable(){
        return table;
    }

}
