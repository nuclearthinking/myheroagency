package com.nuclearthinking.myheroagency.model.actor;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.nuclearthinking.myheroagency.model.template.CharTemplate;
import lombok.Getter;
import lombok.val;

import java.io.Serializable;

public class Player extends GameObject implements Serializable {

    private @Getter final Vector3 velocity;
    private @Getter int exp = 0;

    /**
     * @param collisionLayer - коллизия объекта
     * @param sizeHeight     - высота спрайта
     * @param sizeWidth      - ширина спрайта
     */
    public Player(final TiledMapTileLayer collisionLayer, final int sizeHeight, final int sizeWidth, final CharTemplate template) {
        super(collisionLayer, sizeHeight, sizeWidth, template);

        this.velocity = new Vector3();
    }

    @Override
    protected void update(float delta) {
        setX(getX() + velocity.x * delta); // Задаем новую позицию
        setY(getY() + velocity.y * delta);

        animationTimer += delta;
        setRegion(velocity.x < 0 ? leftAnimation.getKeyFrame(animationTimer) : velocity.x > 0 ? rightAnimation.getKeyFrame(animationTimer) : idleAnimation.getKeyFrame(animationTimer));
    }

    public void setExp(final int exp) {
        val tmpExp = this.exp + exp;

        if(checkLvlUp(tmpExp)){
            setLevel((byte)1);
            this.exp = tmpExp;
        }
    }

    public void resetVelocity() {
        velocity.x = 0;
        velocity.y = 0;
    }

    //TODO: Условие для поднятие уровня
    private boolean checkLvlUp(final int exp){
        if(exp > 100){
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlayer(){
        return true;
    }

}
