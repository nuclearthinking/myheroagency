package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.components.GameActor;
import com.nuclearthinking.myheroagency.model.skills.Calculator;
import com.nuclearthinking.myheroagency.model.skills.Env;
import com.nuclearthinking.myheroagency.model.skills.Function;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 26.09.2016.
 */
public abstract class ActorSystem extends IteratingSystem {

    private Calculator[] calc;

    protected GameActor actor;

    public ActorSystem(@NonNull final Family family){
        super(family);
        calc = new Calculator[Stats.NUM_STATS];
        Function.addFuncToChar(this);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

    public final void addStatFunc(@NonNull final Func f) {
        @NonNull val stat = f.getStat().ordinal();
        @NonNull val calculator = calc;
        synchronized (calculator) {
            if(calculator[stat] == null)
                calculator[stat] = new Calculator(f.getStat(), this);

            calculator[stat].addFunc(f);
        }
    }

    public final double calcStat(@NonNull final Stats stat, final double init){
        @NonNull val id = stat.ordinal();
        @NonNull val calculator = calc[id];

        if(calculator == null || calculator.size() == 0)
            return init;

        @NonNull val env = new Env(this);
        env.setValue(init);

        calculator.calculate(env);

        return env.getValue();
    }

    public void setActor(@NonNull final GameActor actor){
        this.actor = actor;
    }

    public int getLevel(){
        return actor.getLevel();
    }

    public int getBaseSTR(){
        return actor.getBaseSTR();
    }

    public int getBaseINT(){
        return actor.getBaseINT();
    }

    public int getBaseCON(){
        return actor.getBaseCON();
    }

    public void setCon(final int con){
        actor.setBaseCON(con);
    }

    public int getBaseMEN(){
        return actor.getBaseMEN();
    }

    public int getBaseWIT(){
        return actor.getBaseWIT();
    }

    public int getBaseDEX(){
        return actor.getBaseDEX();
    }

    public boolean isNpc(){
        return false;
    }

    public boolean isMonster(){
        return false;
    }

    public boolean isPlayer(){
        return false;
    }
}
