package com.nuclearthinking.myheroagency.model.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

import java.io.Serializable;

public class Player extends GameObject implements Serializable {

    private final Vector3 velocity = new Vector3();
    private static float speed = 100;
    private static Animation idle, left, right;
    private int exp = 0;

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
        this.left = direction[1];
        this.right = direction[2];
    }

    @Override
    protected void update(float delta) {
        setX(getX() + velocity.x * delta); // Задаем новую позицию

        animationTimer += delta;
        setRegion(velocity.x < 0 ? left.getKeyFrame(animationTimer) : velocity.x > 0 ? right.getKeyFrame(animationTimer) : idle.getKeyFrame(animationTimer));
    }

    public int getExp() {
        return exp;
    }

    public void setExp(final int exp) {
        this.exp = exp;
    }

    public float getSpeed(){
        return speed;
    }

    public Vector3 getVelocity(){
        return velocity;
    }

    public void resetVelocity() {
        velocity.x = 0;
        velocity.y = 0;
    }

    @Override
    public boolean isPlayer(){
        return true;
    }

}
