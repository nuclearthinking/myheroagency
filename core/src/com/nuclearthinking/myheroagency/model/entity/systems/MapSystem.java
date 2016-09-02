package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.entity.components.MapComponent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


/**
 * Created by mkuksin on 01.09.2016.
 */
public class MapSystem extends IteratingSystem {
    private OrthographicCamera camera;
    private @Getter @Setter TiledMap tiledMap = null;
    private @Getter @Setter OrthogonalTiledMapRenderer renderer = null;

    public MapSystem() {
        this("map/testMap.tmx");
    }

    public MapSystem(@NonNull String mapName){
        super(Family.all(MapComponent.class).get());

        tiledMap = Asset.getInstance().get(mapName,TiledMap.class);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMap.dispose();
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderer.setView(camera);
        renderer.render();
    }

    public Batch getBatch(){
        return renderer.getBatch();
    }

}
