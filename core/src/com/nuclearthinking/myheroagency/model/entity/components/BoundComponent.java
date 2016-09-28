package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class BoundComponent implements Component {
    private @Getter final Rectangle bounds = new Rectangle();
}
