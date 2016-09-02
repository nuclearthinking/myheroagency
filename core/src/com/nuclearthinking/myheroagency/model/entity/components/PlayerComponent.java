package com.nuclearthinking.myheroagency.model.entity.components;

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
 * Created by mkuksin on 01.09.2016.
 */
public class PlayerComponent implements Component {

    private final Calculator[] _calculators = new Calculator[Stats.NUM_STATS];

    public static final float baseHpReg = 10;
    public static final float baseMpReg = 10;
    public static final int baseHpMax = 100;
    public static final int baseMpMax = 100;
    public static final int basePAtk = 10;
    public static final int baseMAtk = 10;
    public static final int basePDef = 10;
    public static final int baseMDef = 10;
    public static final int basePAtkSpd = 10;
    public static final int baseMAtkSpd = 10;
    public static final int baseCritRate = 10;
    public static final int baseMCritRate = 10;
    public static final int baseRunSpd = 100;

    private @Getter @Setter static int baseSTR = 10;
    private @Getter @Setter static int baseCON = 10;
    private @Getter @Setter static int baseDEX = 10;
    private @Getter @Setter static int baseINT = 10;
    private @Getter @Setter static int baseWIT = 10;
    private @Getter @Setter static int baseMEN = 10;

    private @Getter static int level = 1;

    public PlayerComponent(){
        Function.addFuncToChar(this);
    }

    public final void addStatFunc(@NonNull final Func f) {
        val stat = f.getStat().ordinal();
        synchronized (_calculators) {
            if(_calculators[stat] == null)
                _calculators[stat] = new Calculator(f.getStat(), this);

            _calculators[stat].addFunc(f);
        }
    }

    public final double calcStat(@NonNull final Stats stat, final double init){
        val id = stat.ordinal();
        val calculator = _calculators[id];

        if(calculator == null || calculator.size() == 0)
            return init;

        val env = new Env(this);
        env.setValue(init);

        calculator.calculate(env);

        return env.getValue();
    }

    public int getSpeed(){
        return (int) calcStat(Stats.RUN_SPEED, baseRunSpd);
    }

    public boolean isPlayer(){
        return true;
    }

}
