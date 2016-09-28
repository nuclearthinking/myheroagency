package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nuclearthinking.myheroagency.model.entity.components.Components;
import com.nuclearthinking.myheroagency.model.entity.components.TextureComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import java.util.Comparator;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class RenderingSystem extends IteratingSystem {
    private static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth();
    private static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight();
    private static final float PIXELS_TO_METRES = 1.0f / 32.0f;

    private Batch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private @Getter OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer renderer;

    public RenderingSystem(@NonNull final Batch batch, @NonNull World world) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get());

        renderQueue = new Array<Entity>();

        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity entityA, Entity entityB) {
                return (int)Math.signum(Components.TRANSFORM.get(entityB).getPos().z -
                        Components.TRANSFORM.get(entityA).getPos().z);
            }
        };

        this.batch = batch;
        this.world = world;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);

        renderer = new Box2DDebugRenderer();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);

        camera.update();

        world.step(Gdx.graphics.getDeltaTime(), 0, 0);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (val entity : renderQueue) {
            val tex = Components.TEXTURE.get(entity);

            if (tex.getRegion() == null) {
                continue;
            }
            @NonNull val t = Components.BODY.get(entity);
            @NonNull val t2 = Components.TRANSFORM.get(entity);

            val width = tex.getRegion().getRegionWidth();
            val height = tex.getRegion().getRegionHeight();
            val originX = width * 0.5f;
            val originY = height * 0.5f;

            batch.draw(tex.getRegion(),
                    t.getBody().getPosition().x - originX, t.getBody().getPosition().y - originY,
                    originX, originY,
                    width, height,
                    t2.getScale().x * PIXELS_TO_METRES, t2.getScale().y * PIXELS_TO_METRES,
                    MathUtils.radiansToDegrees * t2.getRotation());
        }

        batch.end();

        renderer.render(world, camera.combined);

        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

}
