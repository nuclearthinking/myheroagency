package com.nuclearthinking.myheroagency.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * Created by Izonami on 22.06.2016.
 */
public abstract class GameObject extends Sprite {

    private final TiledMapTileLayer collisionLayer;

    /**
     * @param collisionLayer    - коллизия объекта
     * @param sizeHeight        - высота спрайта
     * @param sizeWidth         - ширина спрайта
     * @param direction         - первый кадр(состояние)
     */
    public GameObject(final TiledMapTileLayer collisionLayer, int sizeHeight, int sizeWidth, final Animation... direction){
        super(direction[0].getKeyFrame(0)); //Кадр по умолчанию

        this.collisionLayer = collisionLayer;
        setSize(sizeWidth, sizeHeight);
    }

    @Override
    public void draw(Batch batch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    // Все действия над объектом производить в этом методе
    protected abstract void update(final float delta);

    public TiledMapTileLayer getCollisionLayer(){
        return collisionLayer;
    }

    public void setObjectSizeHeight(final int height){
        setSize(getWidth(), height);
    }

    public void setObjectSizeWidth(final int width){
        setSize(width, getHeight());
    }

    // Определяющие методы, опредяляют к какому типу относится объект.
    // Нужно переопределять в классах наследниках
    public boolean isPlayer(){
        return false;
    }

    public boolean isMonster(){
        return false;
    }

    public boolean isNpc(){
        return false;
    }
}
