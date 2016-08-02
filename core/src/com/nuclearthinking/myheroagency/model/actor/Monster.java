package com.nuclearthinking.myheroagency.model.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * Created by Izonami on 22.06.2016.
 */
public class Monster extends GameObject {
    /**
     * @param collisionLayer - коллизия объекта
     * @param sizeHeight     - высота спрайта
     * @param sizeWidth      - ширина спрайта
     * @param direction      - первый кадр(состояние)
     */
    public Monster(TiledMapTileLayer collisionLayer, int sizeHeight, int sizeWidth, Animation... direction) {
        super(collisionLayer, sizeHeight, sizeWidth, direction);
    }

    @Override
    protected void update(float delta) {

    }
}
