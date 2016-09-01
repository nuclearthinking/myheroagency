package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class PlayerComponent implements Component {
    public static final int IDLE = 0;
    public static final int MOVE_R = 1;
    public static final int MOVE_L = 2;
    public static final float MOVE_VELOCITY = 20;
    public static final float WIDTH = 0.8f;
    public static final float HEIGHT = 0.8f;

    public float heightSoFar = 0.0f;
}
