package com.nuclearthinking.myheroagency.controller.button;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nuclearthinking.myheroagency.controller.observer.Observable;
import com.nuclearthinking.myheroagency.controller.observer.Observer;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by Izonami on 19.06.2016.
 */
@Slf4j
public abstract class AbstractButtonListener extends ClickListener implements Observable {

    protected final TextButton button;
    protected final List<Observer> observers;

    public AbstractButtonListener(final TextButton button){
        this.button = button;
        this.observers = new ArrayList<Observer>();
    }

    @Override
    public abstract void clicked (InputEvent event, float x, float y);

    @Override
    public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
        button.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
    }

    @Override
    public void register(Observer observer) {
        if(observer == null)
            throw new NullPointerException("Null observer" );

        if(!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void notifyObservers(){

    }

    @Override
    public void unregister(Observer stats) {
        int index = observers.indexOf(stats);

        if(index >= 0){
            observers.remove(index);
        }
    }

}
