package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
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
    static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth();
    static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight();
    static final float PIXELS_TO_METRES = 1.0f / 32.0f;

    private Batch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private @Getter OrthographicCamera camera;

    public RenderingSystem(Batch batch) {
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

        camera = new OrthographicCamera();
        camera.setToOrtho(false, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);

        camera.update();

        batch.begin();

        for (val entity : renderQueue) {
            val tex = Components.TEXTURE.get(entity);

            if (tex.getRegion() == null) {
                continue;
            }

            /*val light = Components.LIGHT.get(entity);
            light.getRayHandler().setCombinedMatrix(camera);
            light.getRayHandler().updateAndRender();*/

            @NonNull val t = Components.TRANSFORM.get(entity);

            val width = tex.getRegion().getRegionWidth();
            val height = tex.getRegion().getRegionHeight();
            val originX = width * 0.5f;
            val originY = height * 0.5f;

            batch.draw(tex.getRegion(),
                    t.getPos().x - originX, t.getPos().y - originY,
                    originX, originY,
                    width, height,
                    t.getScale().x * PIXELS_TO_METRES, t.getScale().y * PIXELS_TO_METRES,
                    MathUtils.radiansToDegrees * t.getRotation());
            //batch.draw(tex.getRegion(), t.getPos().x, t.getPos().y, width, height);
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

}
