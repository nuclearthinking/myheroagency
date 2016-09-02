package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.entity.components.MapComponent;
import lombok.Getter;


/**
 * Created by mkuksin on 01.09.2016.
 */
public class MapSystem extends IteratingSystem {
    private OrthographicCamera camera;
    private final TiledMap tiledMap;
    private @Getter final OrthogonalTiledMapRenderer renderer;

    public MapSystem() {
        super(Family.all(MapComponent.class).get());
        tiledMap = Asset.getInstance().get("map/testMap.tmx",TiledMap.class);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMap.dispose();
    }

    public OrthogonalTiledMapRenderer getRenderer(){
        return renderer;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderer.setView(camera);
        renderer.render();
    }
}
