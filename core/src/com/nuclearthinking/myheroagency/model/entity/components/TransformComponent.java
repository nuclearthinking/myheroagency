package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import lombok.Getter;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class TransformComponent implements Component {
    private @Getter final Vector3 pos = new Vector3();
    private @Getter final Vector2 scale = new Vector2(1.0f, 1.0f);
    private @Getter float rotation = 0.0f;
}
