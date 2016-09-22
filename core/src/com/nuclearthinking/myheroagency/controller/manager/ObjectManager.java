package com.nuclearthinking.myheroagency.controller.manager;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.actor.Player;
import com.nuclearthinking.myheroagency.model.template.CharTemplate;

/**
 * Created by Izonami on 23.06.2016.
 */
public class ObjectManager {

    private static Player player;
    private static final TextureAtlas playerAtlas = Asset.getInstance().get("player/player.pack");
    public static final Animation idle = new Animation(1 / 2f, playerAtlas.findRegions("still"), Animation.PlayMode.LOOP);
    public static final Animation left = new Animation(1 / 6f, playerAtlas.findRegions("left"), Animation.PlayMode.LOOP);
    public static final Animation right = new Animation(1 / 6f, playerAtlas.findRegions("right"), Animation.PlayMode.LOOP);


    public Player getPlayer(){
        if(player == null){
            player = new Player(null, 40, 35, CharTemplate.getInstance().getTemplate());
            player.setIdleAnimation(idle);
            player.setLeftAnimation(left);
            player.setRightAnimation(right);
            return player;
        }
        return player;
    }
}
