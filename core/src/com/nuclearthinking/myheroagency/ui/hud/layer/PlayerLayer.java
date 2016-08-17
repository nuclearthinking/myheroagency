package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.controller.observer.ObserverCon;
import com.nuclearthinking.myheroagency.ui.UiFactory;

/**
 * Created by Izonami on 22.07.2016.
 */
public class PlayerLayer extends AbstractLayer implements ObserverCon {

    //Блок статуса персонажа
    private final Label playerLvl;
    private final Label playerHp;
    private final Label playerMp;
    private final Label playerExp;
    private final ObjectManager objectManager;

    public PlayerLayer(final UiFactory factory) {
        super(factory);

        objectManager = new ObjectManager();
        playerLvl = factory.getLabel("playerLvl");
        playerHp = factory.getLabel("playerHp");
        playerMp = factory.getLabel("playerMp");
        playerExp = factory.getLabel("playerExp");
    }

    @Override
    public void buildLayer() {
        playerLvl.setText("Lvl " + Integer.toString(objectManager.getPlayer().getLevel()));
        playerHp.setText("Hp " + Integer.toString(objectManager.getPlayer().getHp()));
        playerMp.setText("Mp " + Integer.toString(objectManager.getPlayer().getMp()));
        playerExp.setText("Exp " + Integer.toString(objectManager.getPlayer().getExp()));

        table.setPosition(60, Gdx.graphics.getHeight()-60);

        playerLvl.setColor(Color.WHITE);
        playerHp.setColor(Color.RED);
        playerExp.setColor(Color.CORAL);

        table.add(playerLvl).left();
        table.row();
        table.add(playerHp).left();
        table.row();
        table.add(playerMp).left();
        table.row();
        table.add(playerExp).left();
    }

    @Override
    public void update(){
        updateLvl();
        updateHp();
        updateMp();
        updateExp();
    }

    public void updateLvl(){
        playerLvl.setText("Lvl " + Integer.toString(objectManager.getPlayer().getLevel()));
    }

    @Override
    public void updateHp(){
        playerHp.setText("Hp " + Integer.toString(objectManager.getPlayer().getHp()));
    }

    public void updateMp(){
        playerMp.setText("Mp " + Integer.toString(objectManager.getPlayer().getMp()));
    }

    public void updateExp(){
        playerExp.setText("Exp " + Integer.toString(objectManager.getPlayer().getExp()));
    }

    @Override
    public void resize(final int w, final int h){
        super.resize(w,h);

        table.setPosition(60, Gdx.graphics.getHeight()-60);
    }

}
