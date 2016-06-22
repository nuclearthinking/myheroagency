package com.nuclearthinking.myheroagency.controller;

import com.nuclearthinking.myheroagency.model.GameObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Izonami on 22.06.2016.
 */
public class SpriteManager {

    private static final ArrayList<GameObject> spriteObject = new ArrayList<GameObject>();

    public static void addGameObject(final GameObject object){
        spriteObject.add(object);
    }

    public static ArrayList<GameObject> getAllObjects(){
        return spriteObject;
    }

    public static void removeObject(final GameObject gameObject)
    {
        final Iterator<GameObject> iterator = spriteObject.iterator();

        while (iterator.hasNext())
        {
            final GameObject tmp = iterator.next();
            if (tmp.equals(gameObject))
                iterator.remove();
        }
    }
}
