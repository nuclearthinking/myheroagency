package com.nuclearthinking.myheroagency.model.actor.base;

import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 01.09.2016.
 */
public final class StateComponent implements Component {

    private @Getter int state = 0;
    private @Getter @Setter float time = 0.0f;

    public void set(int state) {
        this.state = state;
        time = 0.0f;
    }
}
