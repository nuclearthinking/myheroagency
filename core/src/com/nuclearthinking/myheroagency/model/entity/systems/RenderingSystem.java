package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.nuclearthinking.myheroagency.model.entity.components.TextureComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;
import lombok.Getter;
import lombok.val;

import java.util.Comparator;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class RenderingSystem extends IteratingSystem {
    static final float FRUSTUM_WIDTH = 10;
    static final float FRUSTUM_HEIGHT = 15;
    static final float PIXELS_TO_METRES = 1.0f / 32.0f;

    private Batch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private @Getter OrthographicCamera camera;

    private ComponentMapper<TextureComponent> textureM;
    private ComponentMapper<TransformComponent> transformM;

    public RenderingSystem(Batch batch) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get());

        textureM = ComponentMapper.getFor(TextureComponent.class);
        transformM = ComponentMapper.getFor(TransformComponent.class);

        renderQueue = new Array<Entity>();

        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity entityA, Entity entityB) {
                return (int)Math.signum(transformM.get(entityB).getPos().z -
                        transformM.get(entityA).getPos().z);
            }
        };

        this.batch = batch;

        camera = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        camera.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (Entity entity : renderQueue) {
            val tex = textureM.get(entity);

            if (tex.getRegion() == null) {
                continue;
            }

            val t = transformM.get(entity);

            float width = tex.getRegion().getRegionWidth();
            float height = tex.getRegion().getRegionHeight();
            float originX = width * 0.5f;
            float originY = height * 0.5f;

            batch.draw(tex.getRegion(),
                    t.getPos().x - originX, t.getPos().y - originY,
                    originX, originY,
                    width, height,
                    t.getScale().x * PIXELS_TO_METRES, t.getScale().y * PIXELS_TO_METRES,
                    MathUtils.radiansToDegrees * t.getRotation());
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

}
