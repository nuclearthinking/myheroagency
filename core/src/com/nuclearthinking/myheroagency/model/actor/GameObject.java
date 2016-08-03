package com.nuclearthinking.myheroagency.model.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.nuclearthinking.myheroagency.model.skills.Function;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;

/**
 * Created by Izonami on 22.06.2016.
 */
public abstract class GameObject extends Sprite {

    protected float animationTimer = 0;
    protected Animation idleAnimation, leftAnimation, rightAnimation;

    private int hp = 100;
    private int mp = 100;
    private byte level = 1;

    private final TiledMapTileLayer collisionLayer;

    /**
     * @param collisionLayer    - коллизия объекта
     * @param sizeHeight        - высота спрайта
     * @param sizeWidth         - ширина спрайта
     */
    public GameObject(final TiledMapTileLayer collisionLayer, int sizeHeight, int sizeWidth){
        this.collisionLayer = collisionLayer;
        setSize(sizeWidth, sizeHeight);

        Function.addFuncToChar(this);
    }

    @Override
    public void draw(Batch batch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    // Все действия над объектом производить в этом методе
    protected abstract void update(final float delta);

    // Характеристики
    public int getHp(){
        return hp;
    }

    public void setHp(final int hp){
        this.hp = hp;
    }

    public int getMp(){
        return mp;
    }

    public void setMp(final int mp){
        this.mp = mp;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(final byte level) {
        this.level = level;
    }

    public final void addStatFunc(final Func f){

    }
    //TODO;
    public byte getSTR() {
        return 10;
    }

    // Отрисовка
    public TiledMapTileLayer getCollisionLayer(){
        return collisionLayer;
    }

    public void setObjectSizeHeight(final int height){
        setSize(getWidth(), height);
    }

    public void setObjectSizeWidth(final int width){
        setSize(width, getHeight());
    }

    public void setIdleAnimation(final Animation idleAnimation){
        this.idleAnimation = idleAnimation;
    }

    public void setRightAnimation(final Animation rightAnimation){
        this.rightAnimation = rightAnimation;
    }

    public void setLeftAnimation(final Animation leftAnimation){
        this.leftAnimation = leftAnimation;
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
