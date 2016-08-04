package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.actor.GameObject;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;

/**
 * Created by Izonami on 03.08.2016.
 */
public class Function {

    public static final int MAX_STAT_VALUE = 100;

    private static final double[] WITbonus = new double[MAX_STAT_VALUE];
    private static final double[] MENbonus = new double[MAX_STAT_VALUE];
    private static final double[] INTbonus = new double[MAX_STAT_VALUE];
    private static final double[] STRbonus = new double[MAX_STAT_VALUE];
    private static final double[] DEXbonus = new double[MAX_STAT_VALUE];
    private static final double[] CONbonus = new double[MAX_STAT_VALUE];

    private static final double STR_MOD = 0.6;
    private static final double CON_MOD = 1.5;
    private static final double INT_MOD = 1.5;

    static {
        for (int i = 0; i < MAX_STAT_VALUE; i++) {
            CONbonus[i] = (100 + MAX_STAT_VALUE - i) / 100;
        }
    }

    private static class FuncPAtkMod extends Func {
        private static final FuncPAtkMod func = new FuncPAtkMod();

        private FuncPAtkMod()
        {
            super(Stats.POWER_ATTACK, 0x20, null);
        }

        @Override
        public void calc(final Env env) {
            env.setValue(env.getValue() * STRbonus[env.getCharacter().getSTR()] * env.getCharacter().getLevel() * STR_MOD);
        }
    }

    private static class FuncMAtkMod extends Func{
        private static final FuncMAtkMod func = new FuncMAtkMod();

        private FuncMAtkMod(){
            super(Stats.MAGIC_ATTACK, 0x20, null);
        }

        @Override
        public void calc(Env env) {
            env.setValue(env.getValue() * INTbonus[env.getCharacter().getINT()] * env.getCharacter().getLevel() * INT_MOD);
        }
    }

    private static class FuncMaxHpMul extends Func {

        private static final FuncMaxHpMul func = new FuncMaxHpMul();

        public FuncMaxHpMul() {
            super(Stats.MAX_HP, 0x20, null);
        }

        @Override
        public void calc(Env env) {
            env.setValue(env.getValue() * CONbonus[env.getCharacter().getCON()] * CON_MOD);
        }
    }

    public static void addFuncToChar(final GameObject character){
        if(character.isPlayer()){
            character.addStatFunc(FuncPAtkMod.func);
            character.addStatFunc(FuncMAtkMod.func);
            character.addStatFunc(FuncMaxHpMul.func);
        }
    }
}
