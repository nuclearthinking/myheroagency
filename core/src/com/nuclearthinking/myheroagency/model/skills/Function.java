package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.actor.GameObject;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;

/**
 * Created by Izonami on 03.08.2016.
 */
public class Function {

    public static final int MAX_STAT_VALUE = 100;

    private static final double[] WITbonus = new double[MAX_STAT_VALUE]; //castSpeed, mResist
    private static final double[] MENbonus = new double[MAX_STAT_VALUE]; //mpMax, mDef
    private static final double[] INTbonus = new double[MAX_STAT_VALUE]; //mAtkDmg, skillChance
    private static final double[] STRbonus = new double[MAX_STAT_VALUE]; //atkDmg, pDef
    private static final double[] DEXbonus = new double[MAX_STAT_VALUE]; //atackSpeed, speed
    private static final double[] CONbonus = new double[MAX_STAT_VALUE]; //hpMax, pResist

    private static final double P_ATK_MOD = 0.6;
    private static final double MAX_HP_MOD = 1.5;
    private static final double MAX_MP_MOD = 1.5;
    private static final double M_ATK_MOD = 1.5;

    //TODO: Это полностью переделать, желательно брать из файла или придумать формулу, сейчас просто затычка для проверки
    static {
        for (int i = 0; i < MAX_STAT_VALUE; i++) {
            CONbonus[i] = (200.0 + (i+.0)) / 100.0;
            MENbonus[i] = (100.0 + MAX_STAT_VALUE - (i+.0)) / 100.0;
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
            env.setValue(env.getValue() * STRbonus[env.getCharacter().getSTR()] * env.getCharacter().getLevel() * P_ATK_MOD);
        }
    }

    private static class FuncMAtkMod extends Func{
        private static final FuncMAtkMod func = new FuncMAtkMod();

        private FuncMAtkMod(){
            super(Stats.MAGIC_ATTACK, 0x20, null);
        }

        @Override
        public void calc(Env env) {
            env.setValue(env.getValue() * INTbonus[env.getCharacter().getINT()] * env.getCharacter().getLevel() * M_ATK_MOD);
        }
    }

    private static class FuncMaxHpMod extends Func {

        private static final FuncMaxHpMod func = new FuncMaxHpMod();

        public FuncMaxHpMod() {
            super(Stats.MAX_HP, 0x20, null);
        }

        @Override
        public void calc(Env env) {
            logger.debug("Base Value "+getClass().getSimpleName()+": "+env.getValue());
            logger.debug("CON: "+env.getCharacter().getCON());
            logger.debug("ConBonus: "+CONbonus[env.getCharacter().getCON()]);
            logger.debug("All Env :"+env.getValue() * CONbonus[env.getCharacter().getCON()] * MAX_HP_MOD);
            env.setValue(env.getValue() * CONbonus[env.getCharacter().getCON()] * MAX_HP_MOD);
        }
    }

    private static class FuncMaxMpMod extends Func{

        private static final FuncMaxMpMod func = new FuncMaxMpMod();

        public FuncMaxMpMod() {
            super(Stats.MAX_MP, 0x20, null);
        }

        @Override
        public void calc(Env env) {
            env.setValue(env.getValue() * MENbonus[env.getCharacter().getMEN()] * MAX_MP_MOD);
        }
    }

    public static void addFuncToChar(final GameObject character){
        if(character.isPlayer()){
            character.addStatFunc(FuncPAtkMod.func);
            character.addStatFunc(FuncMAtkMod.func);
            character.addStatFunc(FuncMaxHpMod.func);
            character.addStatFunc(FuncMaxMpMod.func);
        }
    }
}
