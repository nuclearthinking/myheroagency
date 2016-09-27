package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.myheroagency.model.entity.components.BodyComponent;
import com.nuclearthinking.myheroagency.model.entity.components.Components;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 27.09.2016.
 */
public class BodySystem extends IteratingSystem {
    private static final Family family = Family.all(BodyComponent.class,
                                                    TransformComponent.class).get();

    private final World world;
    private Body worldBody;
    public BodySystem(@NonNull World world) {
        super(family);

        this.world = world;

        val bodyDef = BodyComponent.getBodyDinamic();
        val fixtureDef = BodyComponent.getFixture();
        worldBody = world.createBody(bodyDef);

        val bodyPolygon = new CircleShape();
        bodyPolygon.setRadius(32f);
        fixtureDef.shape = bodyPolygon;
        fixtureDef.friction = 0.3f;
        worldBody.createFixture(fixtureDef);
        bodyPolygon.dispose();

        worldBody.setFixedRotation(true);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val target = Components.TRANSFORM.get(entity);

        worldBody.setTransform(target.getPos().x, target.getPos().y, 0.0f);
    }
}
