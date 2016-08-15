package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.controller.button.PlusStrListener;
import com.nuclearthinking.myheroagency.ui.UiFactory;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Izonami on 02.08.2016.
 */
public class PlayerStatLayer extends AbstractLayer implements ILayer{

    private boolean isShowTable = false;

    public PlayerStatLayer(final UiFactory factory) {
        super(factory);

    }

    @Override
    public void buildLayer() {
        table.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        table.setPosition(60, Gdx.graphics.getHeight()-60);

        ObjectManager objectManager = new ObjectManager();
        Label str = factory.getLabel("str");
        TextButton plus = factory.getTextButton("plusStr");

        str.setText(Integer.toString(objectManager.getPlayer().getSTR()));
        plus.addListener(new PlusStrListener(plus, objectManager));

        table.add(str).center();
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

}
