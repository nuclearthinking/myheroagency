package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nuclearthinking.myheroagency.controller.ObjectManager;
import com.nuclearthinking.myheroagency.controller.observer.ObserverCon;
import com.nuclearthinking.myheroagency.controller.observer.ObserverMen;
import com.nuclearthinking.myheroagency.ui.UiFactory;

/**
 * Created by Izonami on 22.07.2016.
 */
public class PlayerLayer extends AbstractLayer implements ObserverCon, ObserverMen {

    //Блок статуса персонажа
    private final Label playerLvl;
    private final Label playerHp;
    private final Label playerBaseHp;
    private final Label playerMp;
    private final Label playerBaseMp;
    private final Label playerExp;
    private final ObjectManager objectManager;

    public PlayerLayer(final UiFactory factory) {
        super(factory);

        objectManager = new ObjectManager();
        playerLvl = factory.getLabel("playerLvl");
        playerHp = factory.getLabel("playerHp");
        playerBaseHp = factory.getLabel("playerBaseHp");
        playerMp = factory.getLabel("playerMp");
        playerBaseMp = factory.getLabel("playerBaseMp");
        playerExp = factory.getLabel("playerExp");
    }

    @Override
    public void buildLayer() {
        playerLvl.setText("Lvl " + Integer.toString(objectManager.getPlayer().getLevel()));
        playerHp.setText("Hp " + Integer.toString(objectManager.getPlayer().getCurHp()));
        playerBaseHp.setText("/" + Integer.toString(objectManager.getPlayer().getBaseHp()));
        playerMp.setText("Mp " + Integer.toString(objectManager.getPlayer().getCurMp()));
        playerBaseMp.setText("/" + Integer.toString(objectManager.getPlayer().getBaseMp()));
        playerExp.setText("Exp " + Integer.toString(objectManager.getPlayer().getExp()));

        table.setPosition(70, Gdx.graphics.getHeight()-60);

        playerLvl.setColor(Color.WHITE);
        playerHp.setColor(Color.RED);
        playerExp.setColor(Color.CORAL);

        table.add(playerLvl).left();
        table.row();
        table.add(playerHp).left();
        table.add(playerBaseHp).right();
        table.row();
        table.add(playerMp).left();
        table.add(playerBaseMp).right();
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
        playerHp.setText("Hp " + Integer.toString(objectManager.getPlayer().getCurHp()));
        playerBaseHp.setText("/" + Integer.toString(objectManager.getPlayer().getBaseHp()));
    }

    @Override
    public void updateMp(){
        playerMp.setText("Mp " + Integer.toString(objectManager.getPlayer().getCurMp()));
        playerBaseMp.setText("/" + Integer.toString(objectManager.getPlayer().getBaseMp()));
    }

    public void updateExp(){
        playerExp.setText("Exp " + Integer.toString(objectManager.getPlayer().getExp()));
    }

    @Override
    public void resize(final int w, final int h){
        super.resize(w,h);

        table.setPosition(70, Gdx.graphics.getHeight()-60);
    }

}
