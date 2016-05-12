package com.nuclearthinking.myheroagency.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Logger;
import com.nuclearthinking.myheroagency.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by Izonami on 12.05.2016.
 */
public class MenuButton extends TextButton {

    private Logger logger = new Logger(Constants.LOG);

    public MenuButton(String text, Skin skin) {
        this(text,skin,"default");
    }

    public MenuButton (String text, Skin skin, String styleName) {
        super(text, skin, styleName);

        this.addListener(new List(text));
    }

    private class List extends ClickListener{

        private final String name;

        public List(String name){
            this.name = name;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            switch (name){
                case "Play" :
                    System.out.println("PLAY!");
                    break;
                case "Exit" :
                    Gdx.app.exit();
                    break;
                default: logger.error("Can't find Action Clicked for " + name);
            }
        }

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            switch (name){
                case "Play" :
                    addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
                    break;
                case "Loading" :
                    addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
                    break;
                case "Exit" :
                    addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
                    break;
                default: logger.error("Can't find Action Clicked for " + name);
            }

        }
    }
}
