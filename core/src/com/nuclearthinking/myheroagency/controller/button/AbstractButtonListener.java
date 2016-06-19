package com.nuclearthinking.myheroagency.controller.button;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by Izonami on 19.06.2016.
 */
public abstract class AbstractButtonListener extends ClickListener {

    final protected Logger logger = new SimpleLoggerFactory().getLogger(getName());
    private final TextButton button;

    protected AbstractButtonListener(final TextButton button){
        this.button = button;
    }

    @Override
    public abstract void clicked (InputEvent event, float x, float y);

    @Override
    public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
        button.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
    }

    protected TextButton getTextButton(){
        return button;
    }

    private String getName() {
        return getClass().getSimpleName();
    }

}
