package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nuclearthinking.myheroagency.model.entity.Components;
import com.nuclearthinking.myheroagency.model.entity.components.BodyComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TextureComponent;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class RenderingSystem extends IteratingSystem {

    private static final Family family = Family.all(TextureComponent.class,
            BodyComponent.class).get();
    private static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth();
    private static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight();
    private static final float PIXELS_TO_METRES = 1.0f / 32.0f;

    private Batch batch;
    private Array<Entity> renderQueue;
    private @Getter OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderMap;
    private World world;
    private Box2DDebugRenderer renderer;
    private int[] layer = {0};
    private int[] layer1 = {1};

    public RenderingSystem(@NonNull final OrthogonalTiledMapRenderer renderMap, @NonNull World world) {
        super(family);

        renderQueue = new Array<Entity>();

        this.renderMap = renderMap;
        this.batch = renderMap.getBatch();
        this.world = world;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);

        renderer = new Box2DDebugRenderer();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        camera.update();

        world.step(Gdx.graphics.getDeltaTime(), 0, 0);

        renderMap.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (val entity : renderQueue) {
            val tex = Components.TEXTURE.get(entity);

            if (tex.getRegion() == null) {
                continue;
            }

            @NonNull val b = Components.BODY.get(entity);
            val width = tex.getRegion().getRegionWidth();
            val height = tex.getRegion().getRegionHeight();
            val originX = width * 0.5f;
            val originY = height * 0.5f;

            batch.draw(tex.getRegion(),
                    b.getBody().getPosition().x - originX, b.getBody().getPosition().y - originY,
                    originX, originY,
                    width, height,
                    b.getScale().x * PIXELS_TO_METRES, b.getScale().y * PIXELS_TO_METRES,
                    MathUtils.radiansToDegrees * b.getRotation());
        }

        batch.end();

        renderer.render(world, camera.combined);

        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public void resize(int w, int h){
        camera.setToOrtho(false, w, h);
    }

}
