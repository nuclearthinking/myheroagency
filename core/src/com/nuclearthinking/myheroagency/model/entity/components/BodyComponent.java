package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 27.09.2016.
 */
public class BodyComponent implements Component {
    private @Getter @Setter Body body = null;
    private @Getter BodyDef bodyDef = new BodyDef();
    private @Getter FixtureDef fixtureDef = new FixtureDef();
    private @Getter final Vector2 scale = new Vector2(32.0f, 32.0f);
    private @Getter @Setter float rotation = 0.0f;
}
