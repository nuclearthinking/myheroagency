package com.nuclearthinking.myheroagency.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.nuclearthinking.myheroagency.controller.Asset;
import lombok.Getter;

/**
 * Created by Izonami on 30.05.2016.
 */
public class MapManager {

    private final TiledMap tiledMap;
    private @Getter final OrthogonalTiledMapRenderer renderer;

    public MapManager(){
        tiledMap = Asset.getInstance().get("map/testMap.tmx", TiledMap.class);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMap.dispose();
    }

    public MapManager(final int level){
        tiledMap = Asset.getInstance().get("map/level"+level+".tmx", TiledMap.class);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMap.dispose();
    }

    public MapManager(final String map){
        tiledMap = Asset.getInstance().get("map/"+map, TiledMap.class);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMap.dispose();
    }

    public Batch getBatch(){
        return renderer.getBatch();
    }

}
