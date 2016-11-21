package com.nuclearthinking.myheroagency.controller.manager;

import com.badlogic.ashley.core.Entity;
import com.nuclearthinking.myheroagency.model.components.BodyComponent;
import lombok.NonNull;

import java.util.Random;

/**
 * Created by mkuksin on 20.10.2016.
 */


//TODO: ЗАТЫЧКА ДЛЯ СПАВНА
@Deprecated
public final class BuildNpcManager {

    private Random rnd = new Random();

    public BuildNpcManager(){}

    @Deprecated
    public void spawnNpc(@NonNull final Entity npc, final float x, final float y){
        npc.getComponent(BodyComponent.class).getBody().setTransform(rnd.nextInt((int)x), y, 0);
    }

}
