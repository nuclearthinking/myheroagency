package com.nuclearthinking.myheroagency.model.actor.base;

/**
 * Created by mkuksin on 24.11.2016.
 */
public interface GameObject {

    int getId();
    String getName();
    int getLevel();
    int getBaseHpReg();
    int getBaseMpReg();
    int getBaseHpMax();
    int getBaseMpMax();
    int getBasePAtk();
    int getBaseMAtk();
    int getBasePDef();
    int getBaseMDef();
    int getBasePAtkSpd();
    int getBaseMAtkSpd();
    int getBasePCritRate();
    int getBaseMCritRate();
    int getBasePCritChance();
    int getBaseMCritChance();
    int getBaseRunSpd();
    int[] getSkills();

    int getSTR();
    int getCON();
    int getDEX();
    int getINT();
    int getWIT();
    int getMEN();
}
