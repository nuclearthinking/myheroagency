package com.nuclearthinking.myheroagency.model.actor;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.nuclearthinking.myheroagency.model.template.CharTemplate;

/**
 * Created by Izonami on 22.06.2016.
 */
public class Npc extends GameObject {
    /**
     * @param collisionLayer - коллизия объекта
     * @param sizeHeight     - высота спрайта
     * @param sizeWidth      - ширина спрайта
     */
    public Npc(final TiledMapTileLayer collisionLayer, final int sizeHeight, final int sizeWidth, final CharTemplate template) {
        super(collisionLayer, sizeHeight, sizeWidth, template);
    }

    @Override
    protected void update(float delta) {

    }
}
