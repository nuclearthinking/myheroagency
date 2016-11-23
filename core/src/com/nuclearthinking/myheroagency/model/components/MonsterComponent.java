package com.nuclearthinking.myheroagency.model.components;

import com.badlogic.ashley.core.Entity;
import com.nuclearthinking.myheroagency.model.monster.Monster;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by mkuksin on 03.10.2016.
 */
@ToString
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
        STR = template.getSTR();
        CON = template.getCON();
        DEX = template.getDEX();
        INT = template.getINT();
        WIT = template.getWIT();
        MEN = template.getMEN();
    }

    public MonsterComponent(){
        super();
    }

    public int getSpeed(){
        return (int) calcStat(Stats.RUN_SPEED, baseRunSpd);
    }

    @Override
    public boolean isMonster(){
        return true;
    }
}
