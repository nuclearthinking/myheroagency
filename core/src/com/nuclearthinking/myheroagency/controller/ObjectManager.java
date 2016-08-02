package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.nuclearthinking.myheroagency.model.actor.Player;

/**
 * Created by Izonami on 23.06.2016.
 */
public class ObjectManager {

    private static Player player;
    private Animation idle, left,right;
    private final TextureAtlas playerAtlas = Asset.getInstance().get("player/player.pack");

    public Player getPlayer(){
        if(player == null){
            idle = new Animation(1 / 2f, playerAtlas.findRegions("still"), Animation.PlayMode.LOOP);
            left = new Animation(1 / 6f, playerAtlas.findRegions("left"), Animation.PlayMode.LOOP);
            right = new Animation(1 / 6f, playerAtlas.findRegions("right"), Animation.PlayMode.LOOP);
            player = new Player(null, 40, 35, idle,left,right);
            return player;
        }
        return player;
    }
}
