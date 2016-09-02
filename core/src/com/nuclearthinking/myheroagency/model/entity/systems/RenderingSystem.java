package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
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
    static final float FRUSTUM_WIDTH = 400;
    static final float FRUSTUM_HEIGHT = 600;
    static final float PIXELS_TO_METRES = 1.0f / 32.0f;

    private OrthogonalTiledMapRenderer renderer;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private @Getter OrthographicCamera camera;

    public RenderingSystem(OrthogonalTiledMapRenderer renderer) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get());

        renderQueue = new Array<Entity>();

        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity entityA, Entity entityB) {
                return (int)Math.signum(Components.TRANSFORM.get(entityB).getPos().z -
                        Components.TRANSFORM.get(entityA).getPos().z);
            }
        };

        this.renderer = renderer;

        camera = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        camera.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);

        camera.update();

        renderer.getBatch().setProjectionMatrix(camera.combined);
        renderer.getBatch().begin();

        for (val entity : renderQueue) {
            val tex = Components.TEXTURE.get(entity);

            if (tex.getRegion() == null) {
                continue;
            }

            @NonNull val t = Components.TRANSFORM.get(entity);

            val width = tex.getRegion().getRegionWidth();
            val height = tex.getRegion().getRegionHeight();
            val originX = width * 0.5f;
            val originY = height * 0.5f;

            renderer.getBatch().draw(tex.getRegion(),
                    t.getPos().x - originX, t.getPos().y - originY,
                    originX, originY,
                    width, height,
                    t.getScale().x * PIXELS_TO_METRES, t.getScale().y * PIXELS_TO_METRES,
                    MathUtils.radiansToDegrees * t.getRotation());
        }

        renderer.getBatch().end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

}
