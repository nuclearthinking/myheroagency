package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.controller.button.PlusConListener;
import com.nuclearthinking.myheroagency.ui.UiFactory;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Izonami on 02.08.2016.
 */
public class PlayerStatLayer extends AbstractLayer implements ILayer, ObserverStats{

    private boolean isShowTable = false;
    private TextButton plus;
    private Label con;
    private final ObjectManager objectManager;

    public PlayerStatLayer(final UiFactory factory) {
        super(factory);

        this.con = factory.getLabel("con");
        this.plus = factory.getTextButton("plusCon");
        this.objectManager = new ObjectManager();

    }

    @Override
    public void buildLayer() {
        table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        table.setPosition(60, Gdx.graphics.getHeight()-60);

        con.setText(Integer.toString(objectManager.getPlayer().getCON()));
        plus.addListener(new PlusConListener(plus, objectManager));

        table.add(con).center();
        table.add(plus);
    }

    @Override
    public void setTableVisible(boolean isShowTable) {
        this.isShowTable = isShowTable;

        if (isShowTable)
            table.addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
        else
            table.addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
    }

    @Override
    public boolean isShowTable() {
        return isShowTable;
    }

    @Override
    public void resize(final int w, final int h){
        super.resize(w,h);

        table.setPosition(-Gdx.graphics.getWidth(), 0);
        table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
    }

    //TODO: Вот это мне очень не нравится
    public PlusConListener getListener(){
        return (PlusConListener) plus.getListeners().get(1);
    }

    @Override
    public void update() {
        con.setText(Integer.toString(objectManager.getPlayer().getCON()));
    }
}
