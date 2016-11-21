package com.nuclearthinking.myheroagency.model.components;

import com.badlogic.ashley.core.Entity;
import com.nuclearthinking.myheroagency.model.monster.Monster;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 03.10.2016.
 */
public final class MonsterComponent extends GameActor {

    private @Getter @Setter Entity target;
    private @Setter Monster template;
    private @Getter @Setter int id;
    private @Getter @Setter String name;

    @Override
    public void init() {
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
        baseSTR = template.getSTR();
        baseCON = template.getCON();
        baseDEX = template.getDEX();
        baseINT = template.getINT();
        baseWIT = template.getWIT();
        baseMEN = template.getMEN();
    }

    public MonsterComponent(){
        super();
    }

}
