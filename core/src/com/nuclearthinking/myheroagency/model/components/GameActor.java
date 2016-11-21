package com.nuclearthinking.myheroagency.model.components;

import com.badlogic.ashley.core.Component;
import com.nuclearthinking.myheroagency.model.npc.Npc;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by mkuksin on 03.10.2016.
 */
public abstract class GameActor implements Component {

    private @Getter int baseHpReg;
    private @Getter int baseMpReg;
    private @Getter int baseHpMax;
    private @Getter int baseMpMax;
    private @Getter int basePAtk;
    private @Getter int baseMAtk;
    private @Getter int basePDef;
    private @Getter int baseMDef;
    private @Getter int basePAtkSpd;
    private @Getter int baseMAtkSpd;
    private @Getter int basePCritRate;
    private @Getter int baseMCritRate;
    private @Getter int basePCritChance;
    private @Getter int baseMCritChance;
    private @Getter int baseRunSpd;
    private @Getter @Setter int baseCON;
    private @Getter @Setter int baseDEX;
    private @Getter @Setter int baseINT;
    private @Getter @Setter int baseMEN;
    private @Getter @Setter int baseSTR;
    private @Getter @Setter int baseWIT;

    private @Getter int curHp;
    private @Getter int level;

    public void init(@NonNull final Npc template){
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
        baseSTR = template.getSTR();
        baseCON = template.getCON();
        baseDEX = template.getDEX();
        baseINT = template.getINT();
        baseWIT = template.getWIT();
        baseMEN = template.getMEN();
    }

    public GameActor(){
    }
}
