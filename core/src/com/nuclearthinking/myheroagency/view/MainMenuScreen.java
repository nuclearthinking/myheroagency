package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nuclearthinking.myheroagency.controller.Assets;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;

/**
 * Created by Izonami on 11.05.2016.
 */
public class MainMenuScreen extends AbstractScreen {

    private Image bgMenu;
    private Music menuMusic;
    private TextButton play, load, exit;

    @Override
    public void buildStage() {
        Assets.getInstance().getSkin().addRegions(Assets.getInstance().getAssetManager().get("ui/ui.atlas", TextureAtlas.class));
        Assets.getInstance().getSkin().load(Gdx.files.internal("ui/ui.json")); //TODO: Надо поискать способы избавится от этого

        initButton();

        addActor(play);
        addActor(load);
        addActor(exit);
    }

    //TODO: Продумать правильное решение для добавление кнопок. Какая нибудь фабрика или иное решение. Все что ниже сплошной говнокод
    private void initButton(){
        play = setUpButton("Play", 90, 260, 150,35);
        play.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        play.addListener(new ClickListener()
        {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                play.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
            }

            @Override
            public void clicked(InputEvent event, float x, float y)
            {

            }
        });

        load = setUpButton("Loading", 250, 260, 150,35);
        load.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        exit = setUpButton("Exit", 450, 260, 150,35);
        exit.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
    }

    private TextButton setUpButton(String label, int posX, int posY, int sizeX, int sizeY)
    {
        TextButton button = new TextButton(label, Assets.getInstance().getSkin(), "default");
        button.setPosition(posX, posY);
        button.setSize(sizeX, sizeY);

        return button;
    }
}
