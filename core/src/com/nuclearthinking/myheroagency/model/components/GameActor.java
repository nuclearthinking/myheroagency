package com.nuclearthinking.myheroagency.model.components;

import com.badlogic.ashley.core.Component;
import com.nuclearthinking.myheroagency.model.skills.Calculator;
import com.nuclearthinking.myheroagency.model.skills.Env;
import com.nuclearthinking.myheroagency.model.skills.Function;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

/**
 * Created by mkuksin on 03.10.2016.
 */
public abstract class GameActor implements Component {

    private Calculator[] calc;

    protected @Getter int baseHpReg;
    protected @Getter int baseMpReg;
    protected @Getter int baseHpMax;
    protected @Getter int baseMpMax;
    protected @Getter int basePAtk;
    protected @Getter int baseMAtk;
    protected @Getter int basePDef;
    protected @Getter int baseMDef;
    protected @Getter int basePAtkSpd;
    protected @Getter int baseMAtkSpd;
    protected @Getter int basePCritRate;
    protected @Getter int baseMCritRate;
    protected @Getter int basePCritChance;
    protected @Getter int baseMCritChance;
    protected @Getter int baseRunSpd;
    protected @Getter @Setter int CON;
    protected @Getter @Setter int DEX;
    protected @Getter @Setter int INT;
    protected @Getter @Setter int MEN;
    protected @Getter @Setter int STR;
    protected @Getter @Setter int WIT;

    protected @Getter int curHp;
    protected @Getter int level;

    protected abstract void init();

    public GameActor(){
        calc = new Calculator[Stats.NUM_STATS];
        Function.addFuncToChar(this);
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
