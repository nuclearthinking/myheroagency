package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class StateComponent implements Component {
    private int state = 0;
    public float time = 0.0f;

    public int get() {
        return state;
    }

    public void set(int newState) {
        state = newState;
        time = 0.0f;
    }
}
