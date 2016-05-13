package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nuclearthinking.myheroagency.controller.Assets;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by Izonami on 11.05.2016.
 */
public class MainMenuScreen extends AbstractScreen {

    private Table table;
    private Image bgMenu;
    private Music menuMusic;
    private TextButton play, load, exit;
    private static ClickListener button;

    @Override
    public void buildStage() {
        Assets.getInstance().setSkin(Assets.getInstance().getAssetManager().get("ui/ui.json", Skin.class));
        Assets.getInstance().getSkin().addRegions(Assets
                .getInstance()
                .getAssetManager()
                .get("ui/ui.atlas", TextureAtlas.class));

        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        table.setClip(true);

        createListener();
        initButton();

        table.add(play).width(100).pad(10);
        table.row();
        table.add(load).width(100).pad(10);
        table.row();
        table.add(exit).width(100).pad(10);

        addActor(table);
        addAction(sequence(moveTo(getWidth(), 0), moveTo(0, 0, .5f)));
    }

    //TODO: Продумать правильное решение для добавление кнопок. Какая нибудь фабрика или иное решение. Все что ниже сплошной говнокод
    private void initButton(){
        play = new TextButton("Play", Assets.getInstance().getSkin(), "default");
        play.setSize(150, 35);
        play.addListener(button);
        load = new TextButton("Loading", Assets.getInstance().getSkin(), "default");
        load.setSize(150, 35);
        load.addListener(button);
        exit = new TextButton("Exit", Assets.getInstance().getSkin(), "default");
        exit.setSize(150, 35);
        exit.addListener(button);
    }

    @Override
    public void resize(int width, int height){
        super.resize(width, height);

        table.setSize(width, height);
    }

    private void createListener(){
        button = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(event.getListenerActor() == play){

                }else if(event.getListenerActor() == load){

                }else if(event.getListenerActor() == exit){
                    Gdx.app.exit();
                }else {
                    System.out.println("I don't know");
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(event.getListenerActor() == play){
                    play.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
                }else if(event.getListenerActor() == load){
                    load.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
                }else if(event.getListenerActor() == exit){
                    exit.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
                }else {
                    System.out.println("I don't know");
                }
            }
        };
    }

}
