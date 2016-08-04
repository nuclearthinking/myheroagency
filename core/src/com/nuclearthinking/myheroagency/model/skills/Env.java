package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.actor.GameObject;

/**
 * Created by Izonami on 03.08.2016.
 */
public final class Env {

    private GameObject character;
    private GameObject target;

    private double value;

    public Env(){}

    public Env(final GameObject player, final GameObject target){
        this.character = player;
        this.target = target;
    }

    public GameObject getCharacter(){
        return character;
    }

    public void setCharacter(final GameObject character){
        this.character = character;
    }

    public GameObject getTarget(){
        return target;
    }

    public void setTarget(final GameObject target){
        this.target = target;
    }

    public double getValue(){
        return value;
    }

    public void setValue(final double value){
        this.value = value;
    }
}
