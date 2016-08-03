package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.actor.Monster;
import com.nuclearthinking.myheroagency.model.actor.Player;

/**
 * Created by Izonami on 03.08.2016.
 */
public final class Env {

    public Player player;
    public Monster target;

    public double value;

    public Env(){}

    public Env(final Player player, final Monster target){
        this.player = player;
        this.target = target;
    }
}
