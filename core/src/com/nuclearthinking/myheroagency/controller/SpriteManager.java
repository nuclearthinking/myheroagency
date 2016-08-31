package com.nuclearthinking.myheroagency.controller;

import com.nuclearthinking.myheroagency.model.actor.GameObject;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Izonami on 22.06.2016.
 */
public class SpriteManager {

    private static final @Getter ArrayList<GameObject> spriteObject = new ArrayList<GameObject>();

    public static void addGameObject(@NonNull final GameObject object){
        spriteObject.add(object);
    }

    public static void removeObject(@NonNull final GameObject gameObject) {
        val iterator = spriteObject.iterator();

        while (iterator.hasNext()) {
            val tmp = iterator.next();
            if (tmp.equals(gameObject))
                iterator.remove();
        }
    }
}
