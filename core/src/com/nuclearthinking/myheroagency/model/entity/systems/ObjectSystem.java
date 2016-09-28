package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.components.FunctionComponent;
import com.nuclearthinking.myheroagency.model.entity.components.PlayerComponent;
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
public abstract class ObjectSystem extends IteratingSystem {

    public ObjectSystem(@NonNull final Family family){
        super(family);
        Function.addFuncToChar(this);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

    public final void addStatFunc(@NonNull final Func f) {
        @NonNull val stat = f.getStat().ordinal();
        @NonNull val calculators = FunctionComponent.getCalculators();
        synchronized (calculators) {
            if(calculators[stat] == null)
                calculators[stat] = new Calculator(f.getStat(), this);

            calculators[stat].addFunc(f);
        }
    }

    public final double calcStat(@NonNull final Stats stat, final double init){
        @NonNull val id = stat.ordinal();
        @NonNull val calculator = FunctionComponent.getCalculators()[id];

        if(calculator == null || calculator.size() == 0)
            return init;

        @NonNull val env = new Env(this);
        env.setValue(init);

        calculator.calculate(env);

        return env.getValue();
    }

    public int getLevel(){
        return 1;
    }

    public int getBaseSTR(){
        return 1;
    }

    public int getBaseINT(){
        return 1;
    }

    public int getBaseCON(){
        return 1;
    }

    public int getBaseMEN(){
        return 1;
    }

    public int getBaseWIT(){
        return 1;
    }

    public int getBaseDEX(){
        return 1;
    }

    public int getSpeed(){
        return (int) calcStat(Stats.RUN_SPEED, PlayerComponent.BASE_RUN_SPD);
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
