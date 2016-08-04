package com.nuclearthinking.myheroagency.model.template;

/**
 * Created by Izonami on 04.08.2016.
 */
public class CharTemplate {

    public final byte baseSTR;
    public final byte baseCON;
    public final byte baseDEX;
    public final byte baseINT;
    public final byte baseWIT;
    public final byte baseMEN;

    public final int baseHpMax;
    public final int baseMpMax;

    public final float baseHpReg;
    public final float baseMpReg;

    public final int basePAtk;
    public final int baseMAtk;
    public final int basePDef;
    public final int baseMDef;
    public final int basePAtkSpd;
    public final int baseMAtkSpd;
    public final int baseCritRate;
    public final int baseMCritRate;

    public final int baseRunSpd;

    public CharTemplate(){
        baseSTR = 10;
        baseCON = 10;
        baseDEX = 10;
        baseINT = 10;
        baseWIT = 10;
        baseMEN = 10;
        baseHpMax = 100;
        baseMpMax = 100;
        baseHpReg = 0.1f;
        baseMpReg = 0.1f;
        basePAtk = 10;
        baseMAtk = 10;
        basePDef = 10;
        baseMDef = 10;
        basePAtkSpd = 50;
        baseMAtkSpd = 50;
        baseCritRate = 5;
        baseMCritRate = 1;
        baseRunSpd = 100;
    }

    public static CharTemplate getInstance(){
       return SingletonHolder._instance;
    }

    private static class SingletonHolder
    {
        private static final CharTemplate _instance = new CharTemplate();
    }

    public CharTemplate getTemplate(){
        return this;
    }

}
