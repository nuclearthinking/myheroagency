package com.nuclearthinking.myheroagency.model.actor.base;

import com.badlogic.ashley.core.Component;
import com.nuclearthinking.myheroagency.model.stat.Calculator;
import com.nuclearthinking.myheroagency.model.stat.Env;
import com.nuclearthinking.myheroagency.model.stat.Function;
import com.nuclearthinking.myheroagency.model.stat.Stats;
import com.nuclearthinking.myheroagency.model.stat.funcs.Func;
import lombok.*;

/**
 * Created by mkuksin on 03.10.2016.
 */
@ToString
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
    protected @Getter int id;
    protected @Getter String name = "Игрок";

    public void initialize(@NonNull final GameObject template) {
        id = template.getId();
        name = template.getName();
        level = template.getLevel();

        baseHpMax = template.getBaseHpMax();
        baseMpMax = template.getBaseMpMax();
        baseHpReg = template.getBaseHpReg();
        baseMpReg = template.getBaseMpReg();
        basePAtk = template.getBasePAtk();
        baseMAtk = template.getBaseMAtk();
        basePDef = template.getBasePDef();
        baseMDef = template.getBaseMDef();
        basePAtkSpd = template.getBasePAtkSpd();
        baseMAtkSpd = template.getBaseMAtkSpd();
        basePCritRate = template.getBasePCritRate();
        baseMCritRate = template.getBaseMCritRate();
        basePCritChance = template.getBasePCritChance();
        baseMCritChance = template.getBaseMCritChance();
        baseRunSpd = template.getBaseRunSpd();

        CON = template.getCON();
        DEX = template.getDEX();
        INT = template.getINT();
        MEN = template.getMEN();
        STR = template.getSTR();
        WIT = template.getWIT();
    }

    public GameActor() {
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

    public final double calcStat(@NonNull final Stats stat, final double init) {
        @NonNull val id = stat.ordinal();
        @NonNull val calculator = calc[id];

        if(calculator == null || calculator.size() == 0)
            return init;

        @NonNull val env = new Env(this);
        env.setValue(init);

        calculator.calculate(env);

        return env.getValue();
    }

    public int getMaxHp(){
        return (int) calcStat(Stats.MAX_HP, baseHpMax);
    }

    public int getMaxMp(){
        return (int) calcStat(Stats.MAX_MP, baseMpMax);
    }

    public int getSpeed(){
        return (int) calcStat(Stats.RUN_SPEED, baseRunSpd);
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
