package com.nuclearthinking.myheroagency.model.actor;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.nuclearthinking.myheroagency.model.template.CharTemplate;

import java.io.Serializable;

public class Player extends GameObject implements Serializable {

    private final Vector3 velocity = new Vector3();
    private int exp = 0;

    /**
     * @param collisionLayer - коллизия объекта
     * @param sizeHeight     - высота спрайта
     * @param sizeWidth      - ширина спрайта
     */
    public Player(final TiledMapTileLayer collisionLayer, final int sizeHeight, final int sizeWidth, final CharTemplate template) {
        super(collisionLayer, sizeHeight, sizeWidth, template);
    }

    @Override
    protected void update(float delta) {
        setX(getX() + velocity.x * delta); // Задаем новую позицию

        animationTimer += delta;
        setRegion(velocity.x < 0 ? leftAnimation.getKeyFrame(animationTimer) : velocity.x > 0 ? rightAnimation.getKeyFrame(animationTimer) : idleAnimation.getKeyFrame(animationTimer));
    }

    public int getExp() {
        return exp;
    }

    public void setExp(final int exp) {
        this.exp = exp;
    }

    public float getSpeed(){
        return template.baseRunSpd;
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
