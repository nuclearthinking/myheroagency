package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nuclearthinking.myheroagency.controller.Assets;
import com.nuclearthinking.myheroagency.ui.MenuButton;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Izonami on 11.05.2016.
 */
public class MainMenuScreen extends AbstractScreen {

    private Table table;
    private Image bgMenu;
    private Music menuMusic;
    private MenuButton play, load, exit;

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
        play = new MenuButton("Play", Assets.getInstance().getSkin());
        play.setSize(150, 35);
        load = new MenuButton("Loading", Assets.getInstance().getSkin());
        load.setSize(150, 35);
        exit = new MenuButton("Exit", Assets.getInstance().getSkin());
        exit.setSize(150, 35);
    }

    @Override
    public void resize(int width, int height){
        super.resize(width, height);

        table.setSize(width, height);
    }
}
