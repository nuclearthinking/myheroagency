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
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

/**
 * Created by Izonami on 22.06.2016.
 */
@Slf4j(topic = "GameObject")
public abstract class GameObject extends Sprite {

    protected float animationTimer = 0;
    protected @Setter Animation idleAnimation, leftAnimation, rightAnimation;
    protected CharTemplate template;

    private @Getter int curHp;
    private @Getter int curMp;
    private @Getter @Setter byte level = 1;

    private final @Getter TiledMapTileLayer collisionLayer;
    private final Calculator[] _calculators;

    /**
     * @param collisionLayer    - коллизия объекта
     * @param sizeHeight        - высота спрайта
     * @param sizeWidth         - ширина спрайта
     */
    public GameObject(final TiledMapTileLayer collisionLayer, int sizeHeight, int sizeWidth, @NonNull final CharTemplate template){
        this.collisionLayer = collisionLayer;
        setSize(sizeWidth, sizeHeight);

        this.template = template;

        _calculators = new Calculator[Stats.NUM_STATS];

        //Function.addFuncToChar(this);

        curHp = getBaseHp();
        curMp = getBaseMp();
    }

    @Override
    public void draw(Batch batch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    // Все действия над объектом производить в этом методе
    protected abstract void update(final float delta);

    public final void addStatFunc(@NonNull final Func f) {
        val stat = f.getStat().ordinal();
        synchronized (_calculators) {
            if(_calculators[stat] == null)
               // _calculators[stat] = new Calculator(f.getStat(), this);

            _calculators[stat].addFunc(f);
        }
    }

    public final double calcStat(@NonNull final Stats stat, final double init){
        /*val id = stat.ordinal();
        val calculator = _calculators[id];

        if(calculator == null || calculator.size() == 0)
            return init;

        val env = new Env(this);
        env.setValue(init);

        calculator.calculate(env);

        return env.getValue();*/

        return 0;
    }

    // Характеристики
    public int getBaseHp(){
        return (int) calcStat(Stats.MAX_HP, template.baseHpMax);
    }

    public void setCurHpDamage(final int damage){
        val tmpCurHp = curHp - damage;
        log.debug("Damage: " + damage + " "
        + "curHp: " + curHp + " "
        + "curHp - damage: " + tmpCurHp);
        if(tmpCurHp <= 0){
            curHp = 0;
            log.debug("You dead");
            return;
        }
        else
            this.curHp = tmpCurHp;
    }

    public void setCurHpRegen(final int regen){
        val tmpCurHp = curHp + regen;
        log.debug("Regen: " + regen + " "
                + "curHp: " + curHp + " "
                + "curHp + regen: " + tmpCurHp);
        if(tmpCurHp >= getBaseHp()){
            curHp = getBaseHp();
            log.debug("Hp is full");
            return;
        }
        else
            this.curHp = tmpCurHp;
    }

    public int getBaseMp(){
        return (int) calcStat(Stats.MAX_MP, template.baseMpMax);
    }

    public void setCurMpDamage(final int damage){
        val tmpCurMp = curMp - damage;
        log.debug("Damage: " + damage + " "
                + "curMp: " + curMp + " "
                + "curMp - damage: " + tmpCurMp);
        if(tmpCurMp <= 0){
            curMp = 0;
            log.debug("Your manna is over");
            return;
        }
        else
            this.curMp = tmpCurMp;
    }

    public int getSTR() {
        return template.baseSTR;
    }

    public void setSTR(final int str){
        template.baseDEX = str;
    }

    public int getCON(){
        return template.baseCON;
    }

    public void setCon(final int con){
        template.baseCON = con;
    }

    public int getDEX(){
        return template.baseDEX;
    }

    public void setDEX(final int dex){
        template.baseDEX = dex;
    }

    public int getINT(){
        return template.baseINT;
    }

    public void setINT(final int intelligent){
        template.baseINT = intelligent;
    }

    public int getWIT(){
        return template.baseWIT;
    }

    public void setWIT(final int wit){
        template.baseWIT = wit;
    }

    public int getMEN(){
        return template.baseMEN;
    }

    public void setMEN(final int men){
        template.baseMEN = men;
    }

    public int getSpeed(){
        return (int) calcStat(Stats.RUN_SPEED, template.baseRunSpd);
    }

    // Методы, опредяляют к какому типу относится объект.
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
