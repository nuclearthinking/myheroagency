package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.ui.UiFactory;

/**
 * Created by Izonami on 22.07.2016.
 */
public class PlayerLayer extends AbstractLayer {

    private Label playerLvl;
    private Label playerHp;
    private Label playerExp;
    private ObjectManager objectManager;

    public PlayerLayer(final UiFactory factory) {
        super(factory);
        objectManager = new ObjectManager();
        playerLvl = factory.getLabel("playerLvl");
        playerHp = factory.getLabel("playerHp");
        playerExp = factory.getLabel("playerExp");
        playerLvl.setText("Lvl " + Integer.toString(objectManager.getPlayer().getLevel()));
        playerHp.setText("Hp " + "1000");
        playerExp.setText("Exp " + Integer.toString(objectManager.getPlayer().getExp()));
        getTable().setPosition(60, Gdx.graphics.getHeight()-60);
        playerLvl.setColor(Color.WHITE);
        playerHp.setColor(Color.RED);
        playerExp.setColor(Color.CORAL);

        getTable().add(playerLvl).left();
        getTable().row();
        getTable().add(playerHp).left();
        getTable().row();
        getTable().add(playerExp).left();
    }

    public void updateAll(){
        updateLvl();
        updateHp();
        updateExp();
    }

    public void updateLvl(){
        playerLvl.setText("Lvl " + Integer.toString(objectManager.getPlayer().getLevel()));
    }

    public void updateHp(){
        playerHp.setText(Integer.toString(objectManager.getPlayer().getLevel()));
    }

    public void updateExp(){
        playerExp.setText(Integer.toString(objectManager.getPlayer().getExp()));
    }

    @Override
    public void setTableVisible(boolean isShowTable) {

    }

    @Override
    public void resize(final int w, final int h){
        super.resize(w,h);

        getTable().setPosition(60, Gdx.graphics.getHeight()-60);
    }
}
