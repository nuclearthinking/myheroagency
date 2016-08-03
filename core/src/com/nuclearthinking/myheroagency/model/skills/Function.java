package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.actor.GameObject;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;

/**
 * Created by Izonami on 03.08.2016.
 */
public class Function {

    public static final int MAX_STAT_VALUE = 100;

    public static final double[] WITbonus = new double[MAX_STAT_VALUE];
    public static final double[] MENbonus = new double[MAX_STAT_VALUE];
    public static final double[] INTbonus = new double[MAX_STAT_VALUE];
    public static final double[] STRbonus = new double[MAX_STAT_VALUE];
    public static final double[] DEXbonus = new double[MAX_STAT_VALUE];
    public static final double[] CONbonus = new double[MAX_STAT_VALUE];

    private static class FuncPAtkMod extends Func {
        private static final FuncPAtkMod func = new FuncPAtkMod();

        private FuncPAtkMod()
        {
            super(Stats.POWER_ATTACK, 0x20, null);
        }

        @Override
        public void calc(final Env env) {
            env.value *= STRbonus[env.player.getSTR()] * env.player.getLevel();
        }
    }

    public static void addFuncToChar(final GameObject character){
        if(character.isPlayer()){
            character.addStatFunc(FuncPAtkMod.func);
        }
    }
}
