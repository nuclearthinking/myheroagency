package com.nuclearthinking.myheroagency.model.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.nuclearthinking.myheroagency.model.skills.Calculator;
import com.nuclearthinking.myheroagency.model.skills.Env;
import com.nuclearthinking.myheroagency.model.skills.Function;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;
import com.nuclearthinking.myheroagency.model.template.CharTemplate;

/**
 * Created by Izonami on 22.06.2016.
 */
public abstract class GameObject extends Sprite {

    protected float animationTimer = 0;
    protected Animation idleAnimation, leftAnimation, rightAnimation;
    protected CharTemplate template;

    private int hp = 100;
    private int mp = 100;
    private byte level = 1;

    private final TiledMapTileLayer collisionLayer;
    private final Calculator[] _calculators;

    /**
     * @param collisionLayer    - коллизия объекта
     * @param sizeHeight        - высота спрайта
     * @param sizeWidth         - ширина спрайта
     */
    public GameObject(final TiledMapTileLayer collisionLayer, int sizeHeight, int sizeWidth, final CharTemplate template){
        this.collisionLayer = collisionLayer;
        setSize(sizeWidth, sizeHeight);

        this.template = template;

        _calculators = new Calculator[Stats.NUM_STATS];

        Function.addFuncToChar(this);
    }

    @Override
    public void draw(Batch batch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    // Все действия над объектом производить в этом методе
    protected abstract void update(final float delta);

    public final void addStatFunc(final Func f) {
        if(f == null)
            return;

        final int stat = f._stat.ordinal();
        synchronized (_calculators) {
            if(_calculators[stat] == null)
                _calculators[stat] = new Calculator(f._stat, this);

            _calculators[stat].addFunc(f);
        }
    }

    public final double calcStat(final Stats stat, final double init, final GameObject target){
        final int id = stat.ordinal();
        final Calculator calculator = _calculators[id];

        if(calculator == null || calculator.size() == 0)
            return init;

        final Env env = new Env(this, target);
        env.setValue(init);

        calculator.calculate(env);

        return env.getValue();
    }

    // Характеристики
    public int getHp(){
        return (int) calcStat(Stats.MAX_HP, template.baseHpMax, null);
    }

    public void setHp(final int hp){
        this.hp = hp;
    }

    public int getMp(){
        return (int) calcStat(Stats.MAX_MP, template.baseMpMax, null);
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

    public byte getSTR() {
        return template.baseSTR;
    }

    public byte getCON(){
        return template.baseCON;
    }

    public void setCon(final byte con){
        template.baseCON += con;
    }

    public byte getDEX(){
        return template.baseDEX;
    }

    public byte getINT(){
        return template.baseINT;
    }

    public byte getWIT(){
        return template.baseWIT;
    }

    public byte getMEN(){
        return template.baseMEN;
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
