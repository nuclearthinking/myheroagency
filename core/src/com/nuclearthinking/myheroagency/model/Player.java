package com.nuclearthinking.myheroagency.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.io.Serializable;

public class Player extends GameObject implements Serializable {

    private static float speed = 60;
    private static Animation idle;
    private byte level;
    private int exp;

    /**
     * @param collisionLayer - коллизия объекта
     * @param sizeHeight     - высота спрайта
     * @param sizeWidth      - ширина спрайта
     * @param direction      - первый кадр(состояние)
     */
    public Player(TiledMapTileLayer collisionLayer, int sizeHeight, int sizeWidth, Animation... direction) {
        super(collisionLayer, sizeHeight, sizeWidth, direction);

        //Разные виды анимации
        this.idle = direction[0];
    }

    @Override
    protected void update(float delta) {
        float oldX = getX(), oldY = getY(); // Получаем координаты спрайта

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(final byte level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(final int exp) {
        this.exp = exp;
    }

    @Override
    public boolean isPlayer(){
        return true;
    }

}
