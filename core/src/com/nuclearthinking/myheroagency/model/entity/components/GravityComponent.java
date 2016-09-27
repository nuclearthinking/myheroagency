package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

/**
 * Created by mkuksin on 02.09.2016.
 */
public class GravityComponent implements Component {
    private @Getter static final Vector2 gravity = new Vector2(0, -12);

    public void setGravity(int x, int y){
        gravity.set(x, y);
    }
}
