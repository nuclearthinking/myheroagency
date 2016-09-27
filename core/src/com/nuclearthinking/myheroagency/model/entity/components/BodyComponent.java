package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import lombok.val;

/**
 * Created by mkuksin on 27.09.2016.
 */
public class BodyComponent implements Component {
    public static BodyDef getBodyDinamic(){
        val body = new BodyDef();
        body.type = BodyDef.BodyType.DynamicBody;

        return body;
    }

    public static FixtureDef getFixture(){
        val fix = new FixtureDef();
        return fix;
    }
}
