package com.nuclearthinking.myheroagency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by mkuksin on 01.09.2016.
 */
@AllArgsConstructor
public enum AnimationState {

    IDLE(0),
    LEFT(1),
    RIGHT(2),
    JUMP(3),
    FALL(4),
    HIT(5),
    DAMAGE(6);

    public static final int NUM_STATES = values().length;

    private @Getter int value;
}
