package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class MovementComponent implements Component {
    private @Getter final Vector2 velocity = new Vector2();
    private @Getter final Vector2 accel = new Vector2();
    private @Getter @Setter boolean isJump = false;
}
